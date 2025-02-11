package com.itq_group.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itq_group.order_service.entity.OrderDetailsEntity;
import com.itq_group.order_service.entity.enums.TypeOfDelivery;
import com.itq_group.order_service.entity.enums.TypeOfPayment;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderDto {
    private String number;
    private BigDecimal total;
    private LocalDate orderDate;
    private String recipient;
    private String addressDelivery;
    private TypeOfPayment typeOfPayment;
    private TypeOfDelivery typeOfDelivery;
    private List<CreateOrderDetailsDto> orderDetails;



}
