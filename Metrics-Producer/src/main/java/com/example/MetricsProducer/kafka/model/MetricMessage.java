package com.example.MetricsProducer.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetricMessage {
    private String metricName;
    private double invocations;
    private Map<String, Object> metricDetails;
}
