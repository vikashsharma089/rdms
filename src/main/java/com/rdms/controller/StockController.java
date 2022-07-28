package com.rdms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rdms.model.StockModel;
import com.rdms.model.MonthModel;
import com.rdms.model.RationCardModel;
import com.rdms.model.StockDetails;
import com.rdms.service.MonthsService;
import com.rdms.service.StockDetailService;
import com.rdms.service.StockService;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/month")
public class StockController {
	
	
	@Autowired 
	private StockService stockService;
	
	@Autowired 
	private MonthsService monthsService;
	
	@Autowired 
	private StockDetailService stockDetailService;
	@RequestMapping(value = "/save" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Map<String,Object>> saveMonth(@RequestBody StockModel model) throws IOException{
		Map<String,Object> response = new HashMap ();
		try {
			model.setDistributed(false);
			model =stockService.save(model);
			Set<StockDetails> stockDetails = model.getItems();
			for(StockDetails obj : stockDetails ) {
				obj.setStock(model);
			}
			stockDetailService.save(stockDetails);
			response.put("status", "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.put("error", e.getMessage());
			response.put("status", "error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   
    }

	@RequestMapping(value = "/loadPendingMonth/{villageId}", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadAllPending(@PathVariable("villageId") String village ){
		Map<String, Object> response = new HashMap();
		Integer villageId = Integer.valueOf(village);
		response.put("data", stockService.loadAllPendingMonth(villageId));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/loadAll", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadAll(){
		Map<String, Object> response = new HashMap();
		response.put("data", stockService.loadAllMonth());
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/loadMonth", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadMonth(){
		Map<String, Object> response = new HashMap();
		List<MonthModel> list = monthsService.findAll();
		response.put("data",list.stream().sorted((obj1, obj2)-> obj1.getID().compareTo(obj2.getID())).collect(Collectors.toList()));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}

}
