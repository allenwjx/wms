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
 * ShipRecordDO
 * database table: ship_record
 * database table comments: ShipRecord
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class ShipRecordDO implements java.io.Serializable {
	private static final long serialVersionUID = -5216457518046898601L;
	
	/**
	 * id 		db_column: id 
	 */
	private long id;
	/**
	 * 用户ID（代理商） 		db_column: agent_id 
	 */
	private long agentId;
	/**
	 * 商品ID 		db_column: commodity_id 
	 */
	private long commodityId;
	/**
	 * 二维码编号 		db_column: qrcode_no 
	 */
	private String qrcodeNo;
	/**
	 * gmtCreate 		db_column: gmt_create 
	 */
	private Date gmtCreate;
	/**
	 * gmtModified 		db_column: gmt_modified 
	 */
	private Date gmtModified;
	/**
	 * createBy 		db_column: create_by 
	 */
	private String createBy;
	/**
	 * modifiedBy 		db_column: modified_by 
	 */
	private String modifiedBy;

	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}
	
	public long getAgentId() {
		return this.agentId;
	}
	
	public void setCommodityId(long commodityId) {
		this.commodityId = commodityId;
	}
	
	public long getCommodityId() {
		return this.commodityId;
	}
	
	public void setQrcodeNo(String qrcodeNo) {
		this.qrcodeNo = qrcodeNo;
	}
	
	public String getQrcodeNo() {
		return this.qrcodeNo;
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
	
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("AgentId",getAgentId())
			.append("CommodityId",getCommodityId())
			.append("QrcodeNo",getQrcodeNo())
			.append("GmtCreate",getGmtCreate())
			.append("GmtModified",getGmtModified())
			.append("CreateBy",getCreateBy())
			.append("ModifiedBy",getModifiedBy())
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
		if(obj instanceof ShipRecordDO == false) return false;
		ShipRecordDO other = (ShipRecordDO)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

