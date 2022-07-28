package com.rdms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	
	@RequestMapping(value = "/saveItems" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Map<String,Object>> saveMonth(@RequestBody StockItem stockModel) throws IOException{
		Map<String,Object> response = new HashMap ();
		try {
			stockModel =stockItemService.save(stockModel);
			Rules  ruleModel = new Rules();
			
			ruleModel.setPerUnitOrCard("unit");
			ruleModel.setKgPerUnitOrCard(2);
			ruleModel.setRationCardType("all");
			ruleModel.setVillage(stockModel.getVillage());
			ruleModel.setStockItem(stockModel);
			ruleService.save(ruleModel);
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
	
	@RequestMapping(value = "/loadItems/{villageId}", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadItems(@PathVariable("villageId") String village ){
		Map<String, Object> response = new HashMap();
		Integer villageId = Integer.valueOf(village);
		response.put("data", stockItemService.findAllByVillage(villageId));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/loadRules/{villageId}", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadRules(@PathVariable("villageId") String village ){
		Map<String, Object> response = new HashMap();
		Integer villageId = Integer.valueOf(village);
		response.put("data", ruleService.findByVillageId(villageId));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	

}
