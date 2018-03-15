package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ExpressOrderMapper;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.ExpressOrderDAO;
import com.zeh.wms.dal.daointerface.ExpressOrderItemDAO;
import com.zeh.wms.dal.dataobject.ExpressOrderDO;
import com.zeh.wms.dal.dataobject.ExpressOrderItemDO;
import com.zeh.wms.dal.operation.expressorder.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author hzy24985
 * @version $Id: ExpressOrderServiceImpl, v 0.1 2018/2/7 21:48 hzy24985 Exp $
 */
@Service
public class ExpressOrderServiceImpl extends AbstractService implements ExpressOrderService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger                logger        = LoggerFactory.getLogger(ExpressOrderServiceImpl.class);

    @Resource
    private ExpressOrderDAO              expressOrderDAO;

    @Resource
    private ExpressOrderItemDAO          expressOrderItemDAO;

    @Resource
    private ExpressOrderMapper           expressOrderMapper;

    /**
     * 创建订单
     *
     * @param expressOrder
     * @throws ServiceException
     */
    @Override
    public void createOrder(ExpressOrderVO expressOrder) throws ServiceException {
        if (expressOrder == null) {
            throw new RuntimeException("订单无效");
        }
        expressOrder.setOrderNo(CodeGenerator.generateOrderSerialNo());
        ExpressOrderDO expressOrderDO = expressOrderMapper.v2d(expressOrder);
        expressOrderDAO.insert(expressOrderDO);
    }

    /**
     * 更新订单状态
     *
     * @param updateStatusParameter
     * @throws ServiceException
     */
    @Override
    public void updateOrderStatus(UpdateStatusParameter updateStatusParameter) throws ServiceException {
        ExpressOrderVO order = queryOrderByOrderSerialNo(updateStatusParameter.getOrderNo());
        if (order == null) {
            throw new RuntimeException("无效订单，订单不存在，订单流水号：" + updateStatusParameter.getOrderNo());
        }
        expressOrderDAO.updateStatus(updateStatusParameter);
    }

    /**
     * 更新用户订单价格，和包裹重量
     *
     * @param updateCommodityWeightAndPriceParameter
     * @throws ServiceException
     */
    @Override
    public void updateCommodityWeightAndPrice(UpdateCommodityWeightAndPriceParameter updateCommodityWeightAndPriceParameter) throws ServiceException {
        ExpressOrderVO order = queryOrderByOrderSerialNo(updateCommodityWeightAndPriceParameter.getOrderNo());
        if (order == null) {
            throw new RuntimeException("无效订单，订单不存在，订单流水号：" + updateCommodityWeightAndPriceParameter.getOrderNo());
        }
        expressOrderDAO.updateCommodityWeightAndPrice(updateCommodityWeightAndPriceParameter);
    }

    /**
     * 根据订单id，查询订单
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public ExpressOrderVO queryOrderById(Long id) throws ServiceException {
        ExpressOrderDO orderDO = expressOrderDAO.queryById(id);
        return expressOrderMapper.d2v(orderDO);
    }

    /**
     * 查询订单详情
     *
     * @param id the id
     * @return
     * @throws ServiceException
     */
    @Override
    public ExpressOrderVO getOrderDetailInfo(Long id) throws ServiceException {
        ExpressOrderDO orderDO = expressOrderDAO.queryById(id);
        if (orderDO == null) {
            throw new ServiceException(ERROR_FACTORY.notFindExpressOrderDetail(id));
        }
        List<ExpressOrderItemDO> itemDOS = expressOrderItemDAO.getItemByOrderNo(orderDO.getOrderNo());
        return expressOrderMapper.do2voDetails(orderDO, itemDOS);
    }

    /**
     * 根据用户ID查询用户订单
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    @Override
    public Collection<ExpressOrderVO> queryOrderByUserId(QueryByUserIdQuery query) throws ServiceException {
        List<ExpressOrderDO> orderDOs = expressOrderDAO.queryByUserId(query);
        return expressOrderMapper.d2vs(orderDOs);
    }

    /**
     * 根据订单流水号查询订单
     *
     * @param orderSerialNo
     * @return
     * @throws ServiceException
     */
    @Override
    public ExpressOrderVO queryOrderByOrderSerialNo(String orderSerialNo) throws ServiceException {
        if (StringUtils.isBlank(orderSerialNo)) {
            throw new RuntimeException("订单流水号不能为空");
        }
        ExpressOrderDO order = expressOrderDAO.queryByOrderSerialNo(orderSerialNo);
        return expressOrderMapper.d2v(order);
    }

    /**
     * 分页查询订单
     *
     * @param orderQuery 订单查询条件
     * @return
     * @throws ServiceException
     */
    @Override
    public PageList<ExpressOrderVO> pageQueryExpressOrders(FindPageQuery orderQuery) throws ServiceException {
        PageList<ExpressOrderDO> list = expressOrderDAO.findPage(orderQuery);
        Collection<ExpressOrderVO> result = expressOrderMapper.d2vs(list.getData());
        return PageUtils.createPageList(result, list.getPaginator());
    }

    /**
     * 订单导出
     *
     * @param query        查询条件
     * @param templatePath excel模板路径
     * @return
     * @throws ServiceException
     */
    @Override
    public ResponseEntity<byte[]> export(GetAllByParsQuery query, String templatePath) throws ServiceException {
        List<ExpressOrderDO> list = expressOrderDAO.getAllByPars(query);
        Collection<ExpressOrderVO> result = expressOrderMapper.d2vs(list);
        String fileName = "订单信息导出" + System.currentTimeMillis() + ".xlsx";
        return getExcel(templatePath, fileName, result);
    }
}
