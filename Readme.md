# Проект №2 в рамках открытой школы Т1

## Описание
Реализация системы взаимодействия двух микросервисов через Kafka.
Микросервис 1 - MetricsProducer - передает сообщения о статистике приложения в топик Kafka.
Микросервис 2 - MetricsConsumer - читает и сохраняет сообщения о статистике приложения из топика Kafka.

Для проверки функционала может быть использована коллекция Postman


## Сборка приложения
```shell script
# запустить PostgreSQL в docker-контейнере
docker-compose up -d postgres

# запустить Zookeeper в docker-контейнере
docker-compose up -d zookeeper

# запустить Kafka в docker-контейнере
docker-compose up -d kafka

# загружает gradle wrapper
./gradlew wrapper

# сборка проекта, прогон unit-тестов, запуск приложения
./gradlew clean build bootRun
```
