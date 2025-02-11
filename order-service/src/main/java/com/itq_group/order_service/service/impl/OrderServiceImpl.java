package com.itq_group.order_service.service.impl;

import com.itq_group.order_service.dto.*;

import com.itq_group.order_service.entity.OrderEntity;
import com.itq_group.order_service.feign_client.NumberGenerateClient;
import com.itq_group.order_service.mapper.OrderMapper;
import com.itq_group.order_service.repository.OrderDetailsRepository;
import com.itq_group.order_service.repository.OrderRepository;
import com.itq_group.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final NumberGenerateClient numberGenerateClient;
    private final OrderMapper orderMapper;
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderInfoDto save(CreateOrderDto createOrderDto) {
        NumberGenerateDto numberGenerateDto = numberGenerateClient.generateOrderNumber();
        createOrderDto.setNumber(numberGenerateDto.getOrderNumber());


        createOrderDto.setTotal(countTotal(createOrderDto.getOrderDetails()));
        int id = orderRepository.save(orderMapper.toOrderEntity(createOrderDto));

        return OrderInfoDto.builder()
                .id((long) id)
                .build();
    }

    private BigDecimal countTotal(List<CreateOrderDetailsDto> list) {
        List<BigDecimal> bigDecimalList = list.stream()
                .map(dto -> {
                    BigDecimal c = new BigDecimal(dto.getCount());
                    return dto.getPrice().multiply(c);
                })
                .collect(Collectors.toList());

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal number : bigDecimalList) {
            sum = sum.add(number);
        }

        return sum;
    }


    @Transactional
    @Override
    public OrderDto findById(Long id) {
        OrderEntity order = orderRepository.findById(id);
        order.setOrderDetails(orderDetailsRepository.getOrderDetailsByOrderId(order.getId()));
        return orderMapper.toOrderDto(order);
    }

    @Override
    public List<OrderDto> findByOrderDateAndTotal(LocalDate orderDate, BigDecimal total) {
        return orderRepository.findByOrderDateAndTotal(orderDate, total)
                .stream()
                .map(entity -> {
                    entity.setOrderDetails(orderDetailsRepository.getOrderDetailsByOrderId(entity.getId()));
                    return orderMapper.toOrderDto(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getExcludeGoodAtFixTime(LocalDate startDate, LocalDate endDate, String good) {
        return orderRepository.getExcludeGoodAtFixTime(startDate, endDate, good)
                .stream()
                .map(entity -> {
                    entity.setOrderDetails(orderDetailsRepository.getOrderDetailsByOrderId(entity.getId()));
                    return orderMapper.toOrderDto(entity);
                })
                .collect(Collectors.toList());
    }

}
