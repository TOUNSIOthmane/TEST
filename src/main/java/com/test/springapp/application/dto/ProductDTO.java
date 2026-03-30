package com.test.springapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for product response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements DTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean inStock;
    private Instant createdAt;
    private Instant updatedAt;
}
