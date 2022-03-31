package com.salcedoFawcett.services.userService.persistence.repository;

import com.salcedoFawcett.services.userService.domain.model.Module;
import com.salcedoFawcett.services.userService.domain.repository.ModuleRepository;
import com.salcedoFawcett.services.userService.persistence.crud.ModuloCrudRepository;
import com.salcedoFawcett.services.userService.persistence.entity.Modulo;
import com.salcedoFawcett.services.userService.persistence.mapper.ModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ModuloRepository implements ModuleRepository {

    @Autowired
    private ModuloCrudRepository moduloCrudRepository;
    @Autowired
    private ModuleMapper mapper;

    @Override
    public List<Module> getAll() {
        return mapper.toModules((List<Modulo>) moduloCrudRepository.findAll());
    }

    @Override
    public Optional<Module> getModuleById(int moduleId) {
        return moduloCrudRepository.findById(moduleId).map(modulo -> mapper.toModule(modulo));
    }

    @Override
    public Module save(Module module) {
        return mapper.toModule(moduloCrudRepository.save(mapper.tomodulo(module)));
    }

    @Override
    public void delete(int moduleId) {
        moduloCrudRepository.deleteById(moduleId);
    }
}
