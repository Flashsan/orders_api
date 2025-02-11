package com.itq_group.order_service.mapper;

import com.itq_group.order_service.dto.CreateOrderDetailsDto;
import com.itq_group.order_service.dto.OrderDetailsDto;
import com.itq_group.order_service.entity.OrderDetailsEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true)})
    OrderDetailsEntity toOrderEntity(CreateOrderDetailsDto dto);

    OrderDetailsDto toOrderDetailsDto(OrderDetailsEntity entity);

}
