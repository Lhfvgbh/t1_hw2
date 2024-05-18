package com.example.MetricsConsumer.service;

import com.example.MetricsConsumer.domain.MetricDao;
import com.example.MetricsConsumer.domain.MetricTagDao;
import com.example.MetricsConsumer.kafka.model.Metric;
import com.example.MetricsConsumer.repository.MetricTagsRepository;
import com.example.MetricsConsumer.repository.MetricsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с метриками
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricService {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final MetricsRepository metricsRepository;
    private final MetricTagsRepository metricTagsRepository;

    /**
     * Сохранить метрику
     *
     * @param metric Метрика
     */
    @Async
    public void save(@Nonnull Metric metric) {
        var savedMetric = metricsRepository.save(
                new MetricDao()
                        .setName(metric.getName())
                        .setDescription(metric.getDescription())
                        .setBaseUnit(metric.getBaseUnit())
                        .setCreatedAt(OffsetDateTime.now())
                        .setMeasurements(serializeValues(metric.getMeasurements())));
        metric.getAvailableTags()
                .forEach(tag -> metricTagsRepository.save(
                        new MetricTagDao()
                                .setName(tag.getTag())
                                .setValues(serializeValues(tag.getValues()))
                                .setMetric(savedMetric)));

    }

    /**
     * Получить список метрик
     *
     * @return список метрик
     */
    @Nonnull
    @Transactional(readOnly = true)
    public List<MetricDao> findAll() {
        var metrics = metricsRepository.findAll();
        log.info("Found {} metrics id DB", metrics.size());

        return metrics;
    }

    /**
     * Получить метрику по идентификатору
     *
     * @param id Идентификтор метрики
     * @return метрика
     */
    @Nonnull
    @Transactional(readOnly = true)
    public Optional<MetricDao> findById(@Nonnull Integer id) {
        var metric = metricsRepository.findById(id);
        if (metric.isEmpty()) {
            log.warn("Metric not found by id={}", id);
        }
        return metric;
    }

    /**
     * Получить метрику по имени
     *
     * @param name Имя метрики
     * @return список метрик
     */
    @Nonnull
    @Transactional(readOnly = true)
    public List<MetricDao> findByName(@Nonnull String name) {
        var metrics = metricsRepository.findByName(name);
        if (metrics.isEmpty()) {
            log.warn("Metric not found by name={}", name);
            return List.of();
        }
        return metrics;
    }

    @Nonnull
    public List<String> deserializeValues(@Nullable String jsonString) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, new TypeReference<List<String>>(){});
        } catch (JsonProcessingException e) {
            log.warn("Cannot deserialize json={}", jsonString);
            return List.of();
        }
    }

    @Nullable
    private String serializeValues(@Nullable Collection<String> values) {
        try {
            return OBJECT_MAPPER.writeValueAsString(values);
        } catch (JsonProcessingException e) {
            log.warn("Cannot serialize values={}", values);
            return null;
        }
    }
}
