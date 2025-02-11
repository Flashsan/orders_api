package com.itq_group.order_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberGenerateDto {
    private String orderNumber;
}
