package com.itq_group.order_service.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum TypeOfPayment {

    CARD("КАРТА"),
    CASH("НАЛИЧНЫЕ");

    private String name;

    @JsonCreator
    public static TypeOfPayment fromString(String value) {
        for (TypeOfPayment type : TypeOfPayment.values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TypeOfPayment: " + value);
    }

    @JsonValue
    public String toName() {
        return name;
    }

}
