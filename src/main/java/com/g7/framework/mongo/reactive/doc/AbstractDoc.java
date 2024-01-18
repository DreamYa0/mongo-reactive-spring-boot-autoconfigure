package com.g7.framework.mongo.reactive.doc;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dreamyao
 * @title 表公共基础字段
 * @date 2022/03/04 下午14:43
 * @since 1.0.0
 */
public abstract class AbstractDoc<T extends Serializable> implements Persistable<T> {

    // 主键ID
    @MongoId @Field(value = "id") private @Nullable T id;
    // 创建时间
    @CreatedDate @Field(value = "gmtCreate") private Date gmtCreate;
    // 更新时间
    @LastModifiedDate @Field(value = "gmtModified") private Date gmtModified;

    @Nullable
    @Override
    public T getId() {
        return id;
    }

    public void setId(@Nullable T id) {
        this.id = id;
    }

    @Nullable
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(@Nullable Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Nullable
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(@Nullable Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Transient
    @Override
    public boolean isNew() {
        return null == getId();
    }
}
