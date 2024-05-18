package com.example.MetricsProducer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Измерение метрики
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricMeasurement {
    private String statistic;
    private double value;
}
