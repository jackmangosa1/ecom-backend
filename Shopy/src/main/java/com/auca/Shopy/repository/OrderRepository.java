package com.auca.Shopy.repository;

import com.auca.Shopy.enums.OrderStatus;
import com.auca.Shopy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUserIdAndOrderStatus(Long userid, OrderStatus orderStatus);
}
