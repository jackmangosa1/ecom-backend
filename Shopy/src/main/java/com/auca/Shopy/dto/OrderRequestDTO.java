package com.auca.Shopy.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

        private List<OrderItemDTO> orderItems;
        private String address;
        private String paymentMethod;
        private Long userId;

}
