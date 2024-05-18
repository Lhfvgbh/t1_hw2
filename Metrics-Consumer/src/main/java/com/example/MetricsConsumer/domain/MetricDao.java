package com.example.MetricsConsumer.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "metrics")
@ToString
public class MetricDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "base_unit")
    private String baseUnit;

    @Column(name = "values")
    private String measurementValues;

}
