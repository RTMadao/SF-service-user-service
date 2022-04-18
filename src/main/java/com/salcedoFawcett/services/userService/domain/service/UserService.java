package com.salcedoFawcett.services.userService.domain.service;

import com.salcedoFawcett.services.userService.client.SecurityClient;
import com.salcedoFawcett.services.userService.domain.exception.UserNotFoundException;
import com.salcedoFawcett.services.userService.domain.model.SecureUser;
import com.salcedoFawcett.services.userService.domain.model.User;
import com.salcedoFawcett.services.userService.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityClient securityClient;

    @Autowired
    @Qualifier(value = "remoteRestTemplate")
    private RestTemplate restTemplate;

    public List<SecureUser> getAll(){
        return  userRepository.getAll().stream()
                .map(User::getSecureUser)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(int userId){
        return userRepository.getUserById(userId);
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public User newUser(User user){
        String encodePassword = securityClient.encodePassword(user.getPassword()).getBody();
        user.setPassword(encodePassword);
        user.setActive(true);
        return userRepository.save(user);
    }

    public boolean updateUser(User user) throws UserNotFoundException {
        if (userRepository.userExist(user.getId())){
            user.setPassword(userRepository.getUserById(user.getId()).map(User::getPassword).orElseThrow(() -> new UserNotFoundException("Usuario No encontrado")));
            userRepository.save(user);
            return true;
        }
        else {
            throw new UserNotFoundException("Usuario No encontrado");
        }
    }

    public boolean updateUserPassword(String newPass, int userId) throws UserNotFoundException {
        userRepository.save(userRepository.getUserById(userId).map(user -> {
            String encodePassword = securityClient.encodePassword(newPass).getBody();
            user.setPassword(encodePassword);
            return user;
        }).orElseThrow(() -> new UserNotFoundException("Usuario No encontrado")));
        return true;
    }

    public boolean delete( int userId){
        return userRepository.getUserById(userId).map(user -> {
            user.setActive(false);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}
