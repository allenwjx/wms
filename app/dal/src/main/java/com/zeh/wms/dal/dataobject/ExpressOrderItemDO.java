/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zeh.wms.dal.dataobject;

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
 * ExpressOrderItemDO
 * database table: express_order_item
 * database table comments: ExpressOrderItem
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class ExpressOrderItemDO implements java.io.Serializable {
	private static final long serialVersionUID = -5216457518046898601L;
	
	/**
	 * id 		db_column: id 
	 */
	private long id;
	/**
	 * 关联快递单号 		db_column: order_no 
	 */
	private String orderNo;
	/**
	 * 商品名称 		db_column: item_name 
	 */
	private String itemName;
	/**
	 * 商品code 		db_column: item_code 
	 */
	private String itemCode;
	/**
	 * agent用户类型的下单：agent电话号码；大客户类型的下单：厂商编码（授权码）散客：空值 		db_column: relation_code 
	 */
	private String relationCode;
	/**
	 * 商品数量 		db_column: quantity 
	 */
	private int quantity;
	/**
	 * 商品单位 		db_column: unit 
	 */
	private String unit;
	/**
	 * 单价，单位分 		db_column: unit_price 
	 */
	private int unitPrice;
	/**
	 * 商品重量（单件），单位：克 		db_column: unit_weight 
	 */
	private int unitWeight;
	/**
	 * 商品总重量，单位：克 		db_column: total_weight 
	 */
	private int totalWeight;
	/**
	 * 商品快递费总价，单位：分 		db_column: total_price 
	 */
	private int totalPrice;
	/**
	 * 创建时间 		db_column: gmt_create 
	 */
	private Date gmtCreate;
	/**
	 * 修改时间 		db_column: gmt_modified 
	 */
	private Date gmtModified;
	/**
	 * 创建人 		db_column: create_by 
	 */
	private String createBy;
	/**
	 * 修改人 		db_column: modify_by 
	 */
	private String modifyBy;

	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getOrderNo() {
		return this.orderNo;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getItemCode() {
		return this.itemCode;
	}
	
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	
	public String getRelationCode() {
		return this.relationCode;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUnitWeight(int unitWeight) {
		this.unitWeight = unitWeight;
	}
	
	public int getUnitWeight() {
		return this.unitWeight;
	}
	
	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}
	
	public int getTotalWeight() {
		return this.totalWeight;
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public int getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	public Date getGmtModified() {
		return this.gmtModified;
	}
	
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public String getCreateBy() {
		return this.createBy;
	}
	
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	public String getModifyBy() {
		return this.modifyBy;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("OrderNo",getOrderNo())
			.append("ItemName",getItemName())
			.append("ItemCode",getItemCode())
			.append("RelationCode",getRelationCode())
			.append("Quantity",getQuantity())
			.append("Unit",getUnit())
			.append("UnitPrice",getUnitPrice())
			.append("UnitWeight",getUnitWeight())
			.append("TotalWeight",getTotalWeight())
			.append("TotalPrice",getTotalPrice())
			.append("GmtCreate",getGmtCreate())
			.append("GmtModified",getGmtModified())
			.append("CreateBy",getCreateBy())
			.append("ModifyBy",getModifyBy())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(obj instanceof ExpressOrderItemDO == false) return false;
		ExpressOrderItemDO other = (ExpressOrderItemDO)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

