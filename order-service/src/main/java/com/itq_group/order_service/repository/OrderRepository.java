package com.itq_group.order_service.repository;

import com.itq_group.order_service.entity.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    int save(OrderEntity order);

    OrderEntity findById(Long id);

    List<OrderEntity> findByOrderDateAndTotal(LocalDate orderDate, BigDecimal total);

    List<OrderEntity> getExcludeGoodAtFixTime(LocalDate startDate, LocalDate endDate, String good);
}
