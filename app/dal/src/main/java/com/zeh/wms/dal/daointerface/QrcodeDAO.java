/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import org.springframework.dao.DataAccessException;
import com.zeh.wms.dal.operation.qrcode.*;
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
 * QrcodeDAO
 * database table: qrcode
 * database table comments: Qrcode
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public interface QrcodeDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         qrcode         (             id ,serial_no ,commodity_id ,batch_id ,data , state, gmt_create ,gmt_modified ,create_by ,modify_by             )      VALUES         (?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)</pre> 
	 */
	public long insert(QrcodeDO qrcode) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>DELETE      FROM         qrcode      WHERE         id = ?</pre> 
	 */
	public int delete(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>UPDATE         qrcode      SET         serial_no = ? ,commodity_id = ? ,batch_id = ? ,data = ? ,state = ? ,gmt_modified = CURRENT_TIMESTAMP ,modify_by = ?                  WHERE         id = ?</pre> 
	 */
	public int update(QrcodeDO qrcode) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, state, gmt_create, gmt_modified, create_by, modify_by                       FROM         qrcode                  WHERE         id = ?</pre> 
	 */
	public QrcodeDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, state, gmt_create, gmt_modified, create_by, modify_by                       FROM         qrcode                  WHERE         serial_no = ?</pre> 
	 */
	public QrcodeDO queryBySerialno(String serialNo) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, state, gmt_create, gmt_modified, create_by, modify_by                       FROM         qrcode                  WHERE         commodity_id = ?</pre> 
	 */
	public PageList<QrcodeDO> findByCommodityId(Long commodityId,int pageSize,int pageNum) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, state, gmt_create, gmt_modified, create_by, modify_by                       FROM         qrcode                  WHERE         1 = 1                                        AND                      commodity_id = ?                                            AND                      batch_id = ?                                            AND                      state = ?                                                ORDER BY         gmt_modified DESC</pre> 
	 */
	public PageList<QrcodeDO> queryPageByConditions(QueryPageByConditionsQuery param) throws DataAccessException;

}



