package com.salcedoFawcett.services.userService.web.controller;

import com.salcedoFawcett.services.userService.domain.service.ModuleService;
import com.salcedoFawcett.services.userService.domain.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/all")
    public ResponseEntity<List<Module>> getModules(){
        return new ResponseEntity<>(moduleService.getAll(), HttpStatus.OK);
    }

}
