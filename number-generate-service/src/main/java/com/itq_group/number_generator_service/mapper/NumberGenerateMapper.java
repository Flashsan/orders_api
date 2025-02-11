package com.itq_group.number_generator_service.mapper;

import com.itq_group.number_generator_service.dto.NumberGenerateDto;
import com.itq_group.number_generator_service.entity.NumberGenerateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface NumberGenerateMapper {

    @Mappings({
            @Mapping(target = "orderNumber", source = "orderNumber")})
     NumberGenerateDto toNumberGenerateDto(NumberGenerateEntity entity);
}
