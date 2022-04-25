package com.salcedoFawcett.services.userService.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salcedoFawcett.services.userService.domain.exception.UserNotFoundException;
import com.salcedoFawcett.services.userService.domain.model.Module;
import com.salcedoFawcett.services.userService.domain.model.SecureUser;
import com.salcedoFawcett.services.userService.domain.model.User;
import com.salcedoFawcett.services.userService.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<SecureUser>> getUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get_by_id/{id}")
    public  ResponseEntity<SecureUser> getUser(@PathVariable("id") int userId) {
        return userService.getUserById(userId)
                .map( user -> new ResponseEntity<>(user.getSecureUser(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/get_by_name/{username}")
    public  ResponseEntity<SecureUser> getUserByusername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username)
                .map( user -> new ResponseEntity<>(user.getSecureUser(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/full_user/get_by_id/{id}")
    public  ResponseEntity<User> getFullUser(@PathVariable("id") int userId) {
        return userService.getUserById(userId)
                .map( user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/full_user/get_by_name/{username}")
    public  ResponseEntity<User> getFullUserByusername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username)
                .map( user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public  ResponseEntity<SecureUser> saveUser(@RequestBody User user){
        user.setPassword("SalcedoFawcett");
        return new ResponseEntity<>(userService.newUser(user).getSecureUser(),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public  ResponseEntity<SecureUser> updateUser(@RequestBody User user) throws IOException, UserNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        user.setAccess(mapper.convertValue(user.getAccess(), new TypeReference<Set<Module>>(){}));
        if (userService.updateUser(user)) return new ResponseEntity<>(user.getSecureUser(),HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/change_password/{id}")
    public  ResponseEntity updateUserPassword(@PathVariable("id") int userId, @RequestBody User user) throws UserNotFoundException {
        userService.updateUserPassword(user.getPassword(),userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity updateUser(@PathVariable("id") int userId){
        if (userService.delete(userId)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
