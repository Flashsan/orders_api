package com.itq_group.order_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsEntity {
    @Id
    private Long id;
    private Long article;
    private String title;
    private int count;
    private BigDecimal price;
    private Long orderId;
}
