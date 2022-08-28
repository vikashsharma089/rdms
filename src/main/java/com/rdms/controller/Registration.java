package com.rdms.controller;

import com.rdms.config.CustomPasswordEncoder;
import com.rdms.model.*;
import com.rdms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
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

    @Autowired
    private StockItemService stockItemService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;


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
            response.put("error","This Village already registered ! please contact 9416677347");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Users usersModel = new Users();
        Village villageModel= new Village();
        PurwaModel purwaModel = new PurwaModel();
        villageModel.setBlockName(registrationInput.getBlock());
        villageModel.setVillageName(registrationInput.getVillageName());
        villageModel = villageService.save(villageModel);

        usersModel.setActive(true);
        usersModel.setMobile(registrationInput.getMobile());
        usersModel.setRegistratoinDate(new Date());
        usersModel.setUserName(registrationInput.getUserName());
        usersModel.setUserNameLower(registrationInput.getUserName().toLowerCase());
       // usersModel.setPassword(bCryptPasswordEncoder.encode(registrationInput.getPassword()));
        usersModel.setPassword(customPasswordEncoder.encode(registrationInput.getPassword()));
        usersModel.setEmail(registrationInput.getEmail());
        usersModel.setName(registrationInput.getName());
        usersModel.setVillage(villageModel);
        userService.saveUser(usersModel);

        purwaModel.setPurwaName(registrationInput.getVillageName());
        purwaModel.setVillage(villageModel);
        purwaService.save(purwaModel);


        //  making item entry
        StockItem stockModel = new StockItem();
        stockModel.setItemName("Wheat");
        saveStockItem(stockModel,villageModel);
        StockItem stockModel2 = new StockItem();
        stockModel2.setItemName("Rice");
        saveStockItem(stockModel2,villageModel);


        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void saveStockItem(StockItem stockModel, Village village){

        stockModel.setVillage(village);
        stockModel =stockItemService.save(stockModel);
        Rules ruleModel = new Rules();
        Rules  ruleModel2 = new Rules();

        ruleModel.setPerUnitOrCard("unit");
        ruleModel.setKgPerUnitOrCard(2);
        ruleModel.setRate(0.0);
        ruleModel.setRationCardType("PHH");
        ruleModel.setVillage(stockModel.getVillage());
        ruleModel.setStockItem(stockModel);
        ruleModel.setVillage(village);
        ruleService.save(ruleModel);

        ruleModel2.setPerUnitOrCard("unit");
        ruleModel2.setKgPerUnitOrCard(2);
        ruleModel2.setRationCardType("AAY");
        ruleModel2.setRate(0.0);
        ruleModel2.setVillage(stockModel.getVillage());
        ruleModel2.setStockItem(stockModel);
        ruleModel2.setVillage(village);
        ruleService.save(ruleModel2);

    }
}
