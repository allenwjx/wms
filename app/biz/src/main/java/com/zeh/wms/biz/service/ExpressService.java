package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.ExpressVO;
import com.zeh.wms.dal.operation.expres.QueryByPageQuery;

import java.util.List;

/**
 * @author hzy24985
 * @version $Id: ExpressService, v 0.1 2018/3/10 17:01 hzy24985 Exp $
 */
public interface ExpressService {
    List<ExpressVO> getAllExpress() throws ServiceException;

    PageList<ExpressVO> pageQueryExpresses(QueryByPageQuery query) throws ServiceException;

    void addExpress(ExpressVO vo) throws ServiceException;

    void updateExpress(ExpressVO vo) throws ServiceException;

    void deleteExpress(Long id) throws ServiceException;
}
