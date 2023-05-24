package com.example.controllerles10.controllers;

import com.example.controllerles10.services.CiModuleService;
import com.example.controllerles10.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cimodule")
@RestController
public class CiModuleController {

    private final TelevisionService televisionService;
    private final CiModuleService ciModuleService;

    public CiModuleController(TelevisionService televisionService, CiModuleService ciModuleSevice) {
        this.televisionService = televisionService;
        this.ciModuleService = ciModuleSevice;
    }

    @PutMapping("/{id}/television/{television_id}")
    public ResponseEntity<String>assignTelevisionToCiModule(@PathVariable Long id, @PathVariable Long television_id) {
        return ResponseEntity.ok(ciModuleService.assignTelevisionToCiModule(id, television_id));
    }




}
