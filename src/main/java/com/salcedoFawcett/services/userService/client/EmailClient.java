package com.salcedoFawcett.services.userService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("email-service")
public interface EmailClient {

    @GetMapping("/email/new_user/change_password/{username}")
    public ResponseEntity sendNewUserChangePasswordEmail(@PathVariable("username") String username);

}
