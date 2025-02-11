package com.itq_group.order_service.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TypeOfDelivery {
    PICKUP("САМОВЫВОЗ"),
    DOOR_DELIVERY("ДОСТАВКА ДО ДВЕРИ");

    private String name;

    @JsonCreator
    public static TypeOfDelivery fromString(String value) {
        for (TypeOfDelivery type : TypeOfDelivery.values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TypeOfDelivery: " + value);
    }

    @JsonValue
    public String toName() {
        return name;
    }
}
