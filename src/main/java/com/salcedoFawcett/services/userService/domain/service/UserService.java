package com.salcedoFawcett.services.userService.domain.service;

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

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public User newUser(User user){
        String encodePassword = restTemplate.getForObject("http://auth-service/security/encode_user/"+user.getPassword(),String.class);
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    public boolean updateUser(User user){
        if (userRepository.userExist(user.getId())){
            userRepository.save(user);
            return true;
        }
        else return false;
    }

    public boolean delete( int userId){
        if (userRepository.userExist(userId)){
            userRepository.delete(userId);
            return true;
        }
        else return false;
    }
}
