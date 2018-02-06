/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */ 
package com.zeh.wms.dal.ibatis;

import com.zeh.wms.dal.operation.userbg.*;
import com.zeh.wms.dal.dataobject.*;


import java.io.*;
import java.net.*;
import java.util.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.zeh.jungle.dal.paginator.PageQuery;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageQueryUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zeh.wms.dal.dataobject.UserBgDO;
import com.zeh.wms.dal.daointerface.UserBgDAO;

/**
 * UserBgDAO
 * database table: user_bg
 * database table comments: UserBg
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class IbatisUserBgDAO extends SqlMapClientDaoSupport implements UserBgDAO {


	/**
	 * 
	 * sql: 
	 * <pre>INSERT      INTO         user_bg         (           id ,username ,password ,gmt_create ,gmt_modified ,create_by ,modified_by           )      VALUES         (?,?,?,?,?,?,?)</pre>
	 */
	public long insert(UserBgDO userBg) throws DataAccessException {
		if(userBg == null) {
			throw new IllegalArgumentException("Can't insert a null data object into db.");
		}
		getSqlMapClientTemplate().insert("wms.UserBg.insert", userBg);
		return userBg.getId();
	}

	/**
	 * 
	 * sql: 
	 * <pre>DELETE      FROM         user_bg      WHERE         id = ?</pre>
	 */
	public int delete(Long id) throws DataAccessException {
		return getSqlMapClientTemplate().delete("wms.UserBg.delete", id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         user_bg      SET         username = ? ,password = ? ,gmt_create = ? ,gmt_modified = ? ,create_by = ? ,modified_by = ?                WHERE         id = ?</pre>
	 */
	public int update(UserBgDO userBg) throws DataAccessException {
		if(userBg == null) {
			throw new IllegalArgumentException("Can't update by a null data object.");
		}
		return getSqlMapClientTemplate().update("wms.UserBg.update", userBg);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, username, password, gmt_create, gmt_modified, create_by, modified_by                  FROM         user_bg                WHERE         id = ?</pre>
	 */
	public UserBgDO queryById(Long id) throws DataAccessException {
		return (UserBgDO)getSqlMapClientTemplate().queryForObject("wms.UserBg.queryById",id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, username, password, gmt_create, gmt_modified, create_by, modified_by            FROM         user_bg</pre>
	 */
	public PageList<UserBgDO> findPage(int pageSize,int pageNum) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.UserBg.findPage",null,pageNum,pageSize);
	}

}

