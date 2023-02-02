package com.accenture.order.service;

import com.accenture.order.common.OrderUrl;
import com.accenture.order.entity.MagicWand;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class MagicWandService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<MagicWand> getMagicWandById(Long id){
        try {
            ResponseEntity<MagicWand> responseEntity =
                    restTemplate.exchange(OrderUrl.GET_MAGIC_ID, HttpMethod.GET, null,
                            new ParameterizedTypeReference<MagicWand>() {});
            MagicWand magic = responseEntity.getBody();

            if(magic.getId() != null){
                return ResponseEntity.ok().body(magic);
            }else{
                throw new NullPointerException("Magic wand id not found");
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
