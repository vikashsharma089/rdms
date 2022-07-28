package com.rdms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	
    @RequestMapping("/")
    public String getWelcomePage(Model model){

         System.out.println("Welcome to java ");
         model.addAttribute("pageName", "vikash");
         
         return "welcome";
    }
    
    @RequestMapping(value = "/loadConfiguration", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadAll(){
		Map<String, Object> response = new HashMap();
		//response.put("data", rationCardService.getAllRationCardByVillageId(1));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
}
