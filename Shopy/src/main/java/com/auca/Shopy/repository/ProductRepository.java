package com.auca.Shopy.repository;

import com.auca.Shopy.dto.ProductDTO;
import com.auca.Shopy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductDTO> findProductsByName(String name);
}
