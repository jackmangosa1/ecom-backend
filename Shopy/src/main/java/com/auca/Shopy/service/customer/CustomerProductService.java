package com.auca.Shopy.service.customer;

import com.auca.Shopy.dto.ProductDTO;
import com.auca.Shopy.model.Product;

import java.util.List;

public interface CustomerProductService {
    List<Product> getAllProducts();
    List<ProductDTO> getProductByname(String name);
}
