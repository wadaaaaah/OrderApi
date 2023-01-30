package com.accenture.order.controller;

import com.accenture.order.entity.Order;
import com.accenture.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accenture.order.service.OrderService;

@RestController
@RequestMapping("/OrderApi")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    private final OrderRepository repo;

    public OrderController(OrderService orderService, OrderRepository repo) {
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
