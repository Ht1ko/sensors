package org.test.monitor_sensors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.test.monitor_sensors.dto.entity.RangeEntity;
import org.test.monitor_sensors.openapi.model.RangeDto;

@Mapper(componentModel = "spring")
public interface RangeMapper {

    @Mapping(target = "from", source = "dto.from")
    @Mapping(target = "to", source = "dto.to")
    @Mapping(target = "id", ignore = true)
    RangeEntity createEntity(RangeDto dto);

    @Mapping(target = "from", source = "entity.from")
    @Mapping(target = "to", source = "entity.to")
    @Mapping(target = "id", source = "entity.id")
    RangeDto createDto(RangeEntity entity);
}
