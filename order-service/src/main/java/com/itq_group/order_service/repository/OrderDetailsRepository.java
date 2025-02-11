package com.itq_group.order_service.repository;

import com.itq_group.order_service.entity.OrderDetailsEntity;

import java.util.List;

public interface OrderDetailsRepository {
    List<OrderDetailsEntity> getOrderDetailsByOrderId(Long orderId);
}
