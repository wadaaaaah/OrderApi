package com.accenture.order.controller;

import com.accenture.order.service.WizardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/WizardInfoApi")
public class WizardInfoController {

    @Autowired
    private final WizardInfoService wizard;

    public WizardInfoController(WizardInfoService wizard) {
        this.wizard = wizard;
    }

    @GetMapping("/getInfo/{id}")
    public ResponseEntity<?> getWizardInfoById(@PathVariable (value = "id") Long id){
        return wizard.getWizardInfoById(id);
    }
}
