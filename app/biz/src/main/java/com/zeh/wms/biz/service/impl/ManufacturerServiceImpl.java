package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ManufacturerMapper;
import com.zeh.wms.biz.model.ManufacturerVO;
import com.zeh.wms.biz.service.ManufacturerService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.ManufacturerDAO;
import com.zeh.wms.dal.dataobject.ManufacturerDO;
import com.zeh.wms.dal.operation.manufacturer.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: ManufacturerServiceImpl, 18/2/6 16:53 allen Exp $
 * @since 1.0.0
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    /** 厂商DAO */
    @Resource
    private ManufacturerDAO              manufacturerDAO;
    /** mapper */
    @Resource
    private ManufacturerMapper           mapper;

    /**
     * 创建厂商
     *
     * @param manufacturer      厂商
     * @throws ServiceException 厂商创建异常
     */
    @Override
    public void createManufacturer(ManufacturerVO manufacturer) throws ServiceException {
        if (manufacturer == null) {
            throw new ServiceException(ERROR_FACTORY.createManufacturerError());
        }
        manufacturer.setCode(CodeGenerator.generateManufacturerCode());
        ManufacturerDO manufacturerDO = mapper.v2d(manufacturer);
        manufacturerDAO.insert(manufacturerDO);
    }

    /**
     * 更新厂商信息
     *
     * @param manufacturer      厂商
     * @throws ServiceException 厂商更新异常
     */
    @Override
    public void updateManufacturer(ManufacturerVO manufacturer) throws ServiceException {
        if (manufacturer == null || manufacturer.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateManufacturerError());
        }
        ManufacturerDO manufacturerDO = manufacturerDAO.queryById(manufacturer.getId());
        manufacturerDO.setName(StringUtils.isNotBlank(manufacturer.getName()) ? manufacturer.getName() : manufacturerDO.getName());
        manufacturerDO.setSettleType(manufacturer.getSettleType() != null ? String.valueOf(manufacturer.getSettleType().getCode()) : manufacturerDO.getSettleType());
        manufacturerDO.setExpress(manufacturer.getExpress() != null ? manufacturer.getExpress().getCode() : manufacturerDO.getExpress());
        manufacturerDO.setModifyBy(manufacturer.getModifyBy());
        manufacturerDAO.update(manufacturerDO);
    }

    /**
     * 根据厂商编码查询厂商信息
     *
     * @param code 厂商编码
     * @return 厂商
     * @throws ServiceException 厂商查询异常
     */
    @Override
    public ManufacturerVO findManufacturerByCode(String code) throws ServiceException {
        if (StringUtils.isBlank(code)) {
            throw new ServiceException(ERROR_FACTORY.queryManufacturerError());
        }
        ManufacturerDO manufacturer = manufacturerDAO.queryByCode(code);
        return mapper.d2v(manufacturer);
    }

    /**
     * 根据厂商ID查询厂商信息
     *
     * @param id 厂商ID
     * @return 厂商
     * @throws ServiceException 厂商查询异常
     */
    @Override
    public ManufacturerVO findManufacturerById(long id) throws ServiceException {
        ManufacturerDO manufacturer = manufacturerDAO.queryById(id);
        return mapper.d2v(manufacturer);
    }

    /**
     * 分页查询厂商信息
     *
     * @param manufacturer  厂商查询条件
     * @param currentPage   当前页
     * @param size          每页数据量
     * @return              厂商查询结果
     * @throws ServiceException  分页查询异常
     */
    @Override
    public PageList<ManufacturerVO> pageQueryManufacturers(ManufacturerVO manufacturer, int currentPage, int size) throws ServiceException {
        if (manufacturer == null) {
            throw new ServiceException(ERROR_FACTORY.queryManufacturerError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setCode(manufacturer.getCode());
        query.setExpress(manufacturer.getExpress() == null ? null : manufacturer.getExpress().getCode());
        query.setName(manufacturer.getName());
        query.setSettleType(manufacturer.getSettleType() == null ? null : String.valueOf(manufacturer.getSettleType().getCode()));
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<ManufacturerDO> ret = manufacturerDAO.queryByPage(query);
        Collection<ManufacturerVO> manufacturers = mapper.d2vs(ret.getData());
        return PageUtils.createPageList(manufacturers, ret.getPaginator());
    }

    /**
     * 根据厂商ID查询厂商信息
     *
     * @return 厂商
     * @throws ServiceException 厂商查询异常
     */
    @Override
    public List<ManufacturerVO> getAll() throws ServiceException {
        List<ManufacturerDO> manufacturer = manufacturerDAO.getAllEnabled();
        return mapper.do2vos(manufacturer);
    }

    /**
     * 删除厂商信息，不能恢复
     *
     * @param id 厂商ID
     * @throws ServiceException 删除异常
     */
    @Override
    public void deleteManufacturer(long id) throws ServiceException {
        manufacturerDAO.delete(id);
    }
}