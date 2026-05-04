DROP MATERIALIZED VIEW IF EXISTS category_stats CASCADE;

CREATE MATERIALIZED VIEW category_stats AS
SELECT
    a.category                       AS category,
    COUNT(*)                         AS total_accommodations,
    SUM(a.num_rooms)                 AS total_rooms,
    AVG(a.num_rooms)::DOUBLE PRECISION AS avg_rooms
FROM accommodations a
GROUP BY a.category;

-- Recreate the index
CREATE UNIQUE INDEX idx_category_stats_category
    ON category_stats (category);