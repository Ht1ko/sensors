package org.test.monitor_sensors.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.test.monitor_sensors.dto.entity.RangeEntity;
import org.test.monitor_sensors.openapi.model.RangeDto;
import org.test.monitor_sensors.utils.RandomTestUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RangeMapperTest {
    private final RangeMapper mapper = Mappers.getMapper(RangeMapper.class);

    @Test
    void createEntity() {
        final RangeDto rangeDto = new RangeDto().from(RandomTestUtils.getRandomInt(2))
                .to(RandomTestUtils.getRandomInt(3));
        final RangeEntity entity = mapper.createEntity(rangeDto);
        assertThat(entity.getFrom(), is(rangeDto.getFrom()));
        assertThat(entity.getTo(), is(rangeDto.getTo()));
    }

    @Test
    void createDto() {
        final RangeEntity rangeEntity = RangeEntity.builder().from(RandomTestUtils.getRandomInt(2))
                .to(RandomTestUtils.getRandomInt(3)).build();
        final RangeDto dto = mapper.createDto(rangeEntity);
        assertThat(dto.getFrom(), is(rangeEntity.getFrom()));
        assertThat(dto.getTo(), is(rangeEntity.getTo()));
    }
}