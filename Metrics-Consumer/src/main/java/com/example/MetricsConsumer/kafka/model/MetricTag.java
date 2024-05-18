package com.example.MetricsConsumer.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Тег метрики
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricTag {
    private String tag;
    private Set<String> values;
}
