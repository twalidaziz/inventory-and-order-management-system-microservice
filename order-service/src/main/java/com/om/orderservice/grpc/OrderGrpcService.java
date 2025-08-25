package com.om.orderservice.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import order.OrderResponse;
import order.OrderServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(OrderGrpcService.class);

    @Override
    public void createOrder(order.CreateOrderRequest createOrderRequest,
                            StreamObserver<order.OrderResponse> responseObserver) {

        log.info("createOrder request received {}", createOrderRequest.toString());

        // Business logic here...

        OrderResponse response = OrderResponse.newBuilder()
                .setOrderId("12345")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response);  // Send response to client (i.e. Order Service -> Client Service)
        responseObserver.onCompleted();     // End cycle and complete request
    }
}
