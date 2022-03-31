package com.salcedoFawcett.services.userService.domain.repository;

import com.salcedoFawcett.services.userService.domain.model.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository {
    List<Module> getAll();
    Optional<Module> getModuleById(int moduleId);
    Module save(Module module);
    void delete(int moduleId);
}
