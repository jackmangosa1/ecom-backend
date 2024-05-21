package com.auca.Shopy.dto;

import lombok.Data;

@Data
public class AddProductInCartDTO {
    private Long userId;
    private Long productId;
    private int sizeIndex;
}
