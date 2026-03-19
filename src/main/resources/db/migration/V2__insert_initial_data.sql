INSERT INTO countries (name, continent)
VALUES
    ('Italy', 'Europe'),
    ('Spain', 'Europe'),
    ('Greece', 'Europe'),
    ('Croatia', 'Europe'),
    ('Macedonia', 'Europe');

INSERT INTO hosts (created_at, updated_at, name, surname, country_id)
VALUES
    (NOW(), NOW(), 'Marco', 'Rossi', 1),
    (NOW(), NOW(), 'Carlos', 'Garcia', 2),
    (NOW(), NOW(), 'Nikos', 'Papadopoulos', 3),
    (NOW(), NOW(), 'Ana', 'Kovacic', 4),
    (NOW(), NOW(), 'Elena', 'Stojanovska', 5);

INSERT INTO accommodations (created_at, updated_at, name, category, host_id, condition, num_rooms, rented)
VALUES
    (NOW(), NOW(), 'Rome City Apartment', 'APARTMENT', 1, 'GOOD', 3, FALSE),
    (NOW(), NOW(), 'Barcelona Beach House', 'HOUSE', 2, 'GOOD', 6, FALSE),
    (NOW(), NOW(), 'Athens Central Flat', 'FLAT', 3, 'GOOD', 2, TRUE),
    (NOW(), NOW(), 'Dubrovnik Seaside Hotel', 'HOTEL', 4, 'BAD', 12, FALSE),
    (NOW(), NOW(), 'Ohrid Lake Room', 'ROOM', 5, 'GOOD', 1, FALSE);