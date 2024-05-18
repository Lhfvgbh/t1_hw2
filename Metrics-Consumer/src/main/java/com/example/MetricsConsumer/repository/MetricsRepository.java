package com.example.MetricsConsumer.repository;

import com.example.MetricsConsumer.domain.MetricDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторий для работы с таблицей `metrics`
 */
public interface MetricsRepository extends JpaRepository<MetricDao, Integer> {

    @Query(value = "SELECT * FROM metrics AS m WHERE m.name = :metricName", nativeQuery = true)
    List<MetricDao> findByName(@Param("metricName") String metricName);

}