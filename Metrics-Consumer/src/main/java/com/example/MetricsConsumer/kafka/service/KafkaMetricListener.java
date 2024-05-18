package com.example.MetricsConsumer.kafka.service;

import com.example.MetricsConsumer.kafka.model.Metric;
import com.example.MetricsConsumer.service.MetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки кафка-сообщений
 */
@Service
@Slf4j
@RequiredArgsConstructor
@KafkaListener(id = "metrics", topics = {"metrics-topic", "metrics-topic.DLT"})
public class KafkaMetricListener {

    private final MetricService metricService;

    @RetryableTopic(attempts = "2", backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2))
    @KafkaHandler
    public void metric(Metric metric) {
        log.info("Received Metric: {}", metric);
        metricService.save(metric);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("Received unknown: {}", object);
    }

}
