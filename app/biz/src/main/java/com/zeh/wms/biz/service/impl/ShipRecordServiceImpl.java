package com.zeh.wms.biz.service.impl;

import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ShipRecordMapper;
import com.zeh.wms.biz.model.*;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.*;
import com.zeh.wms.dal.daointerface.ShipRecordDAO;
import com.zeh.wms.dal.dataobject.ShipRecordDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author allen
 * @create $ ID: ShipRecordServiceImpl, 18/2/25 20:11 allen Exp $
 * @since 1.0.0
 */
@Service
public class ShipRecordServiceImpl implements ShipRecordService {
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();
    @Resource
    private ShipRecordDAO                shipRecordDAO;
    @Resource
    private ShipRecordMapper             mapper;
    @Resource
    private AgentService                 agentService;
    @Resource
    private QRCodeService                qrCodeService;
    @Resource
    private CommodityService             commodityService;
    @Resource
    private ManufacturerService          manufacturerService;

    /**
     * 代理人，商品，二维码邦定
     *
     * @param shipRecord
     * @throws ServiceException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bind(ShipRecordVO shipRecord) throws ServiceException {
        AgentVO agent = agentService.findAgentById(shipRecord.getAgentId());
        if (agent == null) {
            throw new ServiceException(ERROR_FACTORY.invalidAgent());
        }
        CommodityVO commodity = commodityService.findCommodityById(shipRecord.getCommodityId());
        if (commodity == null) {
            throw new ServiceException(ERROR_FACTORY.invalidCommodity());
        }
        QrcodeVO qrcode = qrCodeService.queryBySerialNo(shipRecord.getQrcodeNo());
        if (qrcode == null) {
            throw new ServiceException(ERROR_FACTORY.invalidQRCode());
        }
        if (qrcode.getState().equals(StateEnum.Y)) {
            throw new ServiceException(ERROR_FACTORY.qrCodeUsed());
        }
        // 生成邦定记录
        ShipRecordDO shipRecordDO = mapper.vo2do(shipRecord);
        shipRecordDAO.insert(shipRecordDO);
        qrCodeService.updateQRCodeState(qrcode.getId(), shipRecord.getModifyBy(), StateEnum.Y);
    }

    /**
     * 根据二维码编号，查询代理人产品邦定详情
     *
     * @param qrCodeSerialNo
     * @return
     * @throws ServiceException
     */
    @Override
    public ShipRecordDetails retrieveShipRecordDetails(String qrCodeSerialNo) throws ServiceException {
        ShipRecordDO shipRecordDO = shipRecordDAO.queryByQRCode(qrCodeSerialNo);
        if (shipRecordDO == null) {
            throw new ServiceException(ERROR_FACTORY.shipRecordNotFound());
        }
        AgentVO agent = agentService.findAgentById(shipRecordDO.getAgentId());
        if (agent == null) {
            throw new ServiceException(ERROR_FACTORY.invalidAgent());
        }
        CommodityVO commodity = commodityService.findCommodityById(shipRecordDO.getCommodityId());
        if (commodity == null) {
            throw new ServiceException(ERROR_FACTORY.invalidCommodity());
        }
        ManufacturerVO manufacturer = manufacturerService.findManufacturerById(commodity.getManufacturerId());

        ShipRecordDetails details = new ShipRecordDetails();
        details.setAgentAddress(agent.getAddress());
        details.setAgentCode(agent.getCode());
        details.setAgentExternalCode(agent.getExternalCode());
        details.setAgentMobile(agent.getMobile());
        details.setAgentName(agent.getName());
        details.setCommodityDescription(commodity.getDescription());
        details.setCommodityName(commodity.getName());
        details.setCommodityPrice(commodity.getPrice());
        details.setCommodityUnit(commodity.getUnit());
        details.setCommodityWeight(commodity.getWeight());
        details.setManufacturer(manufacturer != null ? manufacturer.getName() : "");
        return details;
    }

    /**
     * 根据二维码编号删除邦定记录
     *
     * @param qrCodeSerialNo
     * @throws ServiceException
     */
    @Override
    public void deleteShipRecordByQRCode(String qrCodeSerialNo) throws ServiceException {

    }
}