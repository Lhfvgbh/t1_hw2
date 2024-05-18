package com.example.MetricsConsumer.controller;

import com.example.MetricsConsumer.domain.MetricDao;
import com.example.MetricsConsumer.model.MetricResponseItem;
import com.example.MetricsConsumer.model.MetricsResponse;
import com.example.MetricsConsumer.service.MetricService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для работы с метриками
 */
@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
public class MetricController {

    private final MetricService metricService;

    /**
     * Получить список всех метрик
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public MetricsResponse metrics() {
        return new MetricsResponse()
                .setMetrics(metricService.findAll().stream()
                        .map(this::buildMetricResponse)
                        .toList());
    }

    /**
     * Получить метрику по id
     */
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public MetricResponseItem metricById(@PathVariable Integer id) {
        return metricService.findById(id).map(this::buildMetricResponse).orElse(null);
    }

    /**
     * Получить метрики по имени
     */
    @GetMapping(value = "/params", produces = APPLICATION_JSON_VALUE)
    public MetricsResponse metricsByName(@RequestParam("name") String name) {
        return new MetricsResponse()
                .setMetrics(metricService.findByName(name).stream()
                        .map(this::buildMetricResponse)
                        .toList());
    }

    @Nonnull
    private MetricResponseItem buildMetricResponse(@Nonnull MetricDao metricDao) {
        return new MetricResponseItem()
                .setId(metricDao.getId())
                .setName(metricDao.getName())
                .setDescription(metricDao.getDescription())
                .setCreatedAt(metricDao.getCreatedAt())
                .setBaseUnit(metricDao.getBaseUnit())
                .setMeasurementValues(metricService.deserializeValues(metricDao.getMeasurementValues()));
    }
}
