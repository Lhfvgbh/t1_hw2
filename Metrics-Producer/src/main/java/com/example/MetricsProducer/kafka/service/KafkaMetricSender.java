package com.example.MetricsProducer.kafka.service;

import com.example.MetricsProducer.model.Metric;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Компонент для отправки кафка-сообщений
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaMetricSender {

    private final KafkaTemplate<String, Metric> kafkaTemplate;

    /**
     * Отправляет метрику с метрикой в топик
     *
     * @param message   Метрика
     * @param topicName Имя топика
     */
    private void sendMessage(@Nonnull Metric message, @Nonnull String topicName) {
        log.info("Sending message={} to topic={}", message, topicName);
        kafkaTemplate.send(topicName, message);
    }

    /**
     * Отправляет метрики в топик 'metrics-topic'
     *
     * @param metrics Список метрик
     */
    public void sendMetricsToMetricsTopic(@Nonnull List<Metric> metrics) {
        if (metrics.isEmpty()) {
            log.info("No metrics to collect");
            return;
        }

        metrics.forEach(metric -> sendMessage(metric, "metrics-topic"));
    }

}
