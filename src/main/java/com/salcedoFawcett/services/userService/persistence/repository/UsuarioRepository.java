package com.salcedoFawcett.services.userService.persistence.repository;

import com.salcedoFawcett.services.userService.domain.model.User;
import com.salcedoFawcett.services.userService.domain.repository.UserRepository;
import com.salcedoFawcett.services.userService.persistence.crud.UsuarioCrudRepository;
import com.salcedoFawcett.services.userService.persistence.entity.Usuario;
import com.salcedoFawcett.services.userService.persistence.mapper.ModuleMapper;
import com.salcedoFawcett.services.userService.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepository implements UserRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<User> getAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioCrudRepository.findByIsActive(true);
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return usuarioCrudRepository.findById(userId).map(usuario -> {
            User user = mapper.toUser(usuario);
            user.setAccess(usuario.getAccesos().stream().map(modulo -> moduleMapper.toModule(modulo)).collect(Collectors.toSet()));
            return user;
        });
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return usuarioCrudRepository.findByUsernameAndIsActive(username, true).map(usuario -> {
            User user = mapper.toUser(usuario);
            user.setAccess(usuario.getAccesos().stream().map(modulo -> moduleMapper.toModule(modulo)).collect(Collectors.toSet()));
            return user;
        });
    }

    @Override
    public User save(User user) {
        return mapper.toUser(usuarioCrudRepository.save(mapper.toUsuario(user)));
    }

    @Override
    public boolean userExist(int userId) {
        return usuarioCrudRepository.existsById(userId);
    }

    @Override
    public void delete(int userId) {
        usuarioCrudRepository.deleteById(userId);
    }
}
