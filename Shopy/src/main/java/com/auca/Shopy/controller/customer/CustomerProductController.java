package com.auca.Shopy.controller.customer;

import com.auca.Shopy.dto.ProductDTO;
import com.auca.Shopy.model.Product;
import com.auca.Shopy.service.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = customerProductService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@PathVariable String name) {
        List<ProductDTO> products = customerProductService.getProductByname(name);
        return ResponseEntity.ok(products);
    }


}
