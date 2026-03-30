package com.test.springapp.domain.model;

import com.test.springapp.domain.common.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Product aggregate.
 * Defines domain-specific query methods.
 */
public interface ProductRepository extends Repository<Product, Long> {
    
    /**
     * Finds all products.
     * @return list of all products
     */
    List<Product> findAll();
    
    /**
     * Finds products by name containing the given keyword.
     * @param keyword the keyword to search for
     * @return list of matching products
     */
    List<Product> findByNameContaining(String keyword);
    
    /**
     * Finds products that are in stock.
     * @return list of products in stock
     */
    List<Product> findInStock();
}
