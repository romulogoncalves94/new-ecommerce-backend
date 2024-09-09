package com.ecommerce.ecom.repository;

import com.ecommerce.ecom.dto.OrderDTO;
import com.ecommerce.ecom.entity.Order;
import com.ecommerce.ecom.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
    List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatusList);
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
    Optional<Order> findByTrackingId(UUID trackingId);
    List<Order> findByDateBetweenAndOrderStatus(Date startDate, Date endDate, OrderStatus orderStatus);
    Long countByOrderStatus(OrderStatus orderStatus);

}
