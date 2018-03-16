package com.zeh.wms.biz.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.binarywang.wxpay.util.SignUtils;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.PaymentOrderMapper;
import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.biz.model.enums.ExpressOrderStateEnum;
import com.zeh.wms.biz.model.enums.PaymentStateEnum;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.biz.service.PaymentService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.PaymentOrderDAO;
import com.zeh.wms.dal.dataobject.PaymentOrderDO;
import com.zeh.wms.dal.operation.expressorder.UpdateStatusParameter;
import com.zeh.wms.dal.operation.paymentorder.GetAllDataQuery;
import com.zeh.wms.dal.operation.paymentorder.GetPageDataQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    private static Logger                logger        = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private static final String          SUCCESS       = "SUCCESS";
    /** 支付单dao */
    @Resource
    private PaymentOrderDAO              paymentOrderDAO;
    /** 支付单转化器 */
    @Resource
    private PaymentOrderMapper           paymentOrderMapper;
    /** 微信支付服务 */
    @Resource
    private WxPayServiceImpl             wxPayService;
    /** 快递单服务 */
    @Resource
    private ExpressOrderService          expressOrderService;

    @Override
    public PageList<PaymentOrderVO> pageQueryPaymentOrders(GetPageDataQuery orderQuery) throws ServiceException {
        PageList<PaymentOrderDO> list = paymentOrderDAO.getPageData(orderQuery);
        Collection<PaymentOrderVO> result = paymentOrderMapper.d2vs(list.getData());
        return PageUtils.createPageList(result, list.getPaginator());
    }

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
     * @throws ServiceException the service exception
     */
    @Override
    public PaymentOrderVO createPayOrder(PaymentOrderVO paymentOrderVO) throws ServiceException {
        paymentOrderVO.setPaymentOrderNo(CodeGenerator.generatePaySerialNo());
        paymentOrderVO.setStatus(PaymentStateEnum.WATI_PAY);
        PaymentOrderDO paymentOrderDO = paymentOrderMapper.v2d(paymentOrderVO);
        long id = paymentOrderDAO.insert(paymentOrderDO);
        checkInsert(id, "支付单");
        paymentOrderVO.setId(id);

        return paymentOrderVO;
    }

    /**
     * 生成微信统一订单，返回签名信息。
     * @return 返回签名信息，用于微信支付.
     * @throws ServiceException service exception.
     */
    @Override
    public String createWechatOrder() throws ServiceException {
        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
        try {
            WxPayMpOrderResult result = wxPayService.createOrder(request);
            return result.getPaySign();
        } catch (WxPayException e) {
            throw new ServiceException(ERROR_FACTORY.wechatUniOrderFail(e.getReturnMsg()));
        }
    }

    /**
     * 支付回调处理
     * @param xmlData xml data
     * @return result
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
     * @param result 微信返回结果
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
