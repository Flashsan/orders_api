package com.itq_group.order_service.dto;


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
public class OrderDto {
    private String number;
    private BigDecimal total;
    private LocalDate orderDate;
    private String recipient;
    private String addressDelivery;
    private TypeOfPayment typeOfPayment;
    private TypeOfDelivery typeOfDelivery;
    private List<OrderDetailsDto> orderDetails;

}
