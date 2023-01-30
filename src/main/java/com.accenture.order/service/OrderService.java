package com.accenture.order.service;

import com.accenture.order.entity.Order;
import com.accenture.order.exception.OrderNotFoundException;
import com.accenture.order.integration.OrderIntegration;
import com.accenture.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderIntegration orderIntegration;
    @Autowired
    OrderRepository repo;
    @Autowired
    private WizardInfoService wizard;
    @Autowired
    private MagicWandService magic;

    RestTemplate restTemplate = new RestTemplate();

    public Order getOrderbyId(Long id){
        return repo.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderNotFoundException.INVALID_ID + id));
    }




}
