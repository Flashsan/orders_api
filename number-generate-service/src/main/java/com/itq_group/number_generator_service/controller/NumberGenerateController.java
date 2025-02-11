package com.itq_group.number_generator_service.controller;


import com.itq_group.number_generator_service.dto.NumberGenerateDto;
import com.itq_group.number_generator_service.service.NumberGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/numbers")
@RequiredArgsConstructor
public class NumberGenerateController {

    private final NumberGenerateService numberGenerateService;

    @PostMapping()
    public NumberGenerateDto generateOrderNumber() {
        return numberGenerateService.generateOrderNumber();
    }
}
