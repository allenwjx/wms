package com.zeh.wms.biz.service;

import java.util.Collection;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.QRCodeBatchVO;
import com.zeh.wms.biz.model.enums.StateEnum;

/**
 * @author allen
 * @create $ ID: QRCodeBatchService, 18/2/10 20:16 allen Exp $
 * @since 1.0.0
 */
public interface QRCodeBatchService {

    /**
     * 创建二维码批次记录
     *
     * @param qrCodeBatch 二维码批次
     * @throws ServiceException 创建二维码批次异常
     */
    void createQRCodeBatch(QRCodeBatchVO qrCodeBatch) throws ServiceException;

    /**
     * 根据二维码批次编号查询二维码批次信息
     *
     * @param serialId 二维码批次编号
     * @return 二维码批次信息
     * @throws ServiceException 二维码批次查询异常
     */
    Collection<QRCodeBatchVO> findQRCodeBatchBySerialId(String serialId) throws ServiceException;

    /**
     * 根据二维码批次ID查询二维码批次信息
     *
     * @param id 二维码批次ID
     * @return 二维码批次信息
     * @throws ServiceException 二维码批次查询异常
     */
    QRCodeBatchVO findQRCodeBatchById(long id) throws ServiceException;

    /**
     * 分页查询二维码批次信息
     * @param qrCodeBatch 二维码批次查询条件
     * @param currentPage 查询起始页
     * @param size 每页数量
     * @return 二维码批次信息
     * @throws ServiceException 分页查询异常
     */
    PageList<QRCodeBatchVO> pageQueryQRCodeBatchs(QRCodeBatchVO qrCodeBatch, int currentPage, int size) throws ServiceException;

    /**
     * 更新二维码批次启用、禁用状态
     *
     * @param id       二维码批次ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 二维码批次状态更新异常
     */
    void updateQRCodeBatchState(long id, String modifyBy, StateEnum enabled) throws ServiceException;
}