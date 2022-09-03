package com.rdms.controller;

import com.rdms.model.StockDetails;
import com.rdms.model.StockModel;
import com.rdms.service.RationCardService;
import com.rdms.service.RationDistributionService;
import com.rdms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private StockService stockService;

    @Autowired
    private RationDistributionService rationDistributionService;

    @Autowired
    private RationCardService rationCardService;

    @RequestMapping(value = "/load", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map< String, Object >> searchRationCard() {
        Map<String, Object> response = new HashMap<>();
  // laod all stock and get latest stock
        List<StockModel> stockList = stockService.loadAllMonth();
        if(!stockList.isEmpty()){
            StockModel stockModel = stockList.get(0);
            List<Map<String, Object>> itemDetailsList = new ArrayList<>();



           Set<StockDetails> stockDetails =  stockModel.getItems();
            List<Object[]> currency = rationDistributionService.getTotalDistributedAmmount(stockModel.getID());


            for(StockDetails model :stockDetails ){
                if(model.getInitalQuantiy() != null && model.getInitalQuantiy() >0){
                    Map<String, Object> itemDetail = new HashMap<>();
                    itemDetail.put("name", model.getStockItem().getItemName());
                    itemDetail.put("initalqt", model.getInitalQuantiy());
                    itemDetail.put("currentqt", model.getQuantity());
                    itemDetail.put("stockName", stockModel.getMonthId().getMonthName()+"-"+stockModel.getDetails());
                    itemDetailsList.add(itemDetail);
                }

            }

            List<String> distributedCards = rationDistributionService.findDistributedCardByStockAndVillageId(stockModel.getID());
            if(!currency.isEmpty()){
                Object amount[] = currency.get(0);
                response.put("amount",amount == null || amount[0] == null ? 0:amount[0]);
            }else{
                response.put("amount","0");
            }


            response.put("card", rationCardService.getAllRemaingRationCardCount(distributedCards));
            response.put("stockName", stockModel.getMonthId().getMonthName()+"-"+stockModel.getDetails());

            response.put("data",itemDetailsList );
        }else{
            response.put("data", new ArrayList<>());
        }

        return new ResponseEntity <> (response, HttpStatus.OK);
    }
}

