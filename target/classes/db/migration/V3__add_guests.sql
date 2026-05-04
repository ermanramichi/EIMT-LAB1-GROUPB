CREATE TABLE guests (
                        id       BIGSERIAL PRIMARY KEY,
                        name     VARCHAR(255) NOT NULL,
                        email    VARCHAR(255),
                        passport VARCHAR(100)
);

CREATE TABLE host_guests (
                             host_id  BIGINT NOT NULL REFERENCES hosts(id) ON DELETE CASCADE,
                             guest_id BIGINT NOT NULL REFERENCES guests(id) ON DELETE CASCADE,
                             PRIMARY KEY (host_id, guest_id)
);