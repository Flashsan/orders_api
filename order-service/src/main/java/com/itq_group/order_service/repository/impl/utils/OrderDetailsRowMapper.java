package com.itq_group.order_service.repository.impl.utils;

import com.itq_group.order_service.entity.OrderDetailsEntity;
import com.itq_group.order_service.entity.OrderEntity;
import com.itq_group.order_service.entity.enums.TypeOfDelivery;
import com.itq_group.order_service.entity.enums.TypeOfPayment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRowMapper implements RowMapper<OrderDetailsEntity> {

    @Override
    public OrderDetailsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderDetailsEntity orderDetails = new OrderDetailsEntity();
        orderDetails.setId(rs.getLong("id"));
        orderDetails.setArticle(rs.getLong("article"));
        orderDetails.setTitle(rs.getString("title"));
        orderDetails.setCount(rs.getInt("count"));
        orderDetails.setPrice(rs.getBigDecimal("price"));
        return orderDetails;
    }
}
