package org.test.monitor_sensors.utils.model;

import org.test.monitor_sensors.dto.TypeEnum;
import org.test.monitor_sensors.dto.UnitEnum;
import org.test.monitor_sensors.dto.entity.RangeEntity;
import org.test.monitor_sensors.dto.entity.SensorEntity;
import org.test.monitor_sensors.openapi.model.RangeDto;
import org.test.monitor_sensors.openapi.model.SensorDto;
import org.test.monitor_sensors.utils.RandomTestUtils;

import java.util.UUID;

public class SensorDtoTestUtil {
    public static SensorDto generateValidSensorDto() {
        return new SensorDto().id(UUID.randomUUID())
                .name(RandomTestUtils.getRandomString(10))
                .model(RandomTestUtils.getRandomString(10))
                .range(new RangeDto().id(UUID.randomUUID())
                        .from(RandomTestUtils.getRandomInt(2))
                        .to(RandomTestUtils.getRandomInt(3)))
                .type(RandomTestUtils.getRandomEnum(SensorDto.TypeEnum.values()))
                .unit(RandomTestUtils.getRandomEnum(SensorDto.UnitEnum.values()))
                .location(RandomTestUtils.getRandomString(20))
                .description(RandomTestUtils.getRandomString(20));
    }

    public static SensorEntity generateValidSensorEntity() {
        return SensorEntity.builder()
                .name(RandomTestUtils.getRandomString(10))
                .model(RandomTestUtils.getRandomString(10))
                .range(RangeEntity.builder()
                        .from(RandomTestUtils.getRandomInt(2))
                        .to(RandomTestUtils.getRandomInt(3)).build())
                .type(RandomTestUtils.getRandomEnum(TypeEnum.values()))
                .unit(RandomTestUtils.getRandomEnum(UnitEnum.values()))
                .location(RandomTestUtils.getRandomString(20))
                .description(RandomTestUtils.getRandomString(20))
                .build();
    }
}
