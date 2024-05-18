package com.example.MetricsProducer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Модель ответа сбора и отправки метрик
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricsResponse {
    private List<Metric> metrics;
}
