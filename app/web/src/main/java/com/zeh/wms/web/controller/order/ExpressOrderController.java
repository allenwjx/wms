package com.zeh.wms.web.controller.order;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.dal.operation.expressorder.FindPageQuery;
import com.zeh.wms.dal.operation.expressorder.GetAllByParsQuery;
import com.zeh.wms.web.constant.ExcelConstant;
import com.zeh.wms.web.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * 查看详情页面
     *
     * @param id 订单ID
     * @return
     */
    @RequestMapping("one")
    @ResponseBody
    public ExpressOrderVO one(Long id) throws ServiceException {
        ExpressOrderVO expressOrderVO = new ExpressOrderVO();
        if (id != null) {
            expressOrderVO = expressOrderService.getOrderDetailInfo(id);
        }
        return expressOrderVO;
    }
    @RequestMapping(value = "export", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> export(GetAllByParsQuery query, HttpServletRequest request) throws ServiceException {
        return expressOrderService.export(query, getRealFileName(request, ExcelConstant.SF_FILE_PATH));
    }
}
