package org.test.monitor_sensors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.test.monitor_sensors.dto.entity.SensorEntity;
import org.test.monitor_sensors.mapper.SensorMapper;
import org.test.monitor_sensors.openapi.model.FindSensorDto;
import org.test.monitor_sensors.openapi.model.SensorDto;
import org.test.monitor_sensors.repository.SensorRepository;
import org.test.monitor_sensors.repository.SensorSpecs;
import org.test.monitor_sensors.service.SensorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class SensorServiceImpl implements SensorService {
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private SensorMapper sensorMapper;

    @Override
    public SensorDto createSensor(final SensorDto sensorDto) {
        SensorEntity entity = sensorMapper.createEntity(sensorDto);
        final SensorEntity save = sensorRepository.save(entity);
        return sensorMapper.createDto(save);
    }

    @Override
    public Optional<SensorDto> updateSensor(final SensorDto sensorDto) {
        final Optional<SensorEntity> byId = sensorRepository.findById(sensorDto.getId());
        return byId.map(id->{
            final SensorEntity sensorEntity = sensorMapper.updateEntity(sensorDto);
            final SensorEntity saved = sensorRepository.save(sensorEntity);
            return sensorMapper.createDto(saved);
        });
    }

    @Override
    public void deleteSensor(final UUID sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    @Override
    public Optional<SensorDto> getSensorById(final UUID sensorId) {
        final Optional<SensorEntity> byId = sensorRepository.findById(sensorId);
        return byId.map(entity -> sensorMapper.createDto(entity));
    }

    @Override
    public FindSensorDto findSensors(final String name, final String model, final Integer page) {
        final Pageable pageable = Pageable.ofSize(20)
                .withPage(page);
        List<Specification> specifications = new ArrayList<>();
        if (Objects.nonNull(name)) {
            specifications.add(SensorSpecs.nameLike(name));
        }
        if (Objects.nonNull(model)) {
            specifications.add(SensorSpecs.modelLike(model));
        }
        final Optional<Specification> reduce = specifications.stream().reduce((k, v) -> k.and(v));

        final Page<SensorEntity> entities = sensorRepository
                .findAll(reduce.orElse(null),
                        pageable);
        final List<SensorDto> collect = entities.stream().map(sensorMapper::createDto).collect(Collectors.toList());
        return new FindSensorDto().sensors(collect)
                .page(entities.getPageable().getPageNumber());
    }
}
