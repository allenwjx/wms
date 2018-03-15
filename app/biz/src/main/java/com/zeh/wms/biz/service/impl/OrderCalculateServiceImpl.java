package com.zeh.wms.biz.service.impl;

import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.FreightVO;
import com.zeh.wms.biz.model.calate.OrderCalculatePriceVO;
import com.zeh.wms.biz.service.FreightService;
import com.zeh.wms.biz.service.OrderCalculateService;
import com.zeh.wms.biz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author hzy24985
 * @version $Id: OrderCalculateServiceImpl, v 0.1 2018/3/14 21:39 hzy24985 Exp $
 */
@Service
public class OrderCalculateServiceImpl extends AbstractService implements OrderCalculateService {

    @Resource
    private UserService    userService;
    @Resource
    private FreightService freightService;

    @Override
    public OrderCalculatePriceVO inventoryOrderPrice(String expressCode, String targetProvince, int totalWeight, long userId) throws ServiceException {

        OrderCalculatePriceVO result = new OrderCalculatePriceVO();
        // 计算价格.
        BigDecimal discount = userService.getDiscount(userId, expressCode);
        //获取运价信息
        FreightVO freightVO = freightService.findByProvinceName(targetProvince);
        if (freightVO == null) {
            throw new ServiceException(ERROR_FACTORY.notFindFreightConfig(targetProvince));
        }

        result.setFirstPrice(getPrice(freightVO.getFirstOriginalPrice(), discount).intValue());
        result.setAdditionalPrice(getPrice(freightVO.getAdditionalOriginalPrice(), discount).intValue());

        int total = getPrice(freightVO.getFirstOriginalPrice(), discount).intValue();
        if (totalWeight <= freightVO.getFirstWeight()) {
            result.setTotal(total);
            return result;
        }

        int additionalPrice = new BigDecimal(totalWeight).divide(new BigDecimal(500), BigDecimal.ROUND_UP).setScale(0, BigDecimal.ROUND_UP)
            .multiply(getPrice(freightVO.getAdditionalOriginalPrice(), discount)).intValue();
        total += additionalPrice;
        result.setTotal(total);
        return result;
    }

    private BigDecimal getPrice(int price, BigDecimal discount) {
        return new BigDecimal(price).multiply(discount).setScale(0, BigDecimal.ROUND_UP);
    }
}
