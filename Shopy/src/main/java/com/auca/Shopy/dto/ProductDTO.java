package com.auca.Shopy.dto;
import com.auca.Shopy.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Long[] price;
    private String description;
    private byte[] image;
    private Category category;
}
