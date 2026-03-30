package com.test.springapp.domain.model;

import com.test.springapp.domain.common.BaseEntity;
import com.test.springapp.domain.common.AggregateRoot;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Product aggregate root.
 * Represents a product in the domain.
 */
@Getter
public class Product extends BaseEntity<Long> implements AggregateRoot<Long> {
    
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Instant createdAt;
    private Instant updatedAt;
    
    // Constructor for JPA/ORM
    protected Product() {
    }
    
    // Factory method for creating new products
    public static Product create(String name, String description, BigDecimal price, Integer stockQuantity) {
        Product product = new Product();
        product.name = name;
        product.description = description;
        product.price = price;
        product.stockQuantity = stockQuantity;
        product.createdAt = Instant.now();
        product.updatedAt = Instant.now();
        
        product.validate();
        return product;
    }
    
    // Business methods
    public void updateDetails(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.updatedAt = Instant.now();
        validate();
    }
    
    public void adjustStock(Integer quantity) {
        if (this.stockQuantity + quantity < 0) {
            throw new IllegalStateException(
                String.format("Insufficient stock: current quantity is %d, requested adjustment is %d", 
                    this.stockQuantity, quantity)
            );
        }
        this.stockQuantity += quantity;
        this.updatedAt = Instant.now();
    }
    
    public boolean isInStock() {
        return stockQuantity > 0;
    }
    
    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price must be non-negative");
        }
        if (stockQuantity == null || stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity must be non-negative");
        }
    }
}
