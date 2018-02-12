/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import org.springframework.dao.DataAccessException;
import com.zeh.wms.dal.operation.paymentorder.*;
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
 * PaymentOrderDAO
 * database table: payment_order
 * database table comments: PaymentOrder
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public interface PaymentOrderDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         payment_order         (           id ,order_no ,other_order_no ,user_id ,code ,payment_order_no ,other_payment_no ,amount ,channel ,status ,pay_limited ,gmt_create ,gmt_modified ,create_by ,modify_by           )      VALUES         (?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)</pre> 
	 */
	public long insert(PaymentOrderDO paymentOrder) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>DELETE      FROM         payment_order      WHERE         id = ?</pre> 
	 */
	public int delete(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>UPDATE         payment_order      SET         order_no = ? ,other_order_no = ? ,user_id = ? ,code = ? ,payment_order_no = ? ,other_payment_no = ? ,amount = ? ,channel = ? ,status = ? ,pay_limited = ? ,gmt_modified = CURRENT_TIMESTAMP ,create_by = ? ,modify_by = ?               WHERE         id = ?</pre> 
	 */
	public int update(PaymentOrderDO paymentOrder) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, order_no, other_order_no, user_id, code, payment_order_no, other_payment_no, amount, channel, status, pay_limited, gmt_create, gmt_modified, create_by, modify_by                  FROM         payment_order                WHERE         id = ?</pre> 
	 */
	public PaymentOrderDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, order_no, other_order_no, user_id, code, payment_order_no, other_payment_no, amount, channel, status, pay_limited, gmt_create, gmt_modified, create_by, modify_by            FROM         payment_order         WHERE         1 = 1                   AND       order_no = ?                    AND       other_order_no = ?                    AND       user_id = ?                    AND       code = ?                    AND       payment_order_no = ?                    AND       other_payment_no = ?                    AND       status = ?                    AND                                gmt_create >= ?                                         AND                                gmt_create <= ?</pre> 
	 */
	public PageList<PaymentOrderDO> getPageData(GetPageDataQuery param) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, order_no, other_order_no, user_id, code, payment_order_no, other_payment_no, amount, channel, status, pay_limited, gmt_create, gmt_modified, create_by, modify_by            FROM         payment_order         WHERE         1 = 1                   AND       order_no = ?                    AND       other_order_no = ?                    AND       user_id = ?                    AND       code = ?                    AND       payment_order_no = ?                    AND       other_payment_no = ?                    AND       status = ?                    AND                                gmt_create >= ?                                         AND                                gmt_create <= ?</pre> 
	 */
	public List<PaymentOrderDO> getAllData(GetAllDataQuery param) throws DataAccessException;

}



