package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.jungle.utils.common.UUID;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.QRCodeException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.QRCodeBatchMapper;
import com.zeh.wms.biz.model.QRCodeBatchVO;
import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.QRCodeBatchService;
import com.zeh.wms.biz.service.QRCodeService;
import com.zeh.wms.dal.daointerface.QrcodeBatchDAO;
import com.zeh.wms.dal.dataobject.QrcodeBatchDO;
import com.zeh.wms.dal.operation.qrcodebatch.QueryByPageQuery;

/**
 * @author allen
 * @create $ ID: QRCodeBatchServiceImpl, 18/2/10 20:21 allen Exp $
 * @since 1.0.0
 */
@Service
public class QRCodeBatchServiceImpl implements QRCodeBatchService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger                logger        = LoggerFactory.getLogger(AgentServiceImpl.class);
    /** 代理商数据库访问组件 */
    @Resource
    private QrcodeBatchDAO               qrcodeBatchDAO;
    /** mapper */
    @Resource
    private QRCodeBatchMapper            mapper;
    @Resource
    private QRCodeService                qrCodeService;

    /**
     * 创建二维码批次记录
     *
     * @param qrCodeBatch 二维码批次
     * @throws ServiceException 创建二维码批次异常
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createQRCodeBatch(QRCodeBatchVO qrCodeBatch) throws ServiceException {
        if (qrCodeBatch == null) {
            throw new ServiceException(ERROR_FACTORY.createQRCodeBatchError());
        }
        qrCodeBatch.setBatchSerial(UUID.generateRandomUUID());
        qrCodeBatch.setAmount(qrCodeBatch.getAmount());
        qrCodeBatch.setState(StateEnum.N);
        QrcodeBatchDO commodityDO = mapper.v2d(qrCodeBatch);
        long id = qrcodeBatchDAO.insert(commodityDO);
        qrCodeBatch.setId(id);

        for (int i = 0; i < qrCodeBatch.getAmount(); i++) {
            QrcodeVO qrCode = createQrCodeVO(qrCodeBatch);
            qrCodeService.createQRCode(qrCode);
        }
    }

    /**
     * 生成二维码数据
     *
     * @param qrCodeBatch 二维码批次
     * @return
     */
    private QrcodeVO createQrCodeVO(QRCodeBatchVO qrCodeBatch) throws ServiceException {
        QrcodeVO qrCode = new QrcodeVO();
        qrCode.setBatchId(qrCodeBatch.getId());
        qrCode.setCommodityId(qrCodeBatch.getCommodityId());
        qrCode.setCreateBy(qrCodeBatch.getCreateBy());
        qrCode.setModifyBy(qrCodeBatch.getModifyBy());
        qrCode.setSerialNo(UUID.generateRandomUUID());
        qrCode.setState(StateEnum.N);
        String content = qrCodeService.generateQRCodeContent(qrCode);
        try {
            String data = qrCodeService.encode(content, 256, 256);
            qrCode.setData(data);
            return qrCode;
        } catch (QRCodeException e) {
            throw new ServiceException(e.getError(), e);
        }
    }

    /**
     * 根据二维码批次编号查询二维码批次信息
     *
     * @param serialId 二维码批次编号
     * @return 二维码批次信息
     * @throws ServiceException 二维码批次查询异常
     */
    @Override
    public Collection<QRCodeBatchVO> findQRCodeBatchBySerialId(String serialId) throws ServiceException {
        if (StringUtils.isBlank(serialId)) {
            throw new ServiceException(ERROR_FACTORY.queryQRCodeBatchError());
        }
        List<QrcodeBatchDO> dos = qrcodeBatchDAO.queryAllQRcodesByBatchSerial(serialId);
        return mapper.d2vs(dos);
    }

    /**
     * 根据二维码批次ID查询二维码批次信息
     *
     * @param id 二维码批次ID
     * @return 二维码批次信息
     * @throws ServiceException 二维码批次查询异常
     */
    @Override
    public QRCodeBatchVO findQRCodeBatchById(long id) throws ServiceException {
        QrcodeBatchDO qrcodeBatchDO = qrcodeBatchDAO.queryById(id);
        return mapper.d2v(qrcodeBatchDO);
    }

    /**
     * 分页查询二维码批次信息
     *
     * @param qrCodeBatch 二维码批次查询条件
     * @param currentPage 查询起始页
     * @param size        每页数量
     * @return 二维码批次信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<QRCodeBatchVO> pageQueryQRCodeBatchs(QRCodeBatchVO qrCodeBatch, int currentPage, int size) throws ServiceException {
        if (qrCodeBatch == null) {
            throw new ServiceException(ERROR_FACTORY.queryQRCodeBatchError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setState(qrCodeBatch.getState() == null ? null : qrCodeBatch.getState().getCode());
        query.setBatchSerial(qrCodeBatch.getBatchSerial());
        query.setCommodityId(qrCodeBatch.getCommodityId());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<QrcodeBatchDO> ret = qrcodeBatchDAO.queryByPage(query);
        Collection<QRCodeBatchVO> commodities = mapper.d2vs(ret.getData());
        return PageUtils.createPageList(commodities, ret.getPaginator());
    }

    /**
     * 更新二维码批次启用、禁用状态
     *
     * @param id       二维码批次ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 二维码批次状态更新异常
     */
    @Override
    public void updateQRCodeBatchState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        QRCodeBatchVO qrCodeBatch = new QRCodeBatchVO();
        qrCodeBatch.setState(enabled);
        qrCodeBatch.setId(id);
        qrCodeBatch.setModifyBy(modifyBy);
        QrcodeBatchDO qrcodeBatchDO = mapper.v2d(qrCodeBatch);
        qrcodeBatchDAO.update(qrcodeBatchDO);
    }
}