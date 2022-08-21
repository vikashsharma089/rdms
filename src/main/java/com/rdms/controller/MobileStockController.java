package com.rdms.controller;


import com.rdms.model.RationCardModel;
import com.rdms.model.Rules;
import com.rdms.model.StockDetails;
import com.rdms.model.StockModel;
import com.rdms.service.RationCardService;
import com.rdms.service.RuleService;
import com.rdms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

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
        List<StockModel> stockModelList = stockService.loadAllPendingMonth();
        for (StockModel stockModel : stockModelList) {
            Map<String, Object> response = new HashMap();
            response.put("id", stockModel.getID());
            response.put("name", stockModel.getMonthId().getMonthName() + "-" + stockModel.getDetails());
            listObj.add(response);
        }
        return new ResponseEntity<>(listObj, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadPendingStock", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, Object>> loadPendingStock() {
        Map<String, Object> response = new HashMap();
        response.put("data", stockService.loadAllPendingMonth());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/loadRules", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, Object>> loadRules() {
        Map<String, Object> response = new HashMap();
        List<Rules> rulesList = ruleService.findByVillageId();
        for (Rules ruleModel : rulesList) {
            response.put(ruleModel.getRationCardType() + "_" + ruleModel.getStockItem().getItemName(), ruleModel);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map<String, Object>> searchRationCard(@RequestBody MobleRationSearchInput mobleRationSearchInput) {
        Map<String, Object> response = new HashMap();
        Map<String, Object> rules = new HashMap();
        Integer stockeId = Integer.valueOf(mobleRationSearchInput.getStockId());
        String SearchKey = mobleRationSearchInput.getCardNumber();
        List<Map<String, Object>> itemList = new ArrayList<>();

        List<RationCardModel> rationcardList = rationCardService.searchRationCard(SearchKey);
        response.put("data", rationcardList.get(0));

        List<Rules> rulesList = ruleService.findByVillageId();
        for (Rules ruleModel : rulesList) {
            rules.put(ruleModel.getRationCardType() + "_" + ruleModel.getStockItem().getItemName(), ruleModel);
        }

        Optional<StockModel> stockModelOptional = stockService.findMonthById(stockeId);
        Double totalAmount = 0.0;
        if (!stockModelOptional.isEmpty()) {
            StockModel stockModel = stockModelOptional.get();
            for (StockDetails model : stockModel.getItems()) {
                if (model.getInitalQuantiy() != null && model.getInitalQuantiy() > 0) {
                    Map<String, Object> itemDetail = new HashMap<>();
                    Object config = rules.get(rationcardList.get(0).getCartType() + "_" + model.getStockItem().getItemName());
                    Integer quantityGk = getCalculatedQuantity(config, rationcardList.get(0).getUnit());
                    Double amount = getCalculatedAmount(config,quantityGk);
                    totalAmount = totalAmount+ amount;
                    itemDetail.put("id", model.getStockItem().getID());
                    itemDetail.put("name", model.getStockItem().getItemName());
                    itemDetail.put("quantity", quantityGk);
                    itemDetail.put("amount", amount);
                    itemList.add(itemDetail);
                }
            }


        }
        response.put("items",itemList);
        response.put("totalAmount",totalAmount);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Integer getCalculatedQuantity(Object config, Integer unit) {
        Rules rules = (Rules)config;
        Integer totalKg = 0;
         if(rules.getPerUnitOrCard().equalsIgnoreCase("unit")){
             totalKg = unit* rules.getKgPerUnitOrCard();
         }
        if(rules.getPerUnitOrCard().equalsIgnoreCase("card")){
            totalKg = 1* rules.getKgPerUnitOrCard();
        }
        return totalKg;
    }

    public Double getCalculatedAmount(Object config, Integer quantity) {
        Rules rules = (Rules)config;
        double amount = 0;
        amount = quantity*rules.getRate();
        return amount;
    }

}