package com.auca.Shopy.service.admin.product;

import com.auca.Shopy.dto.ProductDTO;
import com.auca.Shopy.model.Product;
import com.auca.Shopy.repository.CategoryRepository;
import com.auca.Shopy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());

        product.setCategory(categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + productDTO.getCategory().getId()
                        + " does not exist.")));

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
public Product getProductById(Long productId) {
    return productRepository.findById(productId)
            .orElse(null); // Returns null if product with given ID is not found
}
//    public void deleteProduct(Long productId) {
//
//        if (productRepository.existsById(productId)) {
//            productRepository.deleteById(productId);
//        } else {
//            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
//        }
//    }


    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " does not exist."));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());

        product.setCategory(categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + productDTO.getCategory().getId() + " does not exist.")));

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}