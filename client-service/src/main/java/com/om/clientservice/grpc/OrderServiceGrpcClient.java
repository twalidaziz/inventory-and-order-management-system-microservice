package com.om.clientservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import order.CreateOrderRequest;
import order.OrderResponse;
import order.OrderServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceGrpcClient.class);
    private final OrderServiceGrpc.OrderServiceBlockingStub blockingStub;

    public OrderServiceGrpcClient(@Value("${order.service.address:localhost}") String serverAddress,
                                  @Value("${order.service.grpc.port:9001}") int serverPort) {

        log.info("Connecting to GRPC Order Service at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();

        blockingStub = OrderServiceGrpc.newBlockingStub(channel);
    }

    public OrderResponse createOrder(String clientId, String name, String email) {

        CreateOrderRequest request = CreateOrderRequest.newBuilder()
                .setClientId(clientId)
                .setName(name)
                .setEmail(email)
                .build();

        OrderResponse response = blockingStub.createOrder(request);
        log.info("Received response from Order Service via GRPC: {}", response);

        return response;
    }
}
