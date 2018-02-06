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
 * RoleAuthorizationLinkDO
 * database table: role_authorization_link
 * database table comments: RoleAuthorizationLink
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class RoleAuthorizationLinkDO implements java.io.Serializable {
	private static final long serialVersionUID = -5216457518046898601L;
	
	/**
	 * id 		db_column: id 
	 */
	private long id;
	/**
	 * 角色ID 		db_column: role_id 
	 */
	private long roleId;
	/**
	 * 权限ID 		db_column: auth_id 
	 */
	private long authId;

	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	public long getRoleId() {
		return this.roleId;
	}
	
	public void setAuthId(long authId) {
		this.authId = authId;
	}
	
	public long getAuthId() {
		return this.authId;
	}

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("RoleId",getRoleId())
			.append("AuthId",getAuthId())
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
		if(obj instanceof RoleAuthorizationLinkDO == false) return false;
		RoleAuthorizationLinkDO other = (RoleAuthorizationLinkDO)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

