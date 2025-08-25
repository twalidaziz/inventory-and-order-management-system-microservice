package com.om.clientservice.service;

import com.om.clientservice.dto.ClientRequest;
import com.om.clientservice.dto.ClientResponse;
import com.om.clientservice.exception.ClientNotFoundException;
import com.om.clientservice.exception.EmailAlreadyExistsException;
import com.om.clientservice.grpc.OrderServiceGrpcClient;
import com.om.clientservice.kafka.KafkaProducer;
import com.om.clientservice.mapper.ClientMapper;
import com.om.clientservice.model.Client;
import com.om.clientservice.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final OrderServiceGrpcClient orderServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    public ClientService(ClientRepository clientRepository, OrderServiceGrpcClient orderServiceGrpcClient, KafkaProducer kafkaProducer) {
        this.clientRepository = clientRepository;
        this.orderServiceGrpcClient = orderServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<ClientResponse> getClients() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream()
            .map(ClientMapper::toDto)
            .toList();
    }

    public ClientResponse createClient(ClientRequest clientRequest) {
        if(clientRepository.existsByEmail(clientRequest.email()))
            throw new EmailAlreadyExistsException("Client with email already exists");

        Client newClient = clientRepository.save(ClientMapper.toModel(clientRequest));

        orderServiceGrpcClient.createOrder(newClient.getId().toString(), newClient.getName(), newClient.getEmail());

        kafkaProducer.sendEvent(newClient);

        return ClientMapper.toDto(newClient);
    }

    public ClientResponse updateClient(UUID id, ClientRequest clientRequest) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ClientNotFoundException("Cannot find patient with ID: " + id));

        if(clientRepository.existsByEmailAndIdNot(clientRequest.email(), id))
            throw new EmailAlreadyExistsException("Client with email already exists");

        client.setName(clientRequest.name());
        client.setEmail(clientRequest.email());
        client.setAddress(clientRequest.email());
        client.setUpdatedAt(LocalDateTime.now());
        Client updatedClient = clientRepository.save(client);

        return ClientMapper.toDto(updatedClient);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
