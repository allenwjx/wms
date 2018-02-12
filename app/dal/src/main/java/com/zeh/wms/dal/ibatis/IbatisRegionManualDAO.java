package com.zeh.wms.dal.ibatis;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageQueryUtils;
import com.zeh.wms.dal.daointerface.RegionManualDAO;
import com.zeh.wms.dal.operation.region.QueryByPageQuery;
import com.zeh.wms.dal.operation.region.QueryByPageResult;
import org.springframework.stereotype.Service;

/**
 * @author allen
 * @create $ ID: IbatisRegionManualDAO, 18/2/9 16:48 allen Exp $
 * @since 1.0.0
 */
@Service
public class IbatisRegionManualDAO extends SqlMapClientDaoSupport implements RegionManualDAO {
    @Override
    public PageList<QueryByPageResult> queryByPage(QueryByPageQuery param) throws DataAccessException {
        return PageQueryUtils.pageQuery(getSqlMapClientTemplate(), "wms.Region.queryByPage", param);
    }
}