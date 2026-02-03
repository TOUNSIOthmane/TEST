package com.test.springapp.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for ProductEntity.
 */
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    
    List<ProductEntity> findByNameContainingIgnoreCase(String keyword);
    
    @Query("SELECT p FROM ProductEntity p WHERE p.stockQuantity > 0")
    List<ProductEntity> findInStock();
}
