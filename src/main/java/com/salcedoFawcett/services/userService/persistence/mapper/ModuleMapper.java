package com.salcedoFawcett.services.userService.persistence.mapper;

import com.salcedoFawcett.services.userService.domain.model.Module;
import com.salcedoFawcett.services.userService.persistence.entity.Modulo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    Module toModule(Modulo modulo);
    List<Module> toModules(List<Modulo> modulo);

    @InheritInverseConfiguration()
    Modulo tomodulo(Module module);

}
