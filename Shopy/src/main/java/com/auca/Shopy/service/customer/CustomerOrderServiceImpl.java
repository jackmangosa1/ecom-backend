package com.auca.Shopy.service.customer;

import com.auca.Shopy.dto.OrderItemDTO;
import com.auca.Shopy.enums.OrderStatus;
import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.OrderItem;
import com.auca.Shopy.model.Product;
import com.auca.Shopy.repository.OrderRepository;
import com.auca.Shopy.repository.ProductRepository;
import com.auca.Shopy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public static int getOrderStatusValue(OrderStatus orderStatus) {
        switch (orderStatus) {
            case Cooking:
                return 1;
            case OnWay:
                return 2;
            case Delivered:
                return 4;
            default:
                return 0;
        }
    }

    public Order createOrder(List<OrderItemDTO> orderItemDTOs, String address, String paymentMethod, Long userId) {
        // Create a new Order entity
        Order order = new Order();
        order.setDate(new Date());
        order.setAmount(calculateTotalAmount(orderItemDTOs)); // Set total amount
        order.setAddress(address);
        order.setPayment(paymentMethod);
        order.setOrderStatus(OrderStatus.Cooking); // Set initial order status
        order.setTrackingId(UUID.randomUUID()); // Generate a random tracking ID
        order.setUser(userRepository.findById(userId).orElse(null)); // Set user for the order

        // Convert OrderItemDTOs to OrderItems and set the order
        List<OrderItem> orderItems = orderItemDTOs.stream()
                .map(this::convertToOrderItem)
                .peek(orderItem -> orderItem.setOrder(order))
                .toList();

        order.setOrderItems(orderItems);

        // Save the order to the database
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public boolean updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setOrderStatus(newStatus);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    private OrderItem convertToOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setSize(orderItemDTO.getSize());

        Long productId = orderItemDTO.getProductId();
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            orderItem.setProduct(product);
        }

        return orderItem;
    }
    public CustomerOrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    private Long calculateTotalAmount(List<OrderItemDTO> orderItemDTOs) {
        return orderItemDTOs.stream()
                .mapToLong(orderItemDTO -> {
                    Long productId = orderItemDTO.getProductId();
                    Product product = productRepository.findById(productId).orElse(null);
                    if (product != null) {
                        Long[] prices = product.getPrice();
                        int sizeIndex = getSizeIndex(orderItemDTO.getSize());
                        int quantity = orderItemDTO.getQuantity();
                        return (sizeIndex >= 0 && sizeIndex < prices.length) ? prices[sizeIndex] * quantity : 0;
                    }
                    return 0;
                })
                .sum();
    }

    private int getSizeIndex(String size) {
        switch (size) {
            case "small":
                return 0;
            case "medium":
                return 1;
            case "large":
                return 2;
            default:
                return -1; // Invalid size
        }
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
