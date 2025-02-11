package com.itq_group.number_generator_service.repository;

import com.itq_group.number_generator_service.entity.NumberGenerateEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.UUID;

public interface NumberGenerateRepository extends MongoRepository<NumberGenerateEntity, ObjectId> {

    boolean existsByOrderNumber(String orderNumber);
}
