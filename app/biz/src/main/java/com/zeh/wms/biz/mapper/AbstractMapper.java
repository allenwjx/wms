package com.zeh.wms.biz.mapper;

import com.zeh.wms.biz.model.enums.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author allen
 * @create $ ID: AbstractMapper, 18/2/6 17:11 allen Exp $
 * @since 1.0.0
 */
public interface AbstractMapper {
    /**
     * SettleTypeEnum to code
     * 
     * @param type
     * @return
     */
    default String settleTypeEnumToCode(SettleTypeEnum type) {
        return type.getCode();
    }

    /**
     * Code to SettleTypeEnum
     * 
     * @param code
     * @return
     */
    default SettleTypeEnum codeToSettleTypeEnum(String code) {
        return SettleTypeEnum.getEnumByCode(code);
    }

    /**
     * AddressTypeEnum to code
     *
     * @param type
     * @return
     */
    default String addressTypeEnumToCode(AddressTypeEnum type) {
        return type.getCode();
    }

    /**
     * Code to AddressTypeEnum
     *
     * @param code
     * @return
     */
    default AddressTypeEnum codeToAddressTypeEnum(String code) {
        return AddressTypeEnum.getEnumByCode(code);
    }

    /**
     * ExpressOrderStateEnum to code
     *
     * @param type
     * @return
     */
    default String expressOrderStateEnumToCode(ExpressOrderStateEnum type) {
        return type.getCode();
    }

    /**
     * Code to ExpressOrderStateEnum
     *
     * @param code
     * @return
     */
    default ExpressOrderStateEnum codeToExpressOrderStateEnum(String code) {
        return ExpressOrderStateEnum.getEnumByCode(code);
    }

    /**
     * ExpressTypeEnum to code
     *
     * @param type
     * @return
     */
    default String expressTypeEnumToCode(ExpressTypeEnum type) {
        return type.getCode();
    }

    /**
     * Code to ExpressTypeEnum
     *
     * @param code
     * @return
     */
    default ExpressTypeEnum codeToExpressTypeEnum(String code) {
        return ExpressTypeEnum.getEnumByCode(code);
    }

    /**
     * PaymentChannelEnum to code
     *
     * @param type
     * @return
     */
    default String paymentChannelEnumToCode(PaymentChannelEnum type) {
        return type.getCode();
    }

    /**
     * Code to PaymentChannelEnum
     *
     * @param code
     * @return
     */
    default PaymentChannelEnum codeToPaymentChannelEnum(String code) {
        return PaymentChannelEnum.getEnumByCode(code);
    }

    /**
     * PaymentStateEnum to code
     *
     * @param type
     * @return
     */
    default String paymentStateEnumToCode(PaymentStateEnum type) {
        return type.getCode();
    }

    /**
     * Code to PaymentStateEnum
     *
     * @param code
     * @return
     */
    default PaymentStateEnum codeToPaymentStateEnum(String code) {
        return PaymentStateEnum.getEnumByCode(code);
    }

    /**
     * StateEnum to code
     *
     * @param type
     * @return
     */
    default int stateEnumToCode(StateEnum type) {
        return type.getCode();
    }

    /**
     * Code to StateEnum
     *
     * @param code
     * @return
     */
    default StateEnum codeToStateEnum(int code) {
        return StateEnum.getEnumByCode(code);
    }

    /**
     * UserLinkTypeEnum to code
     *
     * @param type
     * @return
     */
    default String userLinkTypeEnumToCode(UserLinkTypeEnum type) {
        return type.getCode();
    }

    /**
     * Code to UserLinkTypeEnum
     *
     * @param code
     * @return
     */
    default UserLinkTypeEnum codeToUserLinkTypeEnum(String code) {
        return UserLinkTypeEnum.getEnumByCode(code);
    }

    /**
     * UserTypeEnum to code
     *
     * @param type
     * @return
     */
    default String userTypeEnumToCode(UserTypeEnum type) {
        return type.getCode();
    }

    /**
     * Code to UserTypeEnum
     *
     * @param code
     * @return
     */
    default UserTypeEnum codeToUserTypeEnum(String code) {
        return UserTypeEnum.getEnumByCode(code);
    }

    /**
     * As string string.
     *
     * @param date the date
     * @return the string
     */
    default String asString(Date date) {
        return date != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) : null;
    }

    /**
     * As date date.
     *
     * @param date the date
     * @return the date
     */
    default Date asDate(String date) {
        try {
            if (StringUtils.isBlank(date)) {
                return null;
            }
            if (date.length() == 10) {
                return new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } else if (date.length() == 19) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            } else {
                return DateTime.parse(date).toDate();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("error date format, date format must be 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm:ss'");
        }
    }

}
