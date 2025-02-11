package com.itq_group.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderDetailsDto {
    private Long article;
    private String title;
    private int count;
    private BigDecimal price;



}
