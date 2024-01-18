package com.g7.framework.mongo.reactive.doc;

import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author dreamyao
 * @title
 * @date 2022/03/04 下午14:43
 * @since 1.0.0
 */
public abstract class AbstractVersionDoc<T extends Serializable> extends AbstractDoc<T> {

    // 版本，使用做乐观锁用
    @Version @Field(value = "version") private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
