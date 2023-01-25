package com.accenture.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class OrderService {

    private final RestTemplate restTemplate;

    private static final String GET_WIZARD_ID = "http://localhost:8090/WizardInfoApi/getInfo/{id}";
    private static final String GET_MAGIC_ID = "http://localhost:8090/MagicWandApi/getInfo/{id}";

    public OrderService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public Object getWizard(String id) throws Exception {
        String url = GET_WIZARD_ID + id;
        try{
            return restTemplate.getForObject(url, Object.class);
        }catch (HttpClientErrorException e){
            log.error("error:{}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "error");
            //throw new OrderException("external error", e);
        }catch (HttpServerErrorException e){
            log.error("server error:{}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "error");
        }
    }

    public Object getMagic(String id) throws Exception {
        String url = GET_MAGIC_ID + id;
        try{
            return restTemplate.getForObject(url, Object.class);
        }catch (HttpClientErrorException e){
            log.error("error:{}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "error");
            //throw new OrderException("external error", e);
        }catch (HttpServerErrorException e){
            log.error("server error:{}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "error");
        }
    }
}
