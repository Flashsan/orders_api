package com.itq_group.order_service.service;

import com.itq_group.order_service.dto.CreateOrderDto;
import com.itq_group.order_service.dto.OrderDto;
import com.itq_group.order_service.dto.OrderInfoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderInfoDto save(CreateOrderDto createOrderDto);

    OrderDto findById(Long id);

    List<OrderDto> findByOrderDateAndTotal(LocalDate orderDate, BigDecimal total);

    List<OrderDto> getExcludeGoodAtFixTime(LocalDate startDate, LocalDate endDate, String good);

}
