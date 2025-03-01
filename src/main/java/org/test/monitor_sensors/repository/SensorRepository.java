package org.test.monitor_sensors.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.test.monitor_sensors.dto.entity.SensorEntity;

import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, UUID>, JpaSpecificationExecutor<SensorEntity> {

    Page<SensorEntity> findAll(Specification<SensorEntity> specification, Pageable pageable);
}
