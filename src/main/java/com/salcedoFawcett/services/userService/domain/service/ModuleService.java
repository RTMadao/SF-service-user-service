package com.salcedoFawcett.services.userService.domain.service;

import com.salcedoFawcett.services.userService.domain.model.Module;
import com.salcedoFawcett.services.userService.domain.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAll(){
        return moduleRepository.getAll();
    }
}
