package com.salcedoFawcett.services.userService.persistence.crud;

import com.salcedoFawcett.services.userService.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

}
