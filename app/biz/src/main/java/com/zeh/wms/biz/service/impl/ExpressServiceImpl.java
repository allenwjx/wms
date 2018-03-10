package com.zeh.wms.biz.service.impl;

import com.google.common.collect.Lists;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ExpressMapper;
import com.zeh.wms.biz.model.ExpressVO;
import com.zeh.wms.biz.service.ExpressService;
import com.zeh.wms.dal.daointerface.ExpresDAO;
import com.zeh.wms.dal.dataobject.ExpresDO;
import com.zeh.wms.dal.operation.expres.QueryByPageQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Express service.
 *
 * @author hzy24985
 * @version $Id : ExpressServiceImpl, v 0.1 2018/3/10 17:06 hzy24985 Exp $
 */
@Service
public class ExpressServiceImpl extends AbstractService implements ExpressService {
    /**
     * The Express mapper.
     */
    @Resource
    private ExpressMapper expressMapper;

    /**
     * The Expres dao.
     */
    @Resource
    private ExpresDAO     expresDAO;

    /**
     * Gets all express.
     *
     * @return the all express
     * @throws ServiceException the service exception
     */
    @Override
    public List<ExpressVO> getAllExpress() throws ServiceException {
        List<ExpresDO> dolist = expresDAO.queryAll();
        return expressMapper.dos2vos(dolist);
    }

    /**
     * Page query expresses page list.
     *
     * @param query the query
     * @return the page list
     * @throws ServiceException the service exception
     */
    @Override
    public PageList<ExpressVO> pageQueryExpresses(QueryByPageQuery query) throws ServiceException {
        PageList<ExpresDO> ret = expresDAO.queryByPage(query);
        List<ExpressVO> expressVOS = expressMapper.dos2vos(Lists.newArrayList(ret.getData()));
        return PageUtils.createPageList(expressVOS, ret.getPaginator());
    }

    /**
     * Add express.
     *
     * @param vo the vo
     * @throws ServiceException the service exception
     */
    @Override
    public void addExpress(ExpressVO vo) throws ServiceException {
        ExpresDO expresDO = expressMapper.vo2do(vo);
        checkInsert(expresDAO.insert(expresDO), "快递公司");
    }

    /**
     * Update express.
     *
     * @param vo the vo
     * @throws ServiceException the service exception
     */
    @Override
    public void updateExpress(ExpressVO vo) throws ServiceException {
        ExpresDO expresDO = expressMapper.vo2do(vo);
        checkUpdate(expresDAO.update(expresDO), "快递公司");
    }

    /**
     * Delete express.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteExpress(Long id) throws ServiceException {
        if (id == null || id <= 0) {
            return;
        }
        expresDAO.delete(id);
    }
}
