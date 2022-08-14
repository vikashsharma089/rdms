package com.rdms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rdms.model.Rules;
import com.rdms.model.StockItem;
import com.rdms.model.StockModel;
import com.rdms.service.RuleService;
import com.rdms.service.StockItemService;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/setting")
public class SettingController {
	
	@Autowired 
	private StockItemService stockItemService;
	
	@Autowired 
	private RuleService  ruleService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/saveItems" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Map<String,Object>> saveItem(@RequestBody StockItem stockModel) throws IOException{
		Map<String,Object> response = new HashMap ();
		try {
			stockModel.setVillage(userService.getVillage());
			stockModel =stockItemService.save(stockModel);
			Rules  ruleModel = new Rules();
			Rules  ruleModel2 = new Rules();
			
			ruleModel.setPerUnitOrCard("unit");
			ruleModel.setKgPerUnitOrCard(2);
			ruleModel.setRationCardType("a");
			ruleModel.setVillage(stockModel.getVillage());
			ruleModel.setStockItem(stockModel);
			ruleModel.setVillage(userService.getVillage());
			ruleService.save(ruleModel);

			ruleModel2.setPerUnitOrCard("unit");
			ruleModel2.setKgPerUnitOrCard(2);
			ruleModel2.setRationCardType("b");
			ruleModel2.setVillage(stockModel.getVillage());
			ruleModel2.setStockItem(stockModel);
			ruleModel2.setVillage(userService.getVillage());
			ruleService.save(ruleModel2);
			response.put("status", "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.put("error", e.getMessage());
			response.put("status", "error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   
    }
	
	@RequestMapping(value = "/updateItem" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Map<String,Object>> updateItem(@RequestBody StockItem stockItemModel) throws IOException{
		Map<String,Object> response = new HashMap ();
		try {
			stockItemModel =stockItemService.save(stockItemModel);
			response.put("status", "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.put("error", e.getMessage());
			response.put("status", "error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   
    }
	
	@RequestMapping(value = "/updateRule" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Map<String,Object>> updateRule(@RequestBody Rules ruleModel) throws IOException{
		Map<String,Object> response = new HashMap ();
		try {
			ruleService.save(ruleModel);
			response.put("status", "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.put("error", e.getMessage());
			response.put("status", "error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   
    }
	
	@RequestMapping(value = "/loadItems", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadItems(){
		Map<String, Object> response = new HashMap();
		response.put("data", stockItemService.findAllByVillage());
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/loadRules", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadRules( ){
		Map<String, Object> response = new HashMap();
		response.put("data", ruleService.findByVillageId());
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	

}
