/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */ 
package com.zeh.wms.dal.ibatis;

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
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zeh.wms.dal.dataobject.QrcodeDO;
import com.zeh.wms.dal.daointerface.QrcodeDAO;

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
public class IbatisQrcodeDAO extends SqlMapClientDaoSupport implements QrcodeDAO {


	/**
	 * 
	 * sql: 
	 * <pre>INSERT      INTO         qrcode         (           id ,serial_no ,commodity_id , batch_id, data ,gmt_create ,gmt_modified ,create_by ,modify_by          )      VALUES         (?,?,?,?,?,?,?,?,?)</pre>
	 */
	public long insert(QrcodeDO qrcode) throws DataAccessException {
		if(qrcode == null) {
			throw new IllegalArgumentException("Can't insert a null data object into db.");
		}
		getSqlMapClientTemplate().insert("wms.Qrcode.insert", qrcode);
		return qrcode.getId();
	}

	/**
	 * 
	 * sql: 
	 * <pre>DELETE      FROM         qrcode      WHERE         id = ?</pre>
	 */
	public int delete(Long id) throws DataAccessException {
		return getSqlMapClientTemplate().delete("wms.Qrcode.delete", id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         qrcode      SET         serial_no = ? ,commodity_id = ? , batch_id = ?, data = ? ,gmt_create = ? ,gmt_modified = ? ,create_by = ? ,modify_by = ?               WHERE         id = ?</pre>
	 */
	public int update(QrcodeDO qrcode) throws DataAccessException {
		if(qrcode == null) {
			throw new IllegalArgumentException("Can't update by a null data object.");
		}
		return getSqlMapClientTemplate().update("wms.Qrcode.update", qrcode);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, gmt_create, gmt_modified, create_by, modify_by                 FROM         qrcode                WHERE         id = ?</pre>
	 */
	public QrcodeDO queryById(Long id) throws DataAccessException {
		return (QrcodeDO)getSqlMapClientTemplate().queryForObject("wms.Qrcode.queryById",id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, gmt_create, gmt_modified, create_by, modify_by           FROM         qrcode</pre>
	 */
	public PageList<QrcodeDO> findPage(int pageSize,int pageNum) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.Qrcode.findPage",null,pageNum,pageSize);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, gmt_create, gmt_modified, create_by, modify_by           FROM         qrcode         WHERE         commodity_id = ?</pre>
	 */
	public PageList<QrcodeDO> findByCommodityId(Long commodityId,int pageSize,int pageNum) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.Qrcode.findByCommodityId",commodityId,pageNum,pageSize);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, gmt_create, gmt_modified, create_by, modify_by           FROM         qrcode         ORDER BY         gmt_modified DESC</pre>
	 */
	public PageList<QrcodeDO> queryPageAll(int pageSize,int pageNum) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.Qrcode.queryPageAll",null,pageNum,pageSize);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, serial_no, commodity_id, batch_id, data, gmt_create, gmt_modified, create_by, modify_by           FROM         qrcode         WHERE         commodity_id = ?                    AND       batch_id = ?                  ORDER BY         gmt_modified DESC</pre>
	 */
	public PageList<QrcodeDO> queryPageByConditions(QueryPageByConditionsQuery param) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.Qrcode.queryPageByConditions",param);
	}

}

