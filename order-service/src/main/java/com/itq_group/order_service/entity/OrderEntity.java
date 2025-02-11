package com.itq_group.order_service.entity;

import com.itq_group.order_service.entity.enums.TypeOfDelivery;
import com.itq_group.order_service.entity.enums.TypeOfPayment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    private Long id;
    private String number;
    private BigDecimal total;
    private LocalDate orderDate;
    private String recipient;
    private String addressDelivery;
    private TypeOfPayment typeOfPayment;
    private TypeOfDelivery typeOfDelivery;
    private List<OrderDetailsEntity> orderDetails;

}
