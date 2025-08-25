package com.om.clientservice.dto;

import java.time.LocalDateTime;

public record ClientResponse(
        String id,
        String name,
        String email,
        String address,
        String createdAt
) {
}
