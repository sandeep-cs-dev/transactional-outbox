package com.outboxdemo.orderservice.controller;

import com.outboxdemo.orderservice.model.Order;
import com.outboxdemo.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(path = "")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

}
