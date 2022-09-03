package com.rdms.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import com.rdms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private  DistributionDetailService distributionDetailService;
    

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

    @RequestMapping(value = "/viewCount/{stockId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity < Map < String, Object >> countDistributedRation(@PathVariable("stockId") String stockId) {
        Integer monthId = Integer.valueOf(stockId);
        Map < String, Object > response = new HashMap();
        List<Object[]> finalList = new ArrayList<>();
        finalList= rationDistributionService.countDistributedRation(monthId);
        finalList.addAll(rationDistributionService.getTotalDistributedAmmount(monthId));
        response.put("data", finalList);
        return new ResponseEntity < > (response, HttpStatus.OK);
    }
    @RequestMapping(value = "/view/{monthId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity < Map < String, Object >> searchRationCard(@PathVariable("monthId") String monthIds, @RequestParam(value ="pageNumber", defaultValue = "0") int page,
                                                                     @RequestParam(value ="pageSize", defaultValue = "3") int size) {

        page = page-1;
        Integer monthId = Integer.valueOf(monthIds);
        Map < String, Object > response = new HashMap();
        Page<RationDistribution> requestedPage = rationDistributionService.findByStockIdPagination(monthId,page,size);
        response.put("totalElement", requestedPage.getTotalElements());
        response.put("totalPage", requestedPage.getTotalPages());
        response.put("totalNumber", requestedPage.getNumberOfElements());
        response.put("currentPage", requestedPage.getNumber());
        response.put("data", requestedPage.getContent());
        return new ResponseEntity < > (response, HttpStatus.OK);
    }
}