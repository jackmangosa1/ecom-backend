package com.auca.Shopy.dto;
import com.auca.Shopy.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderResponseDTO {
    private Long id;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private int orderStatusValue;
    private UUID trackingId;
    private User user;
    private List<OrderItemResponseDTO> orderItems;
}
