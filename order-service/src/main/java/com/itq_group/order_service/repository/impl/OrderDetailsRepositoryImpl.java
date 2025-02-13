package com.itq_group.order_service.repository.impl;

import com.itq_group.order_service.entity.OrderDetailsEntity;
import com.itq_group.order_service.repository.OrderDetailsRepository;
import com.itq_group.order_service.repository.impl.utils.OrderDetailsRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String sqlGetOrderDetailsByOrderId = "SELECT id, article, title, count, price, order_id FROM orders_details WHERE order_id = ?";

    @Override
    public List<OrderDetailsEntity> getOrderDetailsByOrderId(Long orderId) {
        return jdbcTemplate.query(sqlGetOrderDetailsByOrderId, new Object[]{orderId}, new OrderDetailsRowMapper());
    }

}