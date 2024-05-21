package com.auca.Shopy.service.customer;

import com.auca.Shopy.dto.OrderItemDTO;
import com.auca.Shopy.enums.OrderStatus;
import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.OrderItem;

import java.util.List;

public interface CustomerOrderService {
    Order createOrder(List<OrderItemDTO> orderItemDTOs, String address, String paymentMethod, Long userId);
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
    boolean updateOrderStatus(Long orderId, OrderStatus newStatus);
}
