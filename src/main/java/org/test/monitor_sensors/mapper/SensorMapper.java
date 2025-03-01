package org.test.monitor_sensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.test.monitor_sensors.dto.entity.SensorEntity;
import org.test.monitor_sensors.openapi.model.SensorDto;

@Mapper(componentModel = "spring",
        uses = {RangeMapper.class})
public interface SensorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "sensorDto.name")
    @Mapping(target = "model", source = "sensorDto.model")
    @Mapping(target = "range", source = "sensorDto.range")
    @Mapping(target = "type", source = "sensorDto.type")
    @Mapping(target = "unit", source = "sensorDto.unit")
    @Mapping(target = "location", source = "sensorDto.location")
    @Mapping(target = "description", source = "sensorDto.description")
    SensorEntity createEntity(SensorDto sensorDto);

    @Mapping(target = "id", source = "sensorEntity.id")
    @Mapping(target = "name", source = "sensorEntity.name")
    @Mapping(target = "model", source = "sensorEntity.model")
    @Mapping(target = "range", source = "sensorEntity.range")
    @Mapping(target = "type", source = "sensorEntity.type")
    @Mapping(target = "unit", source = "sensorEntity.unit")
    @Mapping(target = "location", source = "sensorEntity.location")
    @Mapping(target = "description", source = "sensorEntity.description")
    SensorDto createDto(SensorEntity sensorEntity);


    @Mapping(target = "id", source = "sensorDto.id")
    @Mapping(target = "name", source = "sensorDto.name")
    @Mapping(target = "model", source = "sensorDto.model")
    @Mapping(target = "range", source = "sensorDto.range")
    @Mapping(target = "type", source = "sensorDto.type")
    @Mapping(target = "unit", source = "sensorDto.unit")
    @Mapping(target = "location", source = "sensorDto.location")
    @Mapping(target = "description", source = "sensorDto.description")
    SensorEntity updateEntity(SensorDto sensorDto);
}
