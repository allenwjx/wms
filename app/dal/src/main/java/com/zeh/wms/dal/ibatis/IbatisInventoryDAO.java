/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */ 
package com.zeh.wms.dal.ibatis;

import com.zeh.wms.dal.operation.inventory.*;
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

import com.zeh.wms.dal.dataobject.InventoryDO;
import com.zeh.wms.dal.daointerface.InventoryDAO;

/**
 * InventoryDAO
 * database table: inventory
 * database table comments: Inventory
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public class IbatisInventoryDAO extends SqlMapClientDaoSupport implements InventoryDAO {


	/**
	 * 
	 * sql: 
	 * <pre>INSERT      INTO         inventory         (             commodity_id,             mobile,             amount,             gmt_create,             gmt_modified,              create_by,             modify_by             )      VALUES         (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)</pre>
	 */
	public long insert(InventoryDO inventory) throws DataAccessException {
		if(inventory == null) {
			throw new IllegalArgumentException("Can't insert a null data object into db.");
		}
		getSqlMapClientTemplate().insert("wms.Inventory.insert", inventory);
		return inventory.getId();
	}

	/**
	 * 
	 * sql: 
	 * <pre>DELETE      FROM         inventory      WHERE         id = ?</pre>
	 */
	public int delete(Long id) throws DataAccessException {
		return getSqlMapClientTemplate().delete("wms.Inventory.delete", id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>DELETE      FROM         inventory      WHERE         mobile = ?</pre>
	 */
	public int deleteByMobile(String mobile) throws DataAccessException {
		return getSqlMapClientTemplate().delete("wms.Inventory.deleteByMobile", mobile);
	}

	/**
	 * 
	 * sql: 
	 * <pre>UPDATE         inventory      SET         amount = amount + ? ,gmt_modified = CURRENT_TIMESTAMP,modify_by = ?                  WHERE         mobile = ?          and commodity_id = ?</pre>
	 */
	public int addAmountByMobile(AddAmountByMobileParameter param) throws DataAccessException {
		return getSqlMapClientTemplate().update("wms.Inventory.addAmountByMobile", param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, commodity_id, mobile, amount, gmt_create, gmt_modified, create_by, modify_by                       FROM         inventory                  WHERE         id = ?</pre>
	 */
	public InventoryDO queryById(Long id) throws DataAccessException {
		return (InventoryDO)getSqlMapClientTemplate().queryForObject("wms.Inventory.queryById",id);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, commodity_id, mobile, amount, gmt_create, gmt_modified, create_by, modify_by                       FROM         inventory                  WHERE         mobile = ?</pre>
	 */
	public List<InventoryDO> queryByMobile(String mobile) throws DataAccessException {
		return (List<InventoryDO>)getSqlMapClientTemplate().queryForList("wms.Inventory.queryByMobile",mobile);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         id, commodity_id, mobile, amount, gmt_create, gmt_modified, create_by, modify_by                       FROM         inventory                  WHERE         mobile = ?          and commodity_id = ?             limit 1</pre>
	 */
	public InventoryDO queryByMobileAndCommodityId(String mobile ,Long commodityId) throws DataAccessException {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("mobile",mobile);
		param.put("commodityId",commodityId);
		return (InventoryDO)getSqlMapClientTemplate().queryForObject("wms.Inventory.queryByMobileAndCommodityId",param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         inv.id,             inv.commodity_id,             inv.mobile,             inv.amount,             inv.gmt_create,              inv.gmt_modified,             inv.create_by,             inv.modify_by,             u.name,              com.commodity_name                  FROM         inventory inv      left join         agent u              on inv.mobile = u.mobile                  left join         (             select                 name as commodity_name, id              from                 commodity          ) com              on inv.commodity_id = com.id                  WHERE         1=1                                        AND                      inv.mobile = ?                                            AND                      u.name = ?                                            AND                      inv.commodity_id = ?                                             AND                                               inv.gmt_modified >= ?                                                                 AND                                               inv.gmt_modified <= ?</pre>
	 */
	public PageList<FindPageResult> findPage(FindPageQuery param) throws DataAccessException {
		return PageQueryUtils.pageQuery(getSqlMapClientTemplate(),"wms.Inventory.findPage",param);
	}

	/**
	 * 
	 * sql: 
	 * <pre>SELECT         inv.id,             inv.commodity_id,             inv.mobile,             inv.amount,             com.code,              com.name,             com.price,             com.unit,             com.weight,             com.description,              com.manufacturer_id                  FROM         inventory inv      left join         agent u              on inv.mobile = u.mobile                  left join         commodity com              on inv.commodity_id = com.id                   WHERE         inv.mobile = ?                                            AND                      inv.id = ?                                            AND                      com.name like concat('%',?,'%')</pre>
	 */
	public List<GetInfoByMobileResult> getInfoByMobile(GetInfoByMobileQuery param) throws DataAccessException {
		return (List<GetInfoByMobileResult>)getSqlMapClientTemplate().queryForList("wms.Inventory.getInfoByMobile",param);
	}

}

