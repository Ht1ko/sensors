package org.test.monitor_sensors.repository;

import org.springframework.data.jpa.domain.Specification;
import org.test.monitor_sensors.dto.entity.SensorEntity;

public class SensorSpecs {

    public static Specification<SensorEntity> modelLike(String model) {
        return (root, query, builder) -> builder.like(root.get("model"),"%"+model+"%");
    }public static Specification<SensorEntity> nameLike(String name) {
        return (root, query, builder) -> builder.like(root.get("name"),"%"+name+"%");
    }
}
