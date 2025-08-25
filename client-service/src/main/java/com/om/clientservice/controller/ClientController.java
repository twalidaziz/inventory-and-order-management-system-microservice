package com.om.clientservice.controller;

import com.om.clientservice.dto.ClientRequest;
import com.om.clientservice.dto.ClientResponse;
import com.om.clientservice.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "API for managing Clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Get Clients")
    public ResponseEntity<List<ClientResponse>> getClients() {
        List<ClientResponse> clients = clientService.getClients();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    @Operation(summary = "Create a new Client")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.createClient(clientRequest);
        return ResponseEntity.ok().body(clientResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Client")
    public ResponseEntity<ClientResponse> updateClient(
            @PathVariable UUID id, @Validated({Default.class}) @RequestBody ClientRequest clientRequest) {

        ClientResponse clientResponse = clientService.updateClient(id, clientRequest);
        return ResponseEntity.ok().body(clientResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Client")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
