package com.itq_group.number_generator_service.repository;

import com.itq_group.number_generator_service.entity.NumberGenerateEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class NumberGenerateRepositoryTest {
    public static final String NUMBER = "12345620250211";
    public static final String NUMBER1 = "65432120250211";
    public static final String NUMBER2 = "00000020250211";

    @Autowired
    private NumberGenerateRepository numberGenerateRepository;

    @Test
    void testSaveOrderNumber() {
        NumberGenerateEntity entity = new NumberGenerateEntity();
        entity.setOrderNumber(NUMBER);

        NumberGenerateEntity savedEntity = numberGenerateRepository.save(entity);

        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getOrderNumber()).isEqualTo(NUMBER);

        Optional<NumberGenerateEntity> foundEntity = numberGenerateRepository.findById(savedEntity.getId());

        assertThat(foundEntity).isPresent();
        assertThat(foundEntity.get().getOrderNumber()).isEqualTo(NUMBER);
    }

    @Test
    void testExistsByOrderNumber() {
        NumberGenerateEntity entity = new NumberGenerateEntity();
        entity.setOrderNumber(NUMBER1);

        numberGenerateRepository.save(entity);

        boolean exists = numberGenerateRepository.existsByOrderNumber(NUMBER1);
        boolean notExists = numberGenerateRepository.existsByOrderNumber(NUMBER2);

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }
}