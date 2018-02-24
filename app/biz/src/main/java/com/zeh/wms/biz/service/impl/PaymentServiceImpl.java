package com.zeh.wms.biz.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.PaymentOrderMapper;
import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.biz.service.PaymentService;
import com.zeh.wms.dal.daointerface.PaymentOrderDAO;
import com.zeh.wms.dal.dataobject.PaymentOrderDO;
import com.zeh.wms.dal.operation.paymentorder.GetAllDataQuery;
import com.zeh.wms.dal.operation.paymentorder.GetPageDataQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author hzy24985
 * @version $Id: PaymentServiceImpl, v 0.1 2018/2/8 19:54 hzy24985 Exp $
 */
@Service
public class PaymentServiceImpl extends AbstractService implements PaymentService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger                logger        = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Resource
    private PaymentOrderDAO              paymentOrderDAO;
    @Resource
    private PaymentOrderMapper           paymentOrderMapper;
    @Resource
    private WxPayService                 wxPayService;

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

    @Override
    public String payCallback(String xmlData) {
        try {
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlData);
            // TODO: 2018/2/24 待确认。
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return WxPayNotifyResponse.success("OK");
    }

}
