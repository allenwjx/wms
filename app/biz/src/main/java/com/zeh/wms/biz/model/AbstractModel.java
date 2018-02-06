package com.zeh.wms.biz.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author allen
 * @create $ ID: AbstractModel, 18/2/6 14:57 allen Exp $
 * @since 1.0.0
 */
public abstract class AbstractModel<T extends Serializable> {
    /**
     * Method toString ...
     * @return String
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}