package com.zeh.wms.biz.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.common.collect.Lists;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.jungle.utils.common.DateUtil;
import com.zeh.jungle.utils.serializer.FastJsonUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.PaymentOrderMapper;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.PaymentChannelEnum;
import com.zeh.wms.biz.model.enums.PaymentStateEnum;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.biz.service.PaymentService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.PaymentOrderDAO;
import com.zeh.wms.dal.dataobject.PaymentOrderDO;
import com.zeh.wms.dal.operation.expressorder.UpdateStatusParameter;
import com.zeh.wms.dal.operation.paymentorder.GetAllDataQuery;
import com.zeh.wms.dal.operation.paymentorder.GetPageDataQuery;
import com.zeh.wms.integration.wechat.model.UnifiedorderDetailReqDto;
import com.zeh.wms.integration.wechat.model.UnifiedorderGoodsDetailReqDto;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * The type Payment service.
 *
 * @author hzy24985
 * @version $Id : PaymentServiceImpl, v 0.1 2018/2/8 19:54 hzy24985 Exp $
 */
@Service
public class PaymentServiceImpl extends AbstractService implements PaymentService {

    /**
     * The Logger.
     */
    private static Logger       logger  = LoggerFactory.getLogger(PaymentServiceImpl.class);

    /**
     * The constant SUCCESS.
     */
    private static final String SUCCESS = "SUCCESS";
    /**
     * 支付单dao
     */
    @Resource
    private PaymentOrderDAO     paymentOrderDAO;
    /**
     * 支付单转化器
     */
    @Resource
    private PaymentOrderMapper  paymentOrderMapper;
    /**
     * 微信支付服务
     */
    @Resource
    private WxPayServiceImpl    wxPayService;
    /**
     * 快递单服务
     */
    @Resource
    private ExpressOrderService expressOrderService;

    /**
     * Page query payment orders page list.
     *
     * @param orderQuery the order query
     * @return the page list
     * @throws ServiceException the service exception
     */
    @Override
    public PageList<PaymentOrderVO> pageQueryPaymentOrders(GetPageDataQuery orderQuery) throws ServiceException {
        PageList<PaymentOrderDO> list = paymentOrderDAO.getPageData(orderQuery);
        Collection<PaymentOrderVO> result = paymentOrderMapper.d2vs(list.getData());
        return PageUtils.createPageList(result, list.getPaginator());
    }

    /**
     * Gets payment detail info.
     *
     * @param id the id
     * @return the payment detail info
     * @throws ServiceException the service exception
     */
    @Override
    public PaymentOrderVO getPaymentDetailInfo(Long id) throws ServiceException {
        PaymentOrderDO paymentOrderDO = paymentOrderDAO.queryById(id);
        if (paymentOrderDO == null) {
            throw new ServiceException(ERROR_FACTORY.notFindExpressOrderDetail(id));
        }
        // TODO: 2018/2/24 是否需要查询微信的订单详情。
        //        WxPayOrderQueryResult payResult = null;
        //        try {
        //            payResult = wxPayService.queryOrder(paymentOrderDO.getOtherPaymentNo(), paymentOrderDO.getPaymentOrderNo());
        //        } catch (WxPayException e) {
        //            e.printStackTrace();
        //
        //        }
        return paymentOrderMapper.d2v(paymentOrderDO);
    }

    /**
     * Export response entity.
     *
     * @param query        the query
     * @param templatePath the template path
     * @return the response entity
     * @throws ServiceException the service exception
     */
    @Override
    public ResponseEntity<byte[]> export(GetAllDataQuery query, String templatePath) throws ServiceException {
        List<PaymentOrderDO> list = paymentOrderDAO.getAllData(query);
        Collection<PaymentOrderVO> result = paymentOrderMapper.d2vs(list);
        String fileName = "支付信息导出" + System.currentTimeMillis() + ".xlsx";
        return getExcel(templatePath, fileName, result);
    }

    /**
     * Creat pay order.
     *
     * @param paymentOrderVO the payment order vo
     * @return the payment order vo
     * @throws ServiceException the service exception
     */
    @Override
    public PaymentOrderVO createPayOrder(PaymentOrderVO paymentOrderVO) throws ServiceException {
        paymentOrderVO.setPaymentOrderNo(CodeGenerator.generatePaySerialNo());
        paymentOrderVO.setStatus(PaymentStateEnum.WATI_PAY);
        //订单创建的30分钟后过期. TODO 具体超时时间待定.
        paymentOrderVO.setPayLimited(60 * 30);
        PaymentOrderDO paymentOrderDO = paymentOrderMapper.v2d(paymentOrderVO);
        long id = paymentOrderDAO.insert(paymentOrderDO);
        checkInsert(id, "支付单");
        paymentOrderVO.setId(id);

        return paymentOrderVO;
    }

    /**
     * 创建支付单，同时创建微信统一订单。
     * 每次创建都重新生成新的支付单。
     *
     * @param orderNo     订单流水号
     * @param currentUser the current user
     * @return wx pay mp order result
     * @throws ServiceException the service exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WxPayMpOrderResult goPay(String orderNo, UserVO currentUser) throws ServiceException {
        ExpressOrderVO orderVO = expressOrderService.queryOrderByOrderSerialNo(orderNo);
        if (orderVO == null) {
            throw new ServiceException(ERROR_FACTORY.notFondOrderNoForPay(orderNo));
        }
        PaymentOrderVO paymentOrderVO = new PaymentOrderVO();
        paymentOrderVO.setAmount(orderVO.getTotalPrice());
        paymentOrderVO.setChannel(PaymentChannelEnum.WX);
        paymentOrderVO.setOrderNo(orderNo);
        paymentOrderVO.setUserId(currentUser.getId());
        paymentOrderVO.setCode(currentUser.getMobile());
        paymentOrderVO = createPayOrder(paymentOrderVO);
        return createWechatOrder(orderVO, paymentOrderVO, currentUser);
    }

    /**
     * 生成微信统一订单，返回签名信息。
     *
     * @param order          the order
     * @param paymentOrderVO the payment order vo
     * @return 返回签名信息 ，用于微信支付.
     * @throws ServiceException service exception.
     */
    @Override
    public WxPayMpOrderResult createWechatOrder(ExpressOrderVO order, PaymentOrderVO paymentOrderVO, UserVO userVO) throws ServiceException {

        try {
            WxPayUnifiedOrderRequest request = getWxPayUnifiedOrderRequest(order, paymentOrderVO, userVO);
            WxPayMpOrderResult result = wxPayService.createOrder(request);
            return result;
        } catch (WxPayException e) {
            throw new ServiceException(ERROR_FACTORY.wechatUniOrderFail(e.getReturnMsg()));
        }
    }

    /**
     * Gets wx pay unified order request.
     *
     * @param order          the order
     * @param paymentOrderVO the payment order vo
     * @return the wx pay unified order request
     */
    private WxPayUnifiedOrderRequest getWxPayUnifiedOrderRequest(ExpressOrderVO order, PaymentOrderVO paymentOrderVO, UserVO userVO) {
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();

        UnifiedorderDetailReqDto reqDto = new UnifiedorderDetailReqDto();
        reqDto.setCostPrice(paymentOrderVO.getAmount());
        reqDto.setReceiptId(paymentOrderVO.getPaymentOrderNo());

        UnifiedorderGoodsDetailReqDto goods = new UnifiedorderGoodsDetailReqDto();
        goods.setGoodsId("CommodityId");
        goods.setGoodsName(order.getCommodityName());
        //商品单价
        goods.setPrice(order.getTotalPrice());
        goods.setQuantity(order.getCommodityQuanity());
        //goodsCategory, body, api文档没有wxtool里定义了，不知道是否正确.
        goods.setGoodsCategory("TODO");
        goods.setBody("TODO");
        reqDto.setGoodsDetail(Lists.newArrayList(goods));

        request.setDetail(FastJsonUtils.toJSONString(reqDto));
        request.setAttach(paymentOrderVO.getPaymentOrderNo());
        request.setOutTradeNo(order.getOrderNo());
        request.setDeviceInfo("TODO");
        request.setBody("TODO 商家名称-销售商品类目");

        request.setFeeType("CNY");
        request.setTotalFee(order.getTotalPrice());
        request.setSpbillCreateIp("TODO IP");
        request.setLimitPay(DateUtil.getLongDateString(order.getGmtCreate()));
        request.setTimeExpire(new DateTime(order.getGmtCreate()).plusSeconds(paymentOrderVO.getPayLimited()).toString(DateUtil.LONG_FORMAT));

        request.setNotifyURL("http://47.97.222.254:8080/wms/api/callback/payCallback");
        request.setTradeType("JSAPI");
        request.setProductId("CommodityId");
        request.setOpenid(userVO.getOpenId());
        return request;
    }

    /**
     * 支付回调处理
     *
     * @param xmlData xml data
     * @return result string
     */
    @Override
    public String payCallback(String xmlData) {
        try {
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlData);
            //校验签名.
            validateSign(result);

            boolean isSuccess = false;
            //通讯失败不做处理
            if (StringUtils.equals(result.getResultCode(), SUCCESS)) {
                //业务处理成功，更新订单、支付状态。业务处理失败，只更新支付状态。
                if (StringUtils.equals(result.getReturnCode(), SUCCESS)) {
                    isSuccess = true;
                    //支付成功，更新订单状态，支付不成功，不更新订单状态
                    updateOrderStatus(result);
                }

                //更新支付单状态
                updatePaymentStatus(result, isSuccess);
            }

        } catch (WxPayException | ServiceException e) {
            logger.error("支付回调异常, xmlData: " + xmlData, e);
        }
        return WxPayNotifyResponse.success("OK");
    }

    /**
     * 校验参数.
     *
     * @param result 微信返回的结果
     * @throws WxPayException wechat pay exception.
     */
    private void validateSign(WxPayOrderNotifyResult result) throws WxPayException {
        Map<String, String> map = result.toMap();
        if (result.getSign() != null && !SignUtils.checkSign(map, null, wxPayService.getConfig().getMchKey())) {
            logger.error("校验结果签名失败，参数：{}", map);
            throw new WxPayException("参数格式校验错误！");
        }
    }

    /**
     * 更新支付单状态。
     *
     * @param result    微信返回结果
     * @param isSuccess 是否成功
     */
    private void updatePaymentStatus(WxPayOrderNotifyResult result, boolean isSuccess) {
        UpdateStatusParameter parameter = new UpdateStatusParameter();
        parameter.setStatus(isSuccess ? PaymentStateEnum.PAYED.getCode() : PaymentStateEnum.PAY_FAIL.getCode());
        parameter.setOrderNo(result.getAttach());
        parameter.setModifyBy("SYSTEM");
        paymentOrderDAO.updateStatus(parameter);
    }

    /**
     * 更新订单状态
     *
     * @param result 微信返回结果
     * @throws ServiceException service exception.
     */
    private void updateOrderStatus(WxPayOrderNotifyResult result) throws ServiceException {
        UpdateStatusParameter parameter = new UpdateStatusParameter();
        parameter.setOrderNo(result.getOutTradeNo());
        parameter.setStatus(ExpressOrderStateEnum.WAIT_SEND.getCode());
        parameter.setModifyBy("SYSTEM");
        expressOrderService.updateOrderStatus(parameter);
    }

}
