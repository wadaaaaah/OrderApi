package com.accenture.order.service;

import com.accenture.order.common.OrderUrl;
import com.accenture.order.entity.WizardInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class WizardInfoService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<WizardInfo> getWizardInfobyId(Long id){
        try {
            ResponseEntity<WizardInfo> responseEntity =
                    restTemplate.getForEntity(OrderUrl.GET_MAGIC_ID, WizardInfo.class);
            WizardInfo wizard = responseEntity.getBody();

            if(wizard.getId() != null){
                return ResponseEntity.ok().body(wizard);
            }else{
                throw new NullPointerException("wizard id not found");
            }
        }catch(ResourceAccessException e){
            throw new ResourceAccessException("Error: Please check if MagicWand Service is online");
        }
    }
}
