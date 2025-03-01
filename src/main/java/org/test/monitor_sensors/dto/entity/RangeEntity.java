package org.test.monitor_sensors.dto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity(name="range")
@Getter
@Table(name = "range")
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class RangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "\"from\"")
    private Integer from;
    @Column(name ="\"to\"")
    private Integer to;
}
