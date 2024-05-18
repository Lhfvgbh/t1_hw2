package com.example.MetricsProducer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Метрика
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metric {
    private String name;
    private String description;
    private String baseUnit;
    private List<String> measurements;
    private List<MetricTag> availableTags;
}
