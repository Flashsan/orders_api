package com.itq_group.order_service;

import com.itq_group.order_service.dto.*;
import com.itq_group.order_service.entity.OrderEntity;
import com.itq_group.order_service.feign_client.NumberGenerateClient;
import com.itq_group.order_service.mapper.OrderMapper;
import com.itq_group.order_service.repository.impl.OrderDetailsRepositoryImpl;
import com.itq_group.order_service.repository.impl.OrderRepositoryImpl;
import com.itq_group.order_service.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepositoryImpl orderRepository;

    @Mock
    private OrderDetailsRepositoryImpl orderDetailsRepository;

    @Mock
    private NumberGenerateClient numberGenerateClient;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private CreateOrderDto createOrderDto;
    private CreateOrderDetailsDto createOrderDetailsDto;
    private OrderEntity orderEntity;
    private OrderInfoDto orderInfoDto;
    private OrderDto orderDto;

    @BeforeEach
    void setup() {
        createOrderDetailsDto = new CreateOrderDetailsDto();
        createOrderDetailsDto.setArticle(123L);
        createOrderDetailsDto.setTitle("Товар");
        createOrderDetailsDto.setPrice(BigDecimal.valueOf(250));
        createOrderDetailsDto.setCount(2);


        createOrderDto = new CreateOrderDto();
        createOrderDto.setTotal(BigDecimal.valueOf(250));
        createOrderDto.setOrderDate(LocalDate.now());
        createOrderDto.setOrderDetails(List.of(createOrderDetailsDto));


        orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setNumber("0000020250211");
        orderEntity.setTotal(BigDecimal.valueOf(100));
        orderEntity.setOrderDate(LocalDate.now());
        orderEntity.setOrderDetails(null);

        orderInfoDto = OrderInfoDto.builder().id(1L).build();
        orderDto = new OrderDto();
        orderDto.setTotal(BigDecimal.valueOf(100));
        orderDto.setNumber("0000020250211");
    }

    @Test
    void testSaveOrder() {
        NumberGenerateDto numberGenerateDto = new NumberGenerateDto();
        numberGenerateDto.setOrderNumber("0000020250211");

        when(numberGenerateClient.generateOrderNumber()).thenReturn(numberGenerateDto);
        when(orderMapper.toOrderEntity(createOrderDto)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(1);

        OrderInfoDto result = orderService.save(createOrderDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_OrderFound() {
        when(orderRepository.findById(1L)).thenReturn(orderEntity);
        when(orderDetailsRepository.getOrderDetailsByOrderId(1L)).thenReturn(Collections.emptyList());
        when(orderMapper.toOrderDto(orderEntity)).thenReturn(orderDto);

        OrderDto result = orderService.findById(1L);

        assertNotNull(result);
        assertEquals("0000020250211", result.getNumber());
    }

    @Test
    void testFindById_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> orderService.findById(1L));
    }

    @Test
    void testFindByOrderDateAndTotal() {
        when(orderRepository.findByOrderDateAndTotal(any(LocalDate.class), any(BigDecimal.class)))
                .thenReturn(List.of(orderEntity));
        when(orderDetailsRepository.getOrderDetailsByOrderId(1L)).thenReturn(Collections.emptyList());
        when(orderMapper.toOrderDto(orderEntity)).thenReturn(orderDto);

        List<OrderDto> result = orderService.findByOrderDateAndTotal(LocalDate.now(), BigDecimal.valueOf(50));

        assertThat(result).hasSize(1);
        assertEquals("0000020250211", result.get(0).getNumber());
    }

    @Test
    void testGetExcludeGoodAtFixTime() {
        when(orderRepository.getExcludeGoodAtFixTime(any(LocalDate.class), any(LocalDate.class), anyString()))
                .thenReturn(List.of(orderEntity));
        when(orderDetailsRepository.getOrderDetailsByOrderId(1L)).thenReturn(Collections.emptyList());
        when(orderMapper.toOrderDto(orderEntity)).thenReturn(orderDto);

        List<OrderDto> result = orderService.getExcludeGoodAtFixTime(LocalDate.now(), LocalDate.now(), "Product A");

        assertThat(result).hasSize(1);
        assertEquals("0000020250211", result.get(0).getNumber());
    }
}