package com.zeh.wms.biz.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import com.zeh.wms.biz.model.QRCodeBatchVO;
import com.zeh.wms.dal.dataobject.QrcodeBatchDO;

/**
 * @author allen
 * @create $ ID: QRCodeBatchMapper, 18/2/10 20:14 allen Exp $
 * @since 1.0.0
 */
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface QRCodeBatchMapper extends AbstractMapper {
    QRCodeBatchVO d2v(QrcodeBatchDO dataObject);

    QrcodeBatchDO v2d(QRCodeBatchVO dataObject);

    Collection<QRCodeBatchVO> d2vs(Collection<QrcodeBatchDO> dataObjects);

    Collection<QrcodeBatchDO> v2ds(Collection<QRCodeBatchVO> dataObjects);
}
