package com.test.springapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for updating a product.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO implements DTO {
    private String name;
    private String description;
    private BigDecimal price;
}
