package com.rdms.controller;


import com.rdms.model.Rules;
import com.rdms.model.StockModel;
import com.rdms.service.RationCardService;
import com.rdms.service.RuleService;
import com.rdms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/mobile")
public class MobileStockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private RuleService ruleService;

    @Autowired
    private RationCardService rationCardService;

    @RequestMapping(value = "/loadStock", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Map<String, Object>>> loadAllPending() {
        List<Map<String, Object>> listObj = new ArrayList<>();
        List<StockModel> stockModelList= stockService.loadAllPendingMonth();
        for(StockModel  stockModel : stockModelList){
            Map<String, Object> response = new HashMap();
            response.put("id", stockModel.getID());
            response.put("name", stockModel.getMonthId().getMonthName()+"-"+stockModel.getDetails());
            listObj.add(response);
        }
        return new ResponseEntity<>(listObj, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadRules", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadRules( ){
        Map<String, Object> response = new HashMap();
        List<Rules>  rulesList = ruleService.findByVillageId();
        for(Rules ruleModel : rulesList){
            response.put(ruleModel.getRationCardType()+"_"+ruleModel.getStockItem().getItemName(), ruleModel);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{searchKey}", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> searchRationCard(@PathVariable("searchKey") String searchKey ){
        Map<String, Object> response = new HashMap();
        response.put("data", rationCardService.searchRationCard(searchKey));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}