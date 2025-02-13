package com.itq_group.order_service.mapper;

import com.itq_group.order_service.dto.CreateOrderDto;
import com.itq_group.order_service.dto.OrderDto;
import com.itq_group.order_service.dto.OrderInfoDto;
import com.itq_group.order_service.entity.OrderEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {OrderDetailsMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)})
    OrderEntity toOrderEntity(CreateOrderDto dto);


    OrderDto toOrderDto(OrderEntity entity);

    OrderInfoDto toOrderInfoDto(OrderEntity entity);

}

