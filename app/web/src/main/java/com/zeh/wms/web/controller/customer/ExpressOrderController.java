package com.zeh.wms.web.controller.customer;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.dal.operation.expressorder.FindPageQuery;
import com.zeh.wms.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * The type Express order controller.
 *
 * @author hzy24985
 * @version $Id : ExpressOrderController, v 0.1 2018/2/7 22:08 hzy24985 Exp $
 */
@Controller
@RequestMapping("/order/express")
public class ExpressOrderController extends BaseController {
    /**
     * The Express order service.
     */
    @Resource
    private ExpressOrderService expressOrderService;

    /**
     * 分页查询
     *
     * @param query the query
     * @return page list
     * @throws ServiceException the service exception
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<ExpressOrderVO> list(FindPageQuery query) throws ServiceException {
        return expressOrderService.pageQueryExpressOrders(query);
    }
}
