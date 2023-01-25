package com.accenture.order.controller;

import com.accenture.order.entity.Order;
import com.accenture.order.repository.OrderRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.accenture.order.service.OrderService;

@RestController
@RequestMapping("/OrderApi")
public class OrderController {

    private final RestTemplate restTemplate;
    private final OrderService orderService;

    private final OrderRepository repo;

    public OrderController(OrderService orderService, RestTemplateBuilder restTemplateBuilder, OrderRepository repo) {
        this.restTemplate = restTemplateBuilder.build();
        this.orderService = orderService;
        this.repo = repo;
    }



    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        Order newOrder = new Order();
        newOrder.setWizard_name(order.getWizard_name());
        newOrder.setMagic_name(order.getMagic_name());
        newOrder.setAge_limit(order.getAge_limit());

        return ResponseEntity.ok(repo.save(newOrder));
    }




}
