package com.example.MetricsConsumer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Модель ответа метрик
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricsResponse {
    private List<MetricResponseItem> metrics;
}
