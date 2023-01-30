package com.accenture.order.integration;

import com.accenture.order.common.OrderUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderIntegration {

    @Autowired
    private final RestTemplate restTemplate;

    public OrderIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getWizard(String id) throws Exception {
        try{
            return restTemplate.getForObject(OrderUrl.GET_WIZARD_ID, Object.class);
        }catch (HttpClientErrorException e){
            log.error("error:{}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "error");
        }catch (HttpServerErrorException e){
            log.error("server error:{}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "error");
        }
    }

    public Object getMagic(String id) throws Exception {
        try{
            return restTemplate.getForObject(OrderUrl.GET_MAGIC_ID, Object.class);
        }catch (HttpClientErrorException e){
            log.error("error:{}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "error");
        }catch (HttpServerErrorException e){
            log.error("server error:{}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "error");
        }
    }
}
