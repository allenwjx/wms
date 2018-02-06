/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import org.springframework.dao.DataAccessException;
import com.zeh.wms.dal.operation.expressorder.*;
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
 * ExpressOrderDAO
 * database table: express_order
 * database table comments: ExpressOrder
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public interface ExpressOrderDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         express_order         (           id ,order_no ,other_order_no ,code ,status ,sender_name ,sender_tel ,sender_province ,sender_city ,sender_region ,sender_address_detail ,sender_zip_code ,receiver_name ,receiver_tel ,receiver_province ,receiver_city ,receiver_region ,receiver_address_detail ,receiver_zip_code ,express_type ,total_price ,gmt_create ,gmt_modified ,create_by ,modify_by           )      VALUES         (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)</pre> 
	 */
	public long insert(ExpressOrderDO expressOrder) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>DELETE      FROM         express_order      WHERE         id = ?</pre> 
	 */
	public int delete(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>UPDATE         express_order      SET         order_no = ? ,other_order_no = ? ,code = ? ,status = ? ,sender_name = ? ,sender_tel = ? ,sender_province = ? ,sender_city = ? ,sender_region = ? ,sender_address_detail = ? ,sender_zip_code = ? ,receiver_name = ? ,receiver_tel = ? ,receiver_province = ? ,receiver_city = ? ,receiver_region = ? ,receiver_address_detail = ? ,receiver_zip_code = ? ,express_type = ? ,total_price = ? ,gmt_create = ? ,gmt_modified = ? ,create_by = ? ,modify_by = ?                WHERE         id = ?</pre> 
	 */
	public int update(ExpressOrderDO expressOrder) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, order_no, other_order_no, code, status, sender_name, sender_tel, sender_province, sender_city, sender_region, sender_address_detail, sender_zip_code, receiver_name, receiver_tel, receiver_province, receiver_city, receiver_region, receiver_address_detail, receiver_zip_code, express_type, total_price, gmt_create, gmt_modified, create_by, modify_by                  FROM         express_order                WHERE         id = ?</pre> 
	 */
	public ExpressOrderDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, order_no, other_order_no, code, status, sender_name, sender_tel, sender_province, sender_city, sender_region, sender_address_detail, sender_zip_code, receiver_name, receiver_tel, receiver_province, receiver_city, receiver_region, receiver_address_detail, receiver_zip_code, express_type, total_price, gmt_create, gmt_modified, create_by, modify_by            FROM         express_order</pre> 
	 */
	public PageList<ExpressOrderDO> findPage(int pageSize,int pageNum) throws DataAccessException;

}


