package com.itq_group.order_service.repository.impl.utils;

import com.itq_group.order_service.entity.OrderEntity;
import com.itq_group.order_service.entity.enums.TypeOfDelivery;
import com.itq_group.order_service.entity.enums.TypeOfPayment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<OrderEntity> {

    @Override
        public OrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderEntity order = new OrderEntity();
            order.setId(rs.getLong("id"));
            order.setNumber(rs.getString("number"));
            order.setTotal(rs.getBigDecimal("total"));
            order.setOrderDate(rs.getDate("order_date").toLocalDate());
            order.setRecipient(rs.getString("recipient"));
            order.setAddressDelivery(rs.getString("address_delivery"));
            order.setTypeOfPayment(TypeOfPayment.valueOf(rs.getString("type_of_payment")));
            order.setTypeOfDelivery(TypeOfDelivery.valueOf(rs.getString("type_of_delivery")));
            return order;
        }
}
