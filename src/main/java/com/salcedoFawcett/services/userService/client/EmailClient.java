package com.salcedoFawcett.services.userService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("email-service")
public interface EmailClient {

    @RequestMapping("/email/change_password/new_user/{user_id}/{token}")
    public ResponseEntity sendNewUserChangePasswordEmail(@PathVariable("user_id") int userId, @PathVariable("token") String token);

    @RequestMapping("/email/change_password/:userID/:token")
    public ResponseEntity sendChangePasswordEmail(@PathVariable("user_id") int userId, @PathVariable("token") String token);
}
