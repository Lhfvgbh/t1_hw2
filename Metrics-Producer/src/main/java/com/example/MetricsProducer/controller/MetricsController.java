package com.example.MetricsProducer.controller;

import com.example.MetricsProducer.kafka.service.KafkaMetricSender;
import com.example.MetricsProducer.model.MetricsRequest;
import com.example.MetricsProducer.model.MetricsResponse;
import com.example.MetricsProducer.service.MetricCollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final KafkaMetricSender kafkaMetricSender;
    private final MetricCollectorService metricCollectorService;

    /**
     * Собрать и отправить метрики
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public MetricsResponse metrics(@RequestBody MetricsRequest request) {
        var metrics = metricCollectorService.collectMetricsByName(request.getMetricNames());
        kafkaMetricSender.sendMetricsToMetricsTopic(metrics);
        return new MetricsResponse().setMetrics(metrics);
    }
}
