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
 * ManufacturerDO
 * database table: manufacturer
 * database table comments: Manufacturer
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class ManufacturerDO implements java.io.Serializable {
	private static final long serialVersionUID = -5216457518046898601L;
	
	/**
	 * id 		db_column: id 
	 */
	private long id;
	/**
	 * serialId 		db_column: serial_id 
	 */
	private String serialId;
	/**
	 * name 		db_column: name 
	 */
	private String name;

	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	
	public String getSerialId() {
		return this.serialId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("SerialId",getSerialId())
			.append("Name",getName())
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
		if(obj instanceof ManufacturerDO == false) return false;
		ManufacturerDO other = (ManufacturerDO)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

