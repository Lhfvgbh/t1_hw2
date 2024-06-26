DROP TABLE IF EXISTS METRIC_TAGS;
DROP TABLE IF EXISTS METRICS CASCADE;

CREATE TABLE METRICS
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    created_at  TIMESTAMP    NOT NULL,
    base_unit   VARCHAR(255),
    values      VARCHAR
);

CREATE TABLE METRIC_TAGS
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    values    VARCHAR      NOT NULL,
    metric_id BIGINT,
    FOREIGN KEY (metric_id) REFERENCES METRICS (id) ON DELETE CASCADE
);