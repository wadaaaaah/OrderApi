package com.accenture.order.service;

import com.accenture.order.common.OrderUrl;
import com.accenture.order.entity.MagicWand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class MagicWandService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<MagicWand> getWandID(Long id){
        try {
            ResponseEntity<MagicWand> responseEntity =
                    restTemplate.getForEntity(OrderUrl.GET_MAGIC_ID, MagicWand.class);
            MagicWand magic = responseEntity.getBody();

            if(magic.getId() != null){
                return ResponseEntity.ok().body(magic);
            }else{
                throw new NullPointerException("magic id not found");
            }
        }catch(ResourceAccessException e){
            throw new ResourceAccessException("Error: Please check if MagicWand Service is online");
        }
    }

    public void updateMagicWand(MagicWand magic){
        try {
            restTemplate.put(OrderUrl.UPDATE_MAGIC_WAND + magic.getId(), magic);
        }catch(ResourceAccessException e){
            throw new ResourceAccessException("Error: Please check if MagicWand Service is online");
        }
    }
}
