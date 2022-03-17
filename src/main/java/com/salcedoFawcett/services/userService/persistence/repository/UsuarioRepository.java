package com.salcedoFawcett.services.userService.persistence.repository;

import com.salcedoFawcett.services.userService.domain.model.User;
import com.salcedoFawcett.services.userService.domain.repository.UserRepository;
import com.salcedoFawcett.services.userService.persistence.crud.UsuarioCrudRepository;
import com.salcedoFawcett.services.userService.persistence.entity.Usuario;
import com.salcedoFawcett.services.userService.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioCrudRepository.findAll();
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return usuarioCrudRepository.findById(userId).map(usuario -> mapper.toUser(usuario));
    }

    @Override
    public User getUserByUsername(String username) {
        return mapper.toUser(usuarioCrudRepository.findByUsername(username));
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
