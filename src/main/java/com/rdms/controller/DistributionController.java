package com.rdms.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.rdms.service.*;
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

import com.rdms.model.StockModel;
import com.rdms.model.DistributionDetails;
import com.rdms.model.RationDistribution;
import com.rdms.model.StockDetails;

@RestController
@CrossOrigin("*")
@RequestMapping("/distribution")
public class DistributionController {

    @Autowired
    private RationDistributionService rationDistributionService;
    
    @Autowired
    private DistributionDetailService distributionDetailService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockDetailService stockDetailService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {
        "application/json"
    })
    public ResponseEntity < Map < String, Object >> saveRationCard(@RequestBody RationDistribution distributionModel) throws IOException {


        Map < String, Object > response = new HashMap();
        try {

            if (rationDistributionService.findByStockAndRationCardId(distributionModel.getStock().getID(), distributionModel.getRationCard().getID()).isEmpty()) {

                Map < Integer, Double > quantityMap = new HashMap();
                distributionModel.setDistributedOn(Instant.now());
                distributionModel.setVillage(userService.getVillage());
                RationDistribution rationDistributionModel = rationDistributionService.save(distributionModel);
               
                for (DistributionDetails distributionDetails: rationDistributionModel.getDetails()) {
                    quantityMap.put(distributionDetails.getStockItem().getID(), distributionDetails.getQuantity());
                    distributionDetails.setDistribution(rationDistributionModel);
                    
                }
                distributionDetailService.saveAll(rationDistributionModel.getDetails());
               
                // to update stock after distribution 
                Optional < StockModel > stockModel = stockService.findMonthById(distributionModel.getStock().getID());
                if (!stockModel.isEmpty()) {
                    StockModel model = stockModel.get();
                    Set < StockDetails > stockItems = model.getItems();
                    for (StockDetails stockDetails: stockItems) {
                        Double distributedQuantity = quantityMap.get(stockDetails.getStockItem().getID());
                       if(distributedQuantity != null) {
                    	   Double remainsStock = (stockDetails.getQuantity() - distributedQuantity);
                           stockDetails.setQuantity(remainsStock);   
                       }
                    }
                    stockDetailService.save(stockItems);
                }

                response.put("status", "success");
            } else {
                response.put("status", "error");
                response.put("error", "This card has been already added.");
            }

            return new ResponseEntity < > (response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("status", "error");
            return new ResponseEntity < > (response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @RequestMapping(value = "/view/{monthId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity < Map < String, Object >> searchRationCard(@PathVariable("monthId") String monthIds) {
        Integer monthId = Integer.valueOf(monthIds);
        Map < String, Object > response = new HashMap();
        response.put("data", rationDistributionService.findByStockId(monthId));
        return new ResponseEntity < > (response, HttpStatus.OK);
    }
}