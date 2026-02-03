package com.test.springapp.domain.common;

import java.io.Serializable;

/**
 * Base interface for all domain entities.
 * Entities have a unique identifier and lifecycle.
 */
public interface Entity<ID extends Serializable> {
    
    /**
     * Returns the unique identifier of this entity.
     * @return the entity ID
     */
    ID getId();
    
    /**
     * Checks if this entity is new (not yet persisted).
     * @return true if the entity is new, false otherwise
     */
    default boolean isNew() {
        return getId() == null;
    }
}
