package com.itq_group.number_generator_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberGenerateDto {
    private String orderNumber;
}
