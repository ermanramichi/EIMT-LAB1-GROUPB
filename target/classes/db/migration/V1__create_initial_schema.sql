CREATE TABLE countries (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255) NOT NULL
);

CREATE TABLE hosts (
                       id BIGSERIAL PRIMARY KEY,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       country_id BIGINT NOT NULL,
                       CONSTRAINT fk_hosts_country
                           FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE accommodations (
                                id BIGSERIAL PRIMARY KEY,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP NOT NULL,
                                name VARCHAR(255) NOT NULL,
                                category VARCHAR(50) NOT NULL,
                                host_id BIGINT NOT NULL,
                                condition VARCHAR(50) NOT NULL,
                                num_rooms INTEGER NOT NULL,
                                rented BOOLEAN NOT NULL DEFAULT TRUE,
                                CONSTRAINT fk_accommodations_host
                                    FOREIGN KEY (host_id) REFERENCES hosts(id)
);