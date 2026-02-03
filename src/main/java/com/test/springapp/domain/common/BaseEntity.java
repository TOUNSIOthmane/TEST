package com.test.springapp.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * Base implementation for domain entities.
 * Provides common functionality for all entities.
 * 
 * @param <ID> the identifier type
 */
@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity<ID extends Serializable> implements Entity<ID> {
    
    protected ID id;
    
    protected BaseEntity() {
    }
    
    protected BaseEntity(ID id) {
        this.id = id;
    }
    
    @Override
    public ID getId() {
        return id;
    }
}
