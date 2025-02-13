package com.itq_group.number_generator_service.controller;


import com.itq_group.number_generator_service.dto.NumberGenerateDto;
import com.itq_group.number_generator_service.service.NumberGenerateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "NumberGenerateController", description = "NumberGenerate APIs")
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
