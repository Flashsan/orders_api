package com.itq_group.order_service.feign_client;

import com.itq_group.order_service.dto.NumberGenerateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "number-generate-service", url = "http://localhost:8081/api/v1numbers")
public interface NumberGenerateClient {
    @PostMapping()
    NumberGenerateDto generateOrderNumber();

}
