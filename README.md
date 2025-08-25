# inventory-and-order-management-system-microservice
A work in progress Spring Boot microservice application with five modules: Client service, Order service, Inventory service, Auth service, and Analytics service.

# Auth Service Database Environment Variables
```
SPRING_DATASOURCE_PASSWORD=password
SPRING_DATASOURCE_URL=jdbc:postgresql://auth-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_SQL_INIT_MODE=always
```
# Auth Service data.sql
```sql
-- Ensure the 'user' table exists
CREATE TABLE IF NOT EXISTS "user" (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

-- Insert the user if no existing user with the same id or email exists
INSERT INTO "user" (id, email, password, role, created_at, updated_at)
SELECT '223e4567-e89b-12d3-a456-426614174006', 'testuser@test.com',
       '$2b$12$7hoRZfJrRKD2nIm2vHLs7OBETy.LWenXXMLKf99W8M4PUwO6KB7fu', 'ADMIN', NOW(), NULL
WHERE NOT EXISTS (
    SELECT 1
    FROM "user"
    WHERE id = '223e4567-e89b-12d3-a456-426614174006'
       OR email = 'testuser@test.com'
);
```
# Client Service Database Environment Variables
```
SPRING_DATASOURCE_PASSWORD=password
SPRING_DATASOURCE_URL=jdbc:postgresql://client-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_SQL_INIT_MODE=always
```
# Client Service data.sql
```sql
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
```
