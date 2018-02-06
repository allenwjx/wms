/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import org.springframework.dao.DataAccessException;
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
public interface UserDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         user         (           id ,nick_name ,user_id ,password ,open_id ,gmt_create ,gmt_modified ,create_by ,modify_by ,type           )      VALUES         (?,?,?,?,?,?,?,?,?,?)</pre> 
	 */
	public long insert(UserDO user) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>DELETE      FROM         user      WHERE         id = ?</pre> 
	 */
	public int delete(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>UPDATE         user      SET         nick_name = ? ,user_id = ? ,password = ? ,open_id = ? ,gmt_create = ? ,gmt_modified = ? ,create_by = ? ,modify_by = ? ,type = ?                WHERE         id = ?</pre> 
	 */
	public int update(UserDO user) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, nick_name, user_id, password, open_id, gmt_create, gmt_modified, create_by, modify_by, type                  FROM         user                WHERE         id = ?</pre> 
	 */
	public UserDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, nick_name, user_id, password, open_id, gmt_create, gmt_modified, create_by, modify_by, type            FROM         user</pre> 
	 */
	public PageList<UserDO> findPage(int pageSize,int pageNum) throws DataAccessException;

}


