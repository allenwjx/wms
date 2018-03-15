/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import org.springframework.dao.DataAccessException;
import com.zeh.wms.dal.operation.userexpressdiscount.*;
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
 * UserExpressDiscountDAO
 * database table: user_express_discount
 * database table comments: UserExpressDiscount
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public interface UserExpressDiscountDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         user_express_discount         (             user_id, express_code, discount, gmt_create, gmt_modified, create_by, modify_by             )      VALUES         (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)</pre> 
	 */
	public long insert(UserExpressDiscountDO userExpressDiscount) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>DELETE      FROM         user_express_discount      WHERE         id = ?</pre> 
	 */
	public int delete(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>UPDATE         user_express_discount      SET         user_id = ? , express_code = ?, gmt_modified = CURRENT_TIMESTAMP , modify_by = ?                  WHERE         id = ?</pre> 
	 */
	public int update(UserExpressDiscountDO userExpressDiscount) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, user_id, express_code, discount, gmt_create, gmt_modified, create_by, modify_by                       FROM         user_express_discount                  WHERE         id = ?</pre> 
	 */
	public UserExpressDiscountDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, user_id, express_code, discount, gmt_create, gmt_modified, create_by, modify_by                       FROM         user_express_discount                  WHERE         user_id = ?</pre> 
	 */
	public List<UserExpressDiscountDO> queryByUserId(Long userId) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, user_id, express_code, discount, gmt_create, gmt_modified, create_by, modify_by                       FROM         user_express_discount                  WHERE         1=1                                        AND                      user_id = ?                                             AND                      express_code = ?</pre> 
	 */
	public UserExpressDiscountDO queryUserDiscountByExpress(QueryUserDiscountByExpressQuery param) throws DataAccessException;

}



