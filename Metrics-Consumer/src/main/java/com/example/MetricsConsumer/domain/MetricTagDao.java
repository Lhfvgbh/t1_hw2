package com.example.MetricsConsumer.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "metric_tags")
@ToString
public class MetricTagDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "values")
    private String values;

    @ManyToOne(optional = false, targetEntity = MetricDao.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private MetricDao metric;
}
