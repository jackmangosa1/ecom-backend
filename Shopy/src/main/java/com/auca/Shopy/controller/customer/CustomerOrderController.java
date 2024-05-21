package com.auca.Shopy.controller.customer;


import com.auca.Shopy.dto.OrderItemDTO;
import com.auca.Shopy.dto.OrderItemResponseDTO;
import com.auca.Shopy.dto.OrderItemResponseDTO;
import com.auca.Shopy.dto.OrderRequestDTO;
import com.auca.Shopy.dto.OrderResponseDTO;
import com.auca.Shopy.enums.OrderStatus;
import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.OrderItem;
import com.auca.Shopy.repository.ProductRepository;
import com.auca.Shopy.service.customer.CustomerOrderService;
import com.auca.Shopy.service.customer.CustomerOrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer/orders")
@RequiredArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;
    private final ProductRepository productRepository;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        List<OrderItemDTO> orderItemDTOs = orderRequest.getOrderItems();
        String address = orderRequest.getAddress();
        String paymentMethod = orderRequest.getPaymentMethod();
        Long userId = orderRequest.getUserId();

        Order order = customerOrderService.createOrder(orderItemDTOs, address, paymentMethod, userId);



        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }



    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {
        Order order = customerOrderService.getOrderById(orderId);
        if (order != null) {
            int orderStatusValue = CustomerOrderServiceImpl.getOrderStatusValue(order.getOrderStatus());
            OrderResponseDTO orderResponse = new OrderResponseDTO();
            orderResponse.setId(order.getId());
            orderResponse.setDate(order.getDate());
            orderResponse.setAmount(order.getAmount());
            orderResponse.setAddress(order.getAddress());
            orderResponse.setPayment(order.getPayment());
            orderResponse.setOrderStatusValue(orderStatusValue);
            orderResponse.setTrackingId(order.getTrackingId());
            orderResponse.setUser(order.getUser());

            List<OrderItemResponseDTO> orderItemResponseDTOs = new ArrayList<>();
            if (order.getOrderItems() != null) {
                orderItemResponseDTOs = order.getOrderItems().stream()
                        .map(this::mapToOrderItemResponseDTO)
                        .collect(Collectors.toList());
            }
            orderResponse.setOrderItems(orderItemResponseDTOs);

            return ResponseEntity.ok(orderResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = customerOrderService.getAllOrders();
        if (!orders.isEmpty()) {
            List<OrderResponseDTO> orderResponses = orders.stream()
                    .map(this::mapToOrderResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(orderResponses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{orderId}/status/{newStatus}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus newStatus) {
        if (customerOrderService.updateOrderStatus(orderId, newStatus)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderResponseDTO mapToOrderResponse(Order order) {
        int orderStatusValue = CustomerOrderServiceImpl.getOrderStatusValue(order.getOrderStatus());
        OrderResponseDTO orderResponse = new OrderResponseDTO();
        orderResponse.setId(order.getId());
        orderResponse.setDate(order.getDate());
        orderResponse.setAmount(order.getAmount());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setPayment(order.getPayment());
        orderResponse.setOrderStatusValue(orderStatusValue);
        orderResponse.setTrackingId(order.getTrackingId());
        orderResponse.setUser(order.getUser());

        List<OrderItemResponseDTO> orderItemResponses;
        if (order.getOrderItems() != null) {
            orderItemResponses = order.getOrderItems().stream()
                    .map(orderItem -> {
                        OrderItemResponseDTO orderItemResponse = new OrderItemResponseDTO();
                        orderItemResponse.setProductId(orderItem.getProduct().getId());
                        orderItemResponse.setProductName(orderItem.getProduct().getName());
                        orderItemResponse.setQuantity(orderItem.getQuantity());
                        orderItemResponse.setSize(orderItem.getSize());
                        return orderItemResponse;
                    })
                    .collect(Collectors.toList());
        } else {
            orderItemResponses = new ArrayList<>();
        }

        orderResponse.setOrderItems(orderItemResponses);
        return orderResponse;
    }

    private OrderItemResponseDTO mapToOrderItemResponseDTO(OrderItem orderItem) {
        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
        orderItemResponseDTO.setProductId(orderItem.getProduct().getId());
        orderItemResponseDTO.setProductName(orderItem.getProduct().getName());
        orderItemResponseDTO.setQuantity(orderItem.getQuantity());
        orderItemResponseDTO.setSize(orderItem.getSize());
        return orderItemResponseDTO;
    }


}
