package com.auca.Shopy.dto;

import com.auca.Shopy.enums.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private Long id;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private UUID trackingId;
    private Long userId;
    private List<OrderItemDTO> orderItems;
}