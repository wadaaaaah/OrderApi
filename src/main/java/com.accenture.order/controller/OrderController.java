package com.accenture.order.controller;

import com.accenture.order.entity.Order;
import com.accenture.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accenture.order.service.OrderService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/OrderApi")
public class OrderController {

    //@Autowired
    OrderService orderService;

    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService,
                           OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> order(@PathVariable(value = "id") Long id){
       return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String addOrder(@RequestBody Order order){

        return orderService.addOrder(order);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable(value = "id") Long id, @RequestBody Order order){
        orderService.updateOrder(id, order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable(value = "id") Long id){
        orderService.deleteOrder(id);
    }
}
