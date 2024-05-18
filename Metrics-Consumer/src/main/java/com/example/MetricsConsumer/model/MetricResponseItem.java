package com.example.MetricsConsumer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Модель ответа метрики
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricResponseItem {
    private Integer id;
    private String name;
    private String description;
    private OffsetDateTime createdAt;
    private String baseUnit;
    private List<String> measurements;
}
