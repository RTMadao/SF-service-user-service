package com.salcedoFawcett.services.userService.persistence.mapper;

import com.salcedoFawcett.services.userService.domain.model.User;
import com.salcedoFawcett.services.userService.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(Usuario usuario);
    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration()
    Usuario toUsuario(User user);

}
