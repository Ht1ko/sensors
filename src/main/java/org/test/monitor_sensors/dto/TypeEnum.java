package org.test.monitor_sensors.dto;

import lombok.Getter;

@Getter
public enum TypeEnum {
    PRESSURE("pressure"),

    VOLTAGE("voltage"),

    TEMPERATURE("temperature"),

    HUMIDITY("humidity");

    private final String value;

    TypeEnum(String value){
        this.value = value;
    }
}
