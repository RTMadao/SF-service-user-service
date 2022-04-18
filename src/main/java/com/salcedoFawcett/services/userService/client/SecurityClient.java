package com.salcedoFawcett.services.userService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("auth-service")
public interface SecurityClient {

    @GetMapping("/security/encode_user/{pass}")
    public ResponseEntity<String> encodePassword(@PathVariable("pass") String pass);
}
