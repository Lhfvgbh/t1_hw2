package com.example.MetricsConsumer.repository;


import com.example.MetricsConsumer.domain.MetricTagDao;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с таблицей `metric_tags`
 */
public interface MetricTagsRepository extends JpaRepository<MetricTagDao, Integer> {
}
