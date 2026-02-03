package com.test.springapp.infrastructure.repository;

import com.test.springapp.domain.model.Product;
import com.test.springapp.domain.model.ProductRepository;
import com.test.springapp.infrastructure.persistence.JpaProductRepository;
import com.test.springapp.infrastructure.persistence.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of ProductRepository using JPA.
 * Adapts between domain model and persistence model.
 */
@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    
    private final JpaProductRepository jpaRepository;
    
    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
    
    @Override
    public void delete(Product product) {
        jpaRepository.deleteById(product.getId());
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Product> findByNameContaining(String keyword) {
        return jpaRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Product> findInStock() {
        return jpaRepository.findInStock().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    // Mapping methods
    private Product toDomain(ProductEntity entity) {
        Product product = Product.create(
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity()
        );
        // Use reflection to set ID (or provide a constructor in Product)
        try {
            var idField = Product.class.getSuperclass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(product, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to set product ID", e);
        }
        return product;
    }
    
    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStockQuantity(product.getStockQuantity());
        entity.setCreatedAt(product.getCreatedAt());
        entity.setUpdatedAt(product.getUpdatedAt());
        return entity;
    }
}
