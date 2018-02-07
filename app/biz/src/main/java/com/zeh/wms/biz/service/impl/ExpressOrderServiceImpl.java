package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ExpressOrderMapper;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.dal.daointerface.ExpressOrderDAO;
import com.zeh.wms.dal.dataobject.ExpressOrderDO;
import com.zeh.wms.dal.operation.expressorder.FindPageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author hzy24985
 * @version $Id: ExpressOrderServiceImpl, v 0.1 2018/2/7 21:48 hzy24985 Exp $
 */
@Service
public class ExpressOrderServiceImpl implements ExpressOrderService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger logger = LoggerFactory.getLogger(ExpressOrderServiceImpl.class);

    @Resource
    private ExpressOrderDAO expressOrderDAO;

    @Resource
    private ExpressOrderMapper expressOrderMapper;
    
    @Override
    public PageList<ExpressOrderVO> pageQueryExpressOrders(FindPageQuery orderQuery) throws ServiceException {
        PageList<ExpressOrderDO> list = expressOrderDAO.findPage(orderQuery);
        Collection<ExpressOrderVO> result = expressOrderMapper.d2vs(list.getData());

        return PageUtils.createPageList(result, list.getPaginator());
    }
}
