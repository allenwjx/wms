package com.zeh.wms.web.controller.order;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.PaymentOrderVO;
import com.zeh.wms.biz.service.PaymentService;
import com.zeh.wms.dal.operation.paymentorder.GetAllDataQuery;
import com.zeh.wms.dal.operation.paymentorder.GetPageDataQuery;
import com.zeh.wms.web.constant.ExcelConstant;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.PaymentQueryForm;
import com.zeh.wms.web.mapper.PaymentFormMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * The type Payment order controller.
 *
 * @author hzy24985
 * @version $Id : PaymentOrderController, v 0.1 2018/2/8 20:25 hzy24985 Exp $
 */
@Controller
@RequestMapping("/order/payment")
public class PaymentOrderController extends BaseController {

    /**
     * The Payment service.
     */
    @Resource
    private PaymentService paymentService;

    @Resource
    private PaymentFormMapper paymentFormMapper;

    /**
     * 分页查询
     *
     * @param form the form
     * @return page list
     * @throws ServiceException the service exception
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<PaymentOrderVO> list(PaymentQueryForm form) throws ServiceException {
        GetPageDataQuery query = paymentFormMapper.f2q(form);
        return paymentService.pageQueryPaymentOrders(query);
    }

    /**
     * 查看详情页面
     *
     * @param id 订单ID
     * @return payment order vo
     * @throws ServiceException the service exception
     */
    @RequestMapping("one")
    @ResponseBody
    public PaymentOrderVO one(Long id) throws ServiceException {
        PaymentOrderVO paymentOrderVO = new PaymentOrderVO();
        if (id != null) {
            paymentOrderVO = paymentService.getPaymentDetailInfo(id);
        }
        return paymentOrderVO;
    }

    /**
     * Export response entity.
     *
     * @param query   the query
     * @param request the request
     * @return the response entity
     * @throws ServiceException the service exception
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> export(GetAllDataQuery query, HttpServletRequest request) throws ServiceException {
        return paymentService.export(query, getRealFileName(request, ExcelConstant.PAYMENT_FILE_PATH));
    }
}
