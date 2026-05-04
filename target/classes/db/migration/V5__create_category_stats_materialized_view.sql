-- Task 5: Materialized View with aggregated stats per category
CREATE MATERIALIZED VIEW category_stats AS
SELECT
    a.category                       AS category,
    COUNT(*)                         AS total_accommodations,
    SUM(a.num_rooms)                 AS total_rooms,
    AVG(a.num_rooms)                 AS avg_rooms
FROM accommodations a
GROUP BY a.category;

-- Unique index required for REFRESH MATERIALIZED VIEW CONCURRENTLY
CREATE UNIQUE INDEX idx_category_stats_category ON category_stats (category);
