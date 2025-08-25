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
# Client Service Database Environment Variables
```
SPRING_DATASOURCE_PASSWORD=password
SPRING_DATASOURCE_URL=jdbc:postgresql://client-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_SQL_INIT_MODE=always
```
