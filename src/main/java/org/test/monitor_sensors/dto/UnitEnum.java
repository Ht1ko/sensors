package org.test.monitor_sensors.dto;

import lombok.Getter;

@Getter
public enum UnitEnum {
    BAR("bar"),
    VOLTAGE("voltage"),
    C("C"),
    PERCENT("%");


    private final String value;


    UnitEnum(String value){
        this.value = value;
    }
}
