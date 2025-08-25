package com.om.clientservice.mapper;

import com.om.clientservice.dto.ClientRequest;
import com.om.clientservice.dto.ClientResponse;
import com.om.clientservice.model.Client;

import java.time.LocalDateTime;

public class ClientMapper {

    public static ClientResponse toDto(Client client) {

        return new ClientResponse(
                client.getId().toString(),
                client.getName(),
                client.getEmail(),
                client.getAddress(),
                client.getCreatedAt().toString());
    }

    public static Client toModel(ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.name());
        client.setEmail(clientRequest.email());
        client.setAddress(clientRequest.address());
        client.setCreatedAt(LocalDateTime.now());
        client.setUpdatedAt(null);
        return client;
    }
}
