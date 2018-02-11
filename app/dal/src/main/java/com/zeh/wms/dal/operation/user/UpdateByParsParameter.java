
/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.zeh.wms.dal.operation.user;

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
 * database table: user
 * database table comments: User
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class UpdateByParsParameter  implements java.io.Serializable {
	private static final long serialVersionUID = -5216457518046898601L;
	
	/** 昵称 */
	private String nickName;
	/** 用户账号，用户电话 */
	private String userId;
	/** 密码 */
	private String password;
	/** openid */
	private String openId;
	/** 客户类型； 代理商：A； 厂商：B； 散客: C； */
	private String type;
	/** noExistsProp */
	private Object noExistsProp;
	/** 修改人 */
	private String modifyBy;
	/** id */
	private Long id;
	
	public UpdateByParsParameter() {
	}
	
	public UpdateByParsParameter(String nickName ,String userId ,String password ,String openId ,String type ,Object noExistsProp ,String modifyBy ,Long id ) {
		this.nickName = nickName;
		this.userId = userId;
		this.password = password;
		this.openId = openId;
		this.type = type;
		this.noExistsProp = noExistsProp;
		this.modifyBy = modifyBy;
		this.id = id;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getNoExistsProp() {
		return noExistsProp;
	}
	public void setNoExistsProp(Object noExistsProp) {
		this.noExistsProp = noExistsProp;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
