/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */ 
package com.zeh.wms.dal.ibatis;

import com.zeh.wms.dal.operation.useraddres.*;
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

import com.zeh.wms.dal.dataobject.UserAddresDO;
import com.zeh.wms.dal.daointerface.UserAddresDAO;

/**
 * UserAddresDAO
 * database table: user_address
 * database table comments: UserAddres
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class IbatisUserAddresDAO extends SqlMapClientDaoSupport implements UserAddresDAO {


	/**
	 * 
	 * sql: 
	 * <pre>INSERT      INTO         user_address         (           id ,name ,tel ,zip_code ,province ,city ,region ,detail ,company, address_type ,user_id ,default_setting ,gmt_create ,gmt_modified ,create_by ,modify_by          )      VALUES         (?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)</pre>
	 */
	public long insert(UserAddresDO userAddres) throws DataAccessException {
		if(userAddres == null) {
			throw new IllegalArgumentException("Can't insert a null data object into db.");
		}
		getSqlMapClientTemplate().insert("wms.UserAddres.insert", userAddres);
		return userAddres.getId();
	}

	/**
	 * 
	 * sql: 
	 * <pre>DELETE      FROM         user_address      WHERE         id = ?          and user_id = ?</pre>
	 */
	public int delete(Long id ,Long userId) throws DataAccessException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id",id);
		param.put("userId",userId);
		return getSqlMapClientTemplate().delete("wms.UserAddres.delete", param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         user_address      SET         name = ? ,tel = ? ,zip_code = ? ,province = ? ,city = ? ,region = ? ,detail = ? ,company = ?, address_type = ? ,user_id = ? ,default_setting = ? , gmt_modified = CURRENT_TIMESTAMP ,modify_by = ?               WHERE         id = ?</pre>
	 */
	public int update(UserAddresDO userAddres) throws DataAccessException {
		if(userAddres == null) {
			throw new IllegalArgumentException("Can't update by a null data object.");
		}
		return getSqlMapClientTemplate().update("wms.UserAddres.update", userAddres);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         user_address      SET         default_setting = ? , gmt_modified = CURRENT_TIMESTAMP ,modify_by = ?                  WHERE         user_id = ?</pre>
	 */
	public int updateDefaultSettingByUserId(Integer defaultSetting ,String modifyBy ,Long userId) throws DataAccessException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("defaultSetting",defaultSetting);
		param.put("modifyBy",modifyBy);
		param.put("userId",userId);
		return getSqlMapClientTemplate().update("wms.UserAddres.updateDefaultSettingByUserId", param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, name, tel, zip_code, province, city, region, detail, company, address_type, user_id, default_setting, gmt_create, gmt_modified, create_by, modify_by                 FROM         user_address                WHERE         id = ?</pre>
	 */
	public UserAddresDO queryById(Long id) throws DataAccessException {
		return (UserAddresDO)getSqlMapClientTemplate().queryForObject("wms.UserAddres.queryById",id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, name, tel, zip_code, province, city, region, detail, company, address_type, user_id, default_setting, gmt_create, gmt_modified, create_by, modify_by           FROM         user_address</pre>
	 */
	public PageList<UserAddresDO> findPage(int pageSize,int pageNum) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.UserAddres.findPage",null,pageNum,pageSize);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, name, tel, zip_code, province, city, region, detail, company, address_type, user_id, default_setting, gmt_create, gmt_modified, create_by, modify_by           FROM         user_address         where         user_id = ?          and address_type = ?          and default_setting = 1    limit 1</pre>
	 */
	public UserAddresDO getDefault(Long userId ,String addressType) throws DataAccessException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId",userId);
		param.put("addressType",addressType);
		return (UserAddresDO)getSqlMapClientTemplate().queryForObject("wms.UserAddres.getDefault",param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, name, tel, zip_code, province, city, region, detail, company, address_type, user_id, default_setting, gmt_create, gmt_modified, create_by, modify_by           FROM         user_address         where         user_id = ?          and address_type = ?</pre>
	 */
	public List<UserAddresDO> getList(Long userId ,String addressType) throws DataAccessException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId",userId);
		param.put("addressType",addressType);
		return (List<UserAddresDO>)getSqlMapClientTemplate().queryForList("wms.UserAddres.getList",param);
	}

}

