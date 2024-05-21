package com.auca.Shopy.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long productId;
    private int quantity;
    private String size;
}