-- Ensure the 'client' table exists
CREATE TABLE IF NOT EXISTS client
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    created_at      TIMESTAMP           NOT NULL,
    updated_at      TIMESTAMP
);

-- Insert well-known UUIDs for specific patients
INSERT INTO client (id, name, email, address, created_at, updated_at)
SELECT '123e4567-e89b-12d3-a456-426614174000',
       'John Doe',
       'johndoe@example.com',
       '123 Main St, Springfield',
       NOW(),
       NULL
WHERE NOT EXISTS (SELECT 1
                  FROM client
                  WHERE id = '123e4567-e89b-12d3-a456-426614174000');

INSERT INTO client (id, name, email, address, created_at, updated_at)
SELECT '123e4567-e89b-12d3-a456-426614174001',
       'Jane Smith',
       'janesmith@example.com',
       '456 Elm St, Shelbyville',
       NOW(),
       NULL
WHERE NOT EXISTS (SELECT 1
                    FROM client
                    WHERE id = '123e4567-e89b-12d3-a456-426614174001');

INSERT INTO client (id, name, email, address, created_at, updated_at)
SELECT '123e4567-e89b-12d3-a456-426614174002',
       'Alice Johnson',
       'alicejohnson@example.com',
       '789 Oak St, Capital City',
       NOW(),
       NULL
WHERE NOT EXISTS (SELECT 1
                    FROM client
                    WHERE id = '123e4567-e89b-12d3-a456-426614174002');

INSERT INTO client (id, name, email, address, created_at, updated_at)
SELECT '123e4567-e89b-12d3-a456-426614174003',
       'Bob Brown',
       'bobbrown@example.com',
       '321 Pine St, Springfield',
       NOW(),
       NULL
WHERE NOT EXISTS (SELECT 1
                    FROM client
                    WHERE id = '123e4567-e89b-12d3-a456-426614174003');

INSERT INTO client (id, name, email, address, created_at, updated_at)
SELECT '123e4567-e89b-12d3-a456-426614174004',
       'Emily Davis',
       'emilydavis@example.com',
       '654 Maple St, Shelbyville',
       NOW(),
       NULL
WHERE NOT EXISTS (SELECT 1
                    FROM client
                    WHERE id = '123e4567-e89b-12d3-a456-426614174004');