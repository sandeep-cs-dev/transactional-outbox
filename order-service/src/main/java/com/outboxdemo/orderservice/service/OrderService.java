package com.outboxdemo.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outboxdemo.orderservice.model.Order;
import com.outboxdemo.orderservice.model.Outbox;
import com.outboxdemo.orderservice.repository.OrderRepository;
import com.outboxdemo.orderservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order createOrder(Order order) {

        orderRepository.save(order);
        var order_map = objectMapper.convertValue(order, Map.class);
        Outbox outbox = Outbox.builder()
                .event("order_created")
                .eventId(order.getId())
                .payload(order_map)
                .createdAt(new Date())
                .build();

        outboxRepository.save(outbox);
        outboxRepository.delete(outbox);

        //throw new IllegalArgumentException("trigger rollback");
        return order;
    }
}
