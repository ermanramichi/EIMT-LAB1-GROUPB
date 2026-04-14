-- Task 9: Activity log table for rental events
CREATE TABLE activity_log (
    id                   BIGSERIAL PRIMARY KEY,
    accommodation_name   VARCHAR(255) NOT NULL,
    event_timestamp      TIMESTAMP    NOT NULL,
    event_type           VARCHAR(100) NOT NULL
);
