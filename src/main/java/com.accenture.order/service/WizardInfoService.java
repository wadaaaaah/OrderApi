package com.accenture.order.service;

import com.accenture.order.common.OrderUrl;
import com.accenture.order.entity.WizardInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class WizardInfoService {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<WizardInfo> getWizardInfoById(Long id){
        try {
            ResponseEntity<WizardInfo> responseEntity =
                    restTemplate.exchange(OrderUrl.GET_MAGIC_ID, HttpMethod.GET, null,
                            new ParameterizedTypeReference<WizardInfo>() {});
            WizardInfo wizard = responseEntity.getBody();

            if(wizard.getId() != null){
                return ResponseEntity.ok().body(wizard);
            }else{
                throw new NullPointerException("Wizard info id not found");
            }
        }catch(Exception e){
             throw new ResourceAccessException("Error: Please check if Wizard Info Service is online");
        }
    }
}
