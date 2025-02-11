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

//    @Mappings({
//            @Mapping(target = "number", ignore = true),
//            @Mapping(target = "total", ignore = true),
//            @Mapping(target = "orderDate", ignore = true),
//            @Mapping(target = "recipient", ignore = true),
//            @Mapping(target = "addressDelivery", ignore = true),
//            @Mapping(target = "typeOfPayment", ignore = true),
//            @Mapping(target = "typeOfDelivery", ignore = true),
//            @Mapping(target = "orderDetails", ignore = true)
//    })
