/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */ 
package com.zeh.wms.dal.ibatis;

import com.zeh.wms.dal.operation.user.*;
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

import com.zeh.wms.dal.dataobject.UserDO;
import com.zeh.wms.dal.daointerface.UserDAO;

/**
 * UserDAO
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
public class IbatisUserDAO extends SqlMapClientDaoSupport implements UserDAO {


	/**
	 * 
	 * sql: 
	 * <pre>INSERT      INTO         user         (             id ,nick_name ,user_id ,password ,open_id ,             gmt_create ,gmt_modified ,create_by ,modify_by ,mobile,             payment_type             )      VALUES         (?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,?,?)</pre>
	 */
	public long insert(UserDO user) throws DataAccessException {
		if(user == null) {
			throw new IllegalArgumentException("Can't insert a null data object into db.");
		}
		getSqlMapClientTemplate().insert("wms.User.insert", user);
		return user.getId();
	}

	/**
	 * 
	 * sql: 
	 * <pre>DELETE      FROM         user      WHERE         id = ?</pre>
	 */
	public int delete(Long id) throws DataAccessException {
		return getSqlMapClientTemplate().delete("wms.User.delete", id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         user      SET         nick_name = ? ,user_id = ? ,password = ? ,open_id = ? ,gmt_modified = ? ,modify_by = ? ,mobile = ?, payment_type = ?                  WHERE         id = ?</pre>
	 */
	public int update(UserDO user) throws DataAccessException {
		if(user == null) {
			throw new IllegalArgumentException("Can't update by a null data object.");
		}
		return getSqlMapClientTemplate().update("wms.User.update", user);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         user      SET         gmt_modified = CURRENT_TIMESTAMP              ,                  nick_name = ?                           ,                  user_id = ?                           ,                  mobile = ?                            ,                  password = ?                            ,                  open_id = ?                            ,                  payment_type = ?                            ,                  modify_by = ?                                WHERE         id = ?                        AND                  1 = ?</pre>
	 */
	public int updateByPars(UpdateByParsParameter param) throws DataAccessException {
		return getSqlMapClientTemplate().update("wms.User.updateByPars", param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, nick_name, user_id, password, open_id, gmt_create, gmt_modified, create_by, modify_by, mobile, payment_type                 FROM         user               WHERE         id = ?</pre>
	 */
	public UserDO queryById(Long id) throws DataAccessException {
		return (UserDO)getSqlMapClientTemplate().queryForObject("wms.User.queryById",id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, nick_name, user_id, password, open_id, gmt_create, gmt_modified, create_by, modify_by, mobile, payment_type                    FROM         user                  WHERE         open_id = ?</pre>
	 */
	public UserDO queryByOpenId(String openId) throws DataAccessException {
		return (UserDO)getSqlMapClientTemplate().queryForObject("wms.User.queryByOpenId",openId);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, nick_name, user_id, password, open_id, gmt_create, gmt_modified, create_by, modify_by, mobile, payment_type                    FROM         user                  WHERE         user_id = ?</pre>
	 */
	public UserDO queryByUserId(String userId) throws DataAccessException {
		return (UserDO)getSqlMapClientTemplate().queryForObject("wms.User.queryByUserId",userId);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, nick_name, user_id, password, open_id, gmt_create, gmt_modified, create_by, modify_by, mobile, payment_type           FROM         user u                  WHERE         1=1                                        AND                      u.nick_name = ?                                            AND                      u.user_id = ?                                            AND                      u.mobile = ?                                            AND                      u.payment_type = ?                                            AND                                               u.gmt_create >= ?                                                                 AND                                               u.gmt_create <= ?</pre>
	 */
	public PageList<UserDO> getAllUserPage(GetAllUserPageQuery param) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.User.getAllUserPage",param);
	}

}

