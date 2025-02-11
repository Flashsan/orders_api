package com.itq_group.number_generator_service.service;

import com.itq_group.number_generator_service.dto.NumberGenerateDto;
import com.itq_group.number_generator_service.entity.NumberGenerateEntity;
import com.itq_group.number_generator_service.mapper.NumberGenerateMapper;
import com.itq_group.number_generator_service.repository.NumberGenerateRepository;
import com.itq_group.number_generator_service.service.impl.NumberGenerateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NumberGenerateServiceTest {
        @Mock
        private NumberGenerateRepository numberGenerateRepository;
        @Mock
        private NumberGenerateMapper numberGenerateMapper;

        @InjectMocks
        private NumberGenerateServiceImpl numberGenerateService;

        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

        @BeforeEach
        void setUp() {
            when(numberGenerateRepository.existsByOrderNumber(anyString())).thenReturn(false);
        }

        @Test
        void testGenerateOrderNumber_Success() {
            String datePart = LocalDateTime.now().format(DATE_FORMATTER);
            String generatedNumber = "12345" + datePart;

            NumberGenerateEntity entity = new NumberGenerateEntity();
            entity.setOrderNumber(generatedNumber);

            NumberGenerateDto expectedDto = new NumberGenerateDto();
            expectedDto.setOrderNumber(generatedNumber);

            when(numberGenerateRepository.save(any(NumberGenerateEntity.class))).thenReturn(entity);
            when(numberGenerateMapper.toNumberGenerateDto(entity)).thenReturn(expectedDto);

            NumberGenerateDto result = numberGenerateService.generateOrderNumber();

            assertNotNull(result);
            assertEquals(generatedNumber, result.getOrderNumber());
        }

        @Test
        void testGenerateOrderNumber_UniqueCheckLoop() {
            when(numberGenerateRepository.existsByOrderNumber(anyString()))
                    .thenReturn(true)
                    .thenReturn(false);

            String datePart = LocalDateTime.now().format(DATE_FORMATTER);
            String generatedNumber = "54321" + datePart;

            NumberGenerateEntity entity = new NumberGenerateEntity();
            entity.setOrderNumber(generatedNumber);

            NumberGenerateDto expectedDto = new NumberGenerateDto();
            expectedDto.setOrderNumber(generatedNumber);

            when(numberGenerateRepository.save(any(NumberGenerateEntity.class))).thenReturn(entity);
            when(numberGenerateMapper.toNumberGenerateDto(entity)).thenReturn(expectedDto);

            NumberGenerateDto result = numberGenerateService.generateOrderNumber();

            assertNotNull(result);
            assertEquals(generatedNumber, result.getOrderNumber());
            verify(numberGenerateRepository, atLeast(2)).existsByOrderNumber(anyString());
        }
}
