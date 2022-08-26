package com.rdms.controller;

import com.rdms.model.Users;
import org.springframework.security.authentication.AuthenticationManager;
import com.rdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/user")
public class MobileAuthentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = {
            "application/json"
    })
    public ResponseEntity<Map< String, Object >> saveRationCard(@RequestBody RegistrationInput registrationInput) {
        Map< String, Object >  response = new HashMap<>();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registrationInput.getUserName(), registrationInput.getPassword()));
        Users users = userService.findUserByUserName(registrationInput.getUserName());
        if(users != null){

            response.put("token",users.getPassword());
            response.put("name", users.getName());
            response.put("village", users.getVillage().getVillageName());
            response.put("village",users.getVillage().getBlockName());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}
