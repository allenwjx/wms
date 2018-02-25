package com.zeh.wms.biz.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.AgentMapper;
import com.zeh.wms.biz.model.AgentVO;
import com.zeh.wms.biz.model.enums.StateEnum;
import com.zeh.wms.biz.service.AgentService;
import com.zeh.wms.biz.utils.CodeGenerator;
import com.zeh.wms.dal.daointerface.AgentDAO;
import com.zeh.wms.dal.dataobject.AgentDO;
import com.zeh.wms.dal.operation.agent.QueryByPageQuery;

/**
 * The type Agent service.
 *
 * @author allen
 * @create $ ID: AgentServiceImpl, 18/2/8 13:15 allen Exp $
 * @since 1.0.0
 */
@Service
public class AgentServiceImpl implements AgentService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger                logger        = LoggerFactory.getLogger(AgentServiceImpl.class);
    /** 代理商数据库访问组件 */
    @Resource
    private AgentDAO                     agentDAO;
    /** mapper */
    @Resource
    private AgentMapper                  mapper;

    /**
     * 创建代理商
     *
     * @param agent 代理商
     * @throws ServiceException 代理商创建异常
     */
    @Override
    public void createAgent(AgentVO agent) throws ServiceException {
        if (agent == null) {
            throw new ServiceException(ERROR_FACTORY.createAgentError());
        }
        agent.setCode(CodeGenerator.generateAgentCode());
        agent.setEnabled(StateEnum.Y);
        AgentDO agentDO = mapper.vo2do(agent);
        agentDAO.insert(agentDO);
    }

    /**
     * 批量更新
     * @param agentVOS 代理商列表。
     * @throws ServiceException 代理商更新异常
     */
    @Override
    public void batchCreateAgent(List<AgentVO> agentVOS) throws ServiceException {
        if (CollectionUtils.isEmpty(agentVOS)) {
            return;
        }
        agentVOS.forEach(item -> {
            AgentDO agentDO = agentDAO.queryByExternalCode(item.getCode());
            if (agentDO == null) {
                try {
                    createAgent(item);
                } catch (ServiceException e) {
                    logger.error("批量导入失败！", e);
                }
                return;
            }

            updateDO(item, agentDO);
        });
    }

    /**
     * 更新代理商信息
     *
     * @param agent 代理商
     * @throws ServiceException 代理商更新异常
     */
    @Override
    public void updateAgent(AgentVO agent) throws ServiceException {
        if (agent == null || agent.getId() <= 0) {
            throw new ServiceException(ERROR_FACTORY.updateAgentError());
        }
        AgentDO agentDO = agentDAO.queryById(agent.getId());
        updateDO(agent, agentDO);
    }

    private void updateDO(AgentVO agent, AgentDO agentDO) {
        agentDO.setAddress(StringUtils.isNotBlank(agent.getAddress()) ? agent.getAddress() : agentDO.getAddress());
        agentDO.setExternalCode(StringUtils.isNotBlank(agent.getExternalCode()) ? agent.getExternalCode() : agentDO.getExternalCode());
        agentDO.setMobile(StringUtils.isNotBlank(agent.getMobile()) ? agent.getMobile() : agentDO.getMobile());
        agentDO.setName(StringUtils.isNotBlank(agent.getName()) ? agent.getName() : agentDO.getName());
        agentDO.setEnabled(agent.getEnabled() != null ? agent.getEnabled().getCode() : agentDO.getEnabled());
        agentDO.setModifyBy(agent.getModifyBy());
        agentDAO.update(agentDO);
    }

    /**
     * 根据代理商编号查询代理商信息
     *
     * @param code 代理商编号
     * @return 代理商信息
     * @throws ServiceException 代理商查询异常
     */
    @Override
    public AgentVO findAgentByCode(String code) throws ServiceException {
        if (StringUtils.isBlank(code)) {
            throw new ServiceException(ERROR_FACTORY.queryAgentError());
        }
        AgentDO agentDO = agentDAO.queryByCode(code);
        return mapper.do2vo(agentDO);
    }

    /**
     * 根据代理商ID查询代理商信息
     *
     * @param id 代理商ID
     * @return 代理商信息
     * @throws ServiceException 代理商查询异常
     */
    @Override
    public AgentVO findAgentById(long id) throws ServiceException {
        AgentDO agentDO = agentDAO.queryById(id);
        if (agentDO == null) {
            return null;
        }
        return mapper.do2vo(agentDO);
    }

    /**
     * 分页查询代理商信息
     *
     * @param agent       代理商查询条件
     * @param currentPage 查询起始页
     * @param size        每页数量
     * @return 代理商信息
     * @throws ServiceException 分页查询异常
     */
    @Override
    public PageList<AgentVO> pageQueryAgents(AgentVO agent, int currentPage, int size) throws ServiceException {
        if (agent == null) {
            throw new ServiceException(ERROR_FACTORY.queryAgentError());
        }
        QueryByPageQuery query = new QueryByPageQuery();
        query.setCode(agent.getCode());
        query.setEnabled(agent.getEnabled() == null ? null : agent.getEnabled().getCode());
        query.setExternalCode(agent.getExternalCode());
        query.setMobile(agent.getMobile());
        query.setName(agent.getName());
        query.setPage(currentPage);
        query.setPageSize(size);
        PageList<AgentDO> ret = agentDAO.queryByPage(query);
        Collection<AgentVO> agents = mapper.do2vos(ret.getData());
        return PageUtils.createPageList(agents, ret.getPaginator());
    }

    /**
     * 查询所有代理商信息
     *
     * @return 所有代理商信息
     * @throws ServiceException 代理商查询异常
     */
    @Override
    public Collection<AgentVO> findAllAgents() throws ServiceException {
        List<AgentDO> agents = agentDAO.queryAllEnabled();
        return mapper.do2vos(agents);
    }

    /**
     * 更新代理商启用、禁用状态
     *
     * @param id       代理商ID
     * @param modifyBy 修改人
     * @param enabled  状态
     * @throws ServiceException 代理商状态更新异常
     */
    @Override
    public void updateAgentState(long id, String modifyBy, StateEnum enabled) throws ServiceException {
        AgentVO agent = new AgentVO();
        agent.setId(id);
        agent.setModifyBy(modifyBy);
        agent.setEnabled(enabled);
        updateAgent(agent);
    }

    /**
     * 删除代理商信息，不能恢复
     *
     * @param id 代理商ID
     * @throws ServiceException 代理商删除异常
     */
    @Override
    public void deleteAgent(long id) throws ServiceException {
        agentDAO.delete(id);
    }
}