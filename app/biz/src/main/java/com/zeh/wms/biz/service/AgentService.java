package com.zeh.wms.biz.service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.enums.StateEnum;

import java.util.Collection;
import java.util.List;

/**
 * The interface Agent service.
 *
 * @author allen
 * @create $ ID: AgentService, 18/2/8 10:59 allen Exp $
 * @since 1.0.0
 */
public interface AgentService {
    /**
     * 创建代理商
     *
     * @param agent 代理商
     * @throws ServiceException 代理商创建异常
     */
    void createAgent(AgentVO agent) throws ServiceException;

    /**
     * 批量更新.
     *
     * @param agentVOS the agent vos
     * @throws ServiceException the service exception
     */
    void batchCreateAgent(List<AgentVO> agentVOS) throws ServiceException;

    /**
     * 更新代理商信息
     *
     * @param agent 代理商
     * @throws ServiceException 代理商更新异常
     */
    void updateAgent(AgentVO agent) throws ServiceException;

    /**
     * 根据代理商编号查询代理商信息
     *
     * @param code 代理商编号
     * @return 代理商信息 agent vo
     * @throws ServiceException 代理商查询异常
     */
    AgentVO findAgentByCode(String code) throws ServiceException;

    /**
     * 根据代理商ID查询代理商信息
     *
     * @param id 代理商ID
     * @return 代理商信息 agent vo
     * @throws ServiceException 代理商查询异常
     */
    AgentVO findAgentById(long id) throws ServiceException;

    /**
     * 分页查询代理商信息
     *
     * @param agent       代理商查询条件
     * @param currentPage 查询起始页
     * @param size        每页数量
     * @return 代理商信息 page list
     * @throws ServiceException 分页查询异常
     */
    PageList<AgentVO> pageQueryAgents(AgentVO agent, int currentPage, int size) throws ServiceException;

    /**
     * 查询所有代理商信息
     *
     * @return 所有代理商信息 collection
     * @throws ServiceException 代理商查询异常
     */
    Collection<AgentVO> findAllAgents() throws ServiceException;

    /**
     * 更新代理商启用、禁用状态
     *
     * @param id       代理商ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 代理商状态更新异常
     */
    void updateAgentState(long id, String modifyBy, StateEnum enabled) throws ServiceException;

    /**
     * 删除代理商信息，不能恢复
     *
     * @param id 代理商ID
     * @throws ServiceException 代理商删除异常
     */
    void deleteAgent(long id) throws ServiceException;
}
