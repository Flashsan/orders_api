package com.itq_group.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsDto {
    private Long article;
    private String title;
    private int count;
    private BigDecimal price;
    private Long orderId;
}
