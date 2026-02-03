package com.test.springapp.domain.common;

import java.io.Serializable;

/**
 * Marker interface for aggregate roots.
 * An aggregate root is the main entity in an aggregate that ensures consistency boundaries.
 * All access to the aggregate should go through the aggregate root.
 */
public interface AggregateRoot<ID extends Serializable> extends Entity<ID> {
}
