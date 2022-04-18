package com.salcedoFawcett.services.userService.persistence.crud;

import com.salcedoFawcett.services.userService.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByUsernameAndIsActive(String username, boolean isActive);

    List<Usuario> findByIsActive(boolean isActive);
}
