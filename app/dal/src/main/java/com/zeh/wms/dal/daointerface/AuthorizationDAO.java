/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import org.springframework.dao.DataAccessException;
import com.zeh.wms.dal.operation.authorization.*;
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
 * AuthorizationDAO
 * database table: authorization
 * database table comments: Authorization
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public interface AuthorizationDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         authorization         (           id ,name ,code ,path ,gmt_create ,gmt_modifeid ,create_by ,modified_by           )      VALUES         (?,?,?,?,?,?,?,?)</pre> 
	 */
	public long insert(AuthorizationDO authorization) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>DELETE      FROM         authorization      WHERE         id = ?</pre> 
	 */
	public int delete(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>UPDATE         authorization      SET         name = ? ,code = ? ,path = ? ,gmt_create = ? ,gmt_modifeid = ? ,create_by = ? ,modified_by = ?                WHERE         id = ?</pre> 
	 */
	public int update(AuthorizationDO authorization) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, name, code, path, gmt_create, gmt_modifeid, create_by, modified_by                  FROM         authorization                WHERE         id = ?</pre> 
	 */
	public AuthorizationDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, name, code, path, gmt_create, gmt_modifeid, create_by, modified_by            FROM         authorization</pre> 
	 */
	public PageList<AuthorizationDO> findPage(int pageSize,int pageNum) throws DataAccessException;

}



