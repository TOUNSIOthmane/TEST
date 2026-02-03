package com.test.springapp.presentation.rest;

import com.test.springapp.application.dto.CreateProductDTO;
import com.test.springapp.application.dto.ProductDTO;
import com.test.springapp.application.dto.UpdateProductDTO;
import com.test.springapp.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for product management.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO dto) {
        ProductDTO product = productService.createProduct(dto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String keyword) {
        List<ProductDTO> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/in-stock")
    public ResponseEntity<List<ProductDTO>> getInStockProducts() {
        List<ProductDTO> products = productService.getInStockProducts();
        return ResponseEntity.ok(products);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody UpdateProductDTO dto) {
        ProductDTO product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(product);
    }
    
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductDTO> adjustStock(@PathVariable Long id, @RequestParam Integer quantity) {
        ProductDTO product = productService.adjustStock(id, quantity);
        return ResponseEntity.ok(product);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
