package com.itq_group.order_service.controller;

import com.itq_group.order_service.dto.CreateOrderDto;
import com.itq_group.order_service.dto.OrderDto;
import com.itq_group.order_service.dto.OrderInfoDto;
import com.itq_group.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Tag(name = "OrderController", description = "Order APIs")
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInfoDto createOrder(@RequestBody final CreateOrderDto dto) {
        return orderService.save(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable final Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getOrdersByDateAndTotal(
            @RequestParam(value = "date", required = false) final LocalDate date,
            @RequestParam(value = "total", required = false) final BigDecimal total) {
        List<OrderDto> orders = orderService.findByOrderDateAndTotal(date, total);
        return orders;
    }

    @GetMapping("/exclude")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getOrdersWithoutProduct(
            @RequestParam(value = "startDate", required = false) final LocalDate startDate,
            @RequestParam(value = "endDate", required = false) final LocalDate endDate,
            @RequestParam(value = "good", required = false) final String good) {
        List<OrderDto> orders = orderService.getExcludeGoodAtFixTime(startDate, endDate, good);
        return orders;
    }



}
