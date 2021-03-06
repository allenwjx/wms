
/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zeh.wms.dal.operation.expressorder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


import java.io.*;
import java.net.*;
import java.util.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.zeh.jungle.dal.paginator.PageQuery;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageQueryUtils;
/**
 * database table: express_order
 * database table comments: ExpressOrder
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class UpdateStatusParameter  implements java.io.Serializable {
	private static final long serialVersionUID = -5216457518046898601L;
	
	/** 订单状态;WATI_PAY（待支付-线上支付）；WAIT_PICKUP（待取件）；WAIT_SEND（待发货）；SENDED（已发货）；CANCEL（订单取消）; */
	private String status;
	/** 修改人 */
	private String modifyBy;
	/** 快递单号 */
	private String orderNo;
	
	public UpdateStatusParameter() {
	}
	
	public UpdateStatusParameter(String status ,String modifyBy ,String orderNo ) {
		this.status = status;
		this.modifyBy = modifyBy;
		this.orderNo = orderNo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
