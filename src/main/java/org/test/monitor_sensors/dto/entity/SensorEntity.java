package org.test.monitor_sensors.dto.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.test.monitor_sensors.dto.TypeEnum;
import org.test.monitor_sensors.dto.UnitEnum;

import java.util.UUID;

@Entity(name = "sensor")
@Getter
@Table(name = "sensor")
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class SensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_range_id")
    private RangeEntity range;

    private TypeEnum type;
    private UnitEnum unit;
    private String location;
    private String description;
}
