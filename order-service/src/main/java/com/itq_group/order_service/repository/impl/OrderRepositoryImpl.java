package com.itq_group.order_service.repository.impl;

import com.itq_group.order_service.entity.OrderDetailsEntity;
import com.itq_group.order_service.entity.OrderEntity;
import com.itq_group.order_service.repository.OrderRepository;
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
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String sqlSave = "INSERT INTO orders (number, total, order_date, recipient, address_delivery, type_of_payment, type_of_delivery) VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";
    private final String sqlSaveOrderDetails = "INSERT INTO orders_details (article,title,count,price,order_id) VALUES (?, ?, ?, ?, ?)";
    private final String sqlFindById = "SELECT * FROM orders WHERE id=?";
    private final String sqlFindByOrderDateAndTotal = "SELECT * FROM orders WHERE order_date = ? AND total > ?";
    private final String sqlGetExcludeGoodAtFixTime = """
                                   SELECT DISTINCT o.* FROM orders o
                                   WHERE o.order_date BETWEEN ? AND ?
                                   AND NOT EXISTS (
                                       SELECT 1 FROM orders_details od WHERE od.order_id = o.id AND LOWER(od.title) = LOWER(?) 
                                   )
                """;

    @Override
    public int save(OrderEntity orders) {
        Integer orderId =
                jdbcTemplate.queryForObject(
                        sqlSave,
                        new Object[]{orders.getNumber(), orders.getTotal(), orders.getOrderDate(), orders.getRecipient(), orders.getAddressDelivery(), orders.getTypeOfPayment().name(), orders.getTypeOfDelivery().name()}, Integer.class);

        if (orderId == null) {
            throw new RuntimeException("Id null");
        }

        saveOrderDetails(Long.valueOf(orderId), orders.getOrderDetails());
        return orderId;
    }

    private void saveOrderDetails(Long orderId, List<OrderDetailsEntity> ordersDetails) {
        for (OrderDetailsEntity detail : ordersDetails) {
            jdbcTemplate.update(sqlSaveOrderDetails,
                    detail.getArticle(), detail.getTitle(), detail.getCount(), detail.getPrice(), orderId);
        }
    }

    @Override
    public OrderEntity findById(Long id) {
        try {
            OrderEntity order = jdbcTemplate.queryForObject(
                    sqlFindById,
                    BeanPropertyRowMapper.newInstance(OrderEntity.class), id);
            return order;
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<OrderEntity> findByOrderDateAndTotal(LocalDate orderDate, BigDecimal total) {
        return jdbcTemplate.query(sqlFindByOrderDateAndTotal, new OrderRowMapper(), orderDate, total);
    }

    @Override
    public List<OrderEntity> getExcludeGoodAtFixTime(LocalDate startDate, LocalDate endDate, String good) {
        return jdbcTemplate.query(sqlGetExcludeGoodAtFixTime, new OrderRowMapper(), startDate, endDate, good);
    }


}