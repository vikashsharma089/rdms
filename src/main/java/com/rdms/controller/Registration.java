package com.rdms.controller;

import com.rdms.model.PurwaModel;
import com.rdms.model.Users;
import com.rdms.model.Village;
import com.rdms.service.PurwaService;
import com.rdms.service.UserService;
import com.rdms.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class Registration {
    @Autowired
    private VillageService villageService;
    @Autowired
    private UserService userService;
    @Autowired
    private PurwaService purwaService;



    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {
            "application/json"
    })
    public ResponseEntity<Map< String, Object >> saveRationCard(@RequestBody RegistrationInput registrationInput) throws IOException {
        Map<String, Object> response = new HashMap();
        if(registrationInput.getUserName() == null || registrationInput.getUserName() == "" || registrationInput.getUserName() == "undefined"){
            response.put("status", "error");
            response.put("error","Username can not be blank !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(registrationInput.getPassword() == null || registrationInput.getPassword() == "" || registrationInput.getPassword() == "undefined"){
            response.put("status", "error");
            response.put("error","password can not be blank !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(registrationInput.getVillageName() == null || registrationInput.getVillageName() == "" || registrationInput.getVillageName() == "undefined"){
            response.put("status", "error");
            response.put("error","Please select Village !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if(registrationInput.getMobile() == null || registrationInput.getMobile() == "" || registrationInput.getMobile() == "undefined"){
            response.put("status", "error");
            response.put("error","Mobile number is blank !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(userService.findUserByUserName(registrationInput.getUserName()) !=null){
            response.put("status", "error");
            response.put("error","Username already exist!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(!villageService.findByVillageName(registrationInput.villageName).isEmpty()){
            response.put("status", "error");
            response.put("error","This Village already registered !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Users usersModel = new Users();
        Village villageModel= new Village();
        PurwaModel purwaModel = new PurwaModel();
        villageModel.setBlockName("Pauli");
        villageModel.setVillageName(registrationInput.getVillageName());
        villageModel = villageService.save(villageModel);

        usersModel.setActive(true);
        usersModel.setMobile(registrationInput.getMobile());
        usersModel.setUserName(registrationInput.getUserName());
        usersModel.setUserNameLower(registrationInput.getUserName().toLowerCase());
        //usersModel.setPassword(new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A).encode(registrationInput.getPassword().trim()));
        usersModel.setPassword(registrationInput.getPassword().trim());
        usersModel.setEmail(registrationInput.getEmail());
        usersModel.setName(registrationInput.getName());
        usersModel.setVillage(villageModel);
        userService.saveUser(usersModel);

        purwaModel.setPurwaName(registrationInput.getVillageName());
        purwaModel.setVillage(villageModel);
        purwaService.save(purwaModel);


        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
