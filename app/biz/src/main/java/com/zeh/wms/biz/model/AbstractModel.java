package com.zeh.wms.biz.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.zeh.jungle.core.Identifiable;

/**
 * @author allen
 * @create $ ID: AbstractModel, 18/2/6 14:57 allen Exp $
 * @since 1.0.0
 */
public abstract class AbstractModel<T extends Serializable> implements Identifiable<T> {
    /**  */
    private static final long serialVersionUID = 1L;

    /**
     * Method toString ...
     * @return String
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}