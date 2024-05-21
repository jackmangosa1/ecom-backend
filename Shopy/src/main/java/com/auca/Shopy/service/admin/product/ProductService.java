package com.auca.Shopy.service.admin.product;

import com.auca.Shopy.dto.ProductDTO;
import com.auca.Shopy.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product getProductById(Long productId);
    void deleteProduct(Long productId);
    Product updateProduct(Long productId, ProductDTO productDTO);
    List<Product> getAllProducts();

}
