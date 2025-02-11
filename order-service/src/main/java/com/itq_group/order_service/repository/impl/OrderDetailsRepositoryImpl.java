package com.itq_group.order_service.repository.impl;

import com.itq_group.order_service.entity.OrderDetailsEntity;
import com.itq_group.order_service.entity.OrderEntity;
import com.itq_group.order_service.repository.OrderDetailsRepository;
import com.itq_group.order_service.repository.OrderRepository;
import com.itq_group.order_service.repository.impl.utils.OrderDetailsRowMapper;
import com.itq_group.order_service.repository.impl.utils.OrderRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderDetailsEntity> getOrderDetailsByOrderId(Long orderId) {
        String sql = "SELECT id, article, title, count, price, order_id FROM orders_details WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, new OrderDetailsRowMapper());
    }

}