package com.auca.Shopy.service.customer;


import com.auca.Shopy.dto.ProductDTO;
import com.auca.Shopy.model.Product;
import com.auca.Shopy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<ProductDTO> getProductByname(String name){
        return productRepository.findProductsByName(name);
    }
}
