package com.itq_group.number_generator_service.service.impl;

import com.itq_group.number_generator_service.dto.NumberGenerateDto;
import com.itq_group.number_generator_service.entity.NumberGenerateEntity;
import com.itq_group.number_generator_service.mapper.NumberGenerateMapper;
import com.itq_group.number_generator_service.repository.NumberGenerateRepository;
import com.itq_group.number_generator_service.service.NumberGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class NumberGenerateServiceImpl implements NumberGenerateService {

    private final NumberGenerateRepository numberGenerateRepository;
    private final NumberGenerateMapper numberGenerateMapper;
    private final Random random = new Random();

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String FORMAT = "%05d";
    private static final int BOUND = 100000;


    @Override
    public NumberGenerateDto generateOrderNumber() {
        String datePart = LocalDateTime.now().format(DATE_FORMATTER);
        String uniqueNumber;
        do {
            uniqueNumber = String.format(FORMAT, random.nextInt(BOUND));
        } while (existsByOrderNumber(uniqueNumber + datePart));

        NumberGenerateEntity entity = new NumberGenerateEntity();
        entity.setOrderNumber(uniqueNumber + datePart);


        NumberGenerateEntity savedEntity = numberGenerateRepository.save(entity);
        return numberGenerateMapper.toNumberGenerateDto(savedEntity);
    }

    private boolean existsByOrderNumber(String orderNumber) {
        return numberGenerateRepository.existsByOrderNumber(orderNumber);
    }
}
