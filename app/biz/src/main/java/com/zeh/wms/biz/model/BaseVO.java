package com.zeh.wms.biz.model;

import java.util.Date;

import lombok.Data;

/**
 * 基础数据模型
 * 
 * @author allen
 * @create $ ID: BaseVO, 18/2/6 14:59 allen Exp $
 * @since 1.0.0
 */
@Data
public class BaseVO extends AbstractModel<Long> {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 主键  */
    private long              id;
    /** 创建时间 */
    private Date              gmtCreate;
    /** 修改时间 */
    private Date              gmtModified;
    /** 创建人 */
    private String            createBy;
    /** 修改人 */
    private String            modifyBy;

    /**
     * 获取领域模型ID
     *
     * @return 返回ID
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 设置领域模型ID
     *
     * @param id 领域模型ID
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }
}