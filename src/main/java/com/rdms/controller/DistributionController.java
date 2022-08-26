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
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {
        "application/json"
    })
    public ResponseEntity < Map < String, Object >> saveRationCard(@RequestBody RationDistribution distributionModel) throws IOException {
        Map< String, Object > response = new HashMap<>();
        try{

            rationDistributionService.Distribute(distributionModel,response);
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