package com.test.springapp.application.service;

import com.test.springapp.application.dto.CreateProductDTO;
import com.test.springapp.application.dto.ProductDTO;
import com.test.springapp.application.dto.UpdateProductDTO;
import com.test.springapp.domain.model.Product;
import com.test.springapp.domain.model.ProductRepository;
import com.test.springapp.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application service for managing products.
 * Orchestrates use cases and coordinates domain objects.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements ApplicationService {
    
    private final ProductRepository productRepository;
    
    public ProductDTO createProduct(CreateProductDTO dto) {
        Product product = Product.create(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStockQuantity()
        );
        
        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }
    
    public ProductDTO updateProduct(Long id, UpdateProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        
        product.updateDetails(dto.getName(), dto.getDescription(), dto.getPrice());
        
        Product updatedProduct = productRepository.save(product);
        return toDTO(updatedProduct);
    }
    
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product", id);
        }
        productRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        return toDTO(product);
    }
    
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ProductDTO> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ProductDTO> getInStockProducts() {
        return productRepository.findInStock().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public ProductDTO adjustStock(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        
        product.adjustStock(quantity);
        
        Product updatedProduct = productRepository.save(product);
        return toDTO(updatedProduct);
    }
    
    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.isInStock(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
