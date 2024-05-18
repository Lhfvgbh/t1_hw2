package com.example.MetricsProducer.service;

import com.example.MetricsProducer.model.Metric;
import com.example.MetricsProducer.model.MetricTag;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с метриками Spring Actuator
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricCollectorService {

    private final MetricsEndpoint metricsEndpoint;

    /**
     * Собирает указанные метрики
     *
     * @param metricNames Список имен метрик
     * @return Список метрик
     */
    @Nonnull
    public List<Metric> collectMetricsByName(@Nonnull List<String> metricNames) {
        if (metricNames.isEmpty()) {
            log.info("No metrics to collect");
            return List.of();
        }

        return metricNames.stream()
                .map(this::findMetricByName)
                .flatMap(Optional::stream)
                .toList();
    }

    @Nonnull
    private Optional<Metric> findMetricByName(@Nonnull String name) {
        var metricDetails = metricsEndpoint.metric(name, null);

        if (metricDetails == null) {
            log.warn("Metric not found by name={}", name);
            return Optional.empty();
        }

        return Optional.of(
                new Metric()
                        .setName(metricDetails.getName())
                        .setDescription(metricDetails.getDescription())
                        .setBaseUnit(metricDetails.getBaseUnit())
                        .setMeasurements(metricDetails.getMeasurements()
                                .stream()
                                .map(MetricsEndpoint.Sample::toString)
                                .toList())
                        .setAvailableTags(metricDetails.getAvailableTags()
                                .stream()
                                .map(tag -> new MetricTag()
                                        .setTag(tag.getTag())
                                        .setValues(tag.getValues()))
                                .toList()));

    }
}
