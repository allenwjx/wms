/**
 * Jungle.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */package com.zeh.wms.dal.daointerface;
import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.dal.dataobject.InventoryHistoryDO;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryQuery;
import com.zeh.wms.dal.operation.inventoryhistory.QueryHistoryResult;
import org.springframework.dao.DataAccessException;

import java.util.List;
/**
 * InventoryHistoryDAO
 * database table: inventory_history
 * database table comments: InventoryHistory
 * This file is generated by <tt>dalgen</tt>, a DAL (Data Access Layer)
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/dalgen</tt>
 * 
 * @author Allen Wang(Wang Junxiang)
 * */
public interface InventoryHistoryDAO {


	/**
	 * 
	 * sql:
	 * <pre>INSERT      INTO         inventory_history         (             commodity_id,             mobile,             amount,             gmt_create,             gmt_modified,              create_by,             modify_by             )      VALUES         (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?)</pre> 
	 */
	public long insert(InventoryHistoryDO inventoryHistory) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, commodity_id, mobile, amount, gmt_create, gmt_modified, create_by, modify_by                       FROM         inventory_history                  WHERE         id = ?</pre> 
	 */
	public InventoryHistoryDO queryById(Long id) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         id, commodity_id, mobile, amount, gmt_create, gmt_modified, create_by, modify_by                       FROM         inventory_history                  WHERE         mobile = ?</pre> 
	 */
	public List<InventoryHistoryDO> queryByMobile(String mobile) throws DataAccessException;

	/**
	 * 
	 * sql:
	 * <pre>SELECT         inv.id,             inv.commodity_id,             inv.mobile,             inv.amount,             inv.gmt_create,              inv.gmt_modified,             inv.create_by,             inv.modify_by,             u.nick_name,              com.commodity_name                  FROM         inventory_history inv      left join         user u              on inv.mobile = u.mobile                  left join         (             select                 name as commodity_name, id              from                 commodity          ) com              on inv.commodity_id = com.id                  WHERE         1=1                                        AND                      inv.mobile = ?                                            AND                      u.nick_name = ?                                            AND                      inv.commodity_id = ?                                             AND                                               inv.gmt_modified >= ?                                                                 AND                                               inv.gmt_modified <= ?</pre> 
	 */
	public PageList<QueryHistoryResult> queryHistory(QueryHistoryQuery param) throws DataAccessException;

}



