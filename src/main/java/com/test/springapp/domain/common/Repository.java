package com.test.springapp.domain.common;

import java.io.Serializable;
import java.util.Optional;

/**
 * Base interface for all repositories.
 * Repositories provide the illusion of an in-memory collection of aggregate roots.
 * 
 * @param <T> the aggregate root type
 * @param <ID> the identifier type
 */
public interface Repository<T extends AggregateRoot<ID>, ID extends Serializable> {
    
    /**
     * Saves the given aggregate root.
     * @param aggregate the aggregate root to save
     * @return the saved aggregate root
     */
    T save(T aggregate);
    
    /**
     * Finds an aggregate root by its identifier.
     * @param id the identifier
     * @return an Optional containing the aggregate root if found
     */
    Optional<T> findById(ID id);
    
    /**
     * Checks if an aggregate root exists by its identifier.
     * @param id the identifier
     * @return true if the aggregate root exists, false otherwise
     */
    boolean existsById(ID id);
    
    /**
     * Deletes the given aggregate root.
     * @param aggregate the aggregate root to delete
     */
    void delete(T aggregate);
    
    /**
     * Deletes an aggregate root by its identifier.
     * @param id the identifier
     */
    void deleteById(ID id);
}
