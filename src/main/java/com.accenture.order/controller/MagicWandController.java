package com.accenture.order.controller;

import com.accenture.order.service.MagicWandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MagicWandApi")
public class MagicWandController {

    @Autowired
    private final MagicWandService magic;

    public MagicWandController(MagicWandService magic) {
        this.magic = magic;
    }

    @GetMapping("/getInfo/{id}")
    public ResponseEntity<?> getMagicWandById(@PathVariable(value = "id") Long id){

        return magic.getMagicWandById(id);
    }
}
