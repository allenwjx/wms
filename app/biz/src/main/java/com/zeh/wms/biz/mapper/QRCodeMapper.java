package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.QrcodeVO;
import com.zeh.wms.dal.dataobject.QrcodeDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface QRCodeMapper extends AbstractMapper {
    QrcodeVO d2v(QrcodeDO dataObject);

    QrcodeDO v2d(QrcodeVO dataObject);

    Collection<QrcodeVO> d2vs(Collection<QrcodeDO> dataObjects);

    Collection<QrcodeDO> v2ds(Collection<QrcodeVO> dataObjects);
}
