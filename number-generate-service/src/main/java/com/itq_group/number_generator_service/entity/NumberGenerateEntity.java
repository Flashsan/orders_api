package com.itq_group.number_generator_service.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "number_generate")
public class NumberGenerateEntity {
    @Id
    private ObjectId id;

    @Indexed(unique=true)
    private String orderNumber;

    private Long orderId;
}
