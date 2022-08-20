package com.rdms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rdms.model.*;
import com.rdms.service.PurwaService;
import com.rdms.service.UserService;
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

import com.rdms.service.RationCardService;
import com.rdms.service.RationDistributionService;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/rationcard")
public class RationCardController {
	
	@Autowired
	private RationCardService rationCardService;

	 @Autowired
	 private UserService userService;
	
	@Autowired 
	private RationDistributionService rationDistributionService;

	@Autowired
	private PurwaService purwaService;
	
	@RequestMapping(value = "/upload" , method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE  })
    public ResponseEntity<Map<String,Object>> submitInspection(@RequestParam(value="file") MultipartFile filse) throws IOException{
		
		Map<String,Object> response = new HashMap ();
		try {

		
		Workbook workbook = new XSSFWorkbook(filse.getInputStream()); 
		Sheet sheet  = workbook.getSheetAt(0);
		List<String> availableRationCard = rationCardService.getAllRationCardNumberByVillageId(1);
		List<RationCardModel> models = new ArrayList<RationCardModel>();

		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
		  Row currentRow = rows.next();
		  
		  RationCardModel model = new RationCardModel();
			model.setVillage(userService.getVillage());


			String cardNumber =  currentRow.getCell(1).getStringCellValue().trim();

		  if(!availableRationCard.contains(cardNumber)) {
			  String holderName = currentRow.getCell(2).getStringCellValue().trim();
			  String fatherOrHusband = currentRow.getCell(3).getStringCellValue().trim();
			  String motherName = currentRow.getCell(4).getStringCellValue().trim();
			  Integer  unit = Integer.valueOf(currentRow.getCell(5).getStringCellValue().trim());
			  String cardType = currentRow.getCell(7).getStringCellValue().trim();
			  //Integer purwaId = (int)(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue() );

			  model.setPurwa(purwaService.findAllByVillage().get(0));

			  model.setCardHolder(holderName);
			  model.setFatherOrHusband(fatherOrHusband);
			  model.setMotherName(motherName);
			  model.setUnit(unit);
			  model.setCardNumber(cardNumber);
			  model.setCartType(cardType);
			  models.add(model);
			  
		  }
		  
		}
		
		rationCardService.saveAll(models);
		     
		workbook.close();
		response.put("status", "success");
		response.put("total", models.size());
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.put("error", e.getMessage());
			response.put("status", "error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		   
    }
	
	
	@RequestMapping(value = "/save" , method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Map<String,Object>> saveRationCard(@RequestBody RationCardModel model) throws IOException{
		Map<String,Object> response = new HashMap ();
		try {
			model.setVillage(userService.getVillage());
			model.setPurwa(purwaService.findAllByVillage().get(0));
			 rationCardService.save(model);
			 response.put("status", "success");
		return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response.put("error", e.getMessage());
			response.put("status", "error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		   
    }
	@RequestMapping(value = "/loadAll", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadAll(){
		Map<String, Object> response = new HashMap();
		response.put("data", rationCardService.getAllRationCardByVillageId());
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
    	
	@RequestMapping(value = "/search/{searchKey}", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> searchRationCard(@PathVariable("searchKey") String searchKey ){
		Map<String, Object> response = new HashMap();
		response.put("data", rationCardService.searchRationCard(searchKey));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
    		
	@RequestMapping(value = "/loadRemaining/{stockId}", method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<Map<String,Object>> loadRemaining(@PathVariable("stockId") String stock ){

		Integer stockId = Integer.valueOf(stock);
		List<String> distributedCards = rationDistributionService.findDistributedCardByStockAndVillageId(stockId);
		Map<String, Object> response = new HashMap();
		response.put("data", rationCardService.getAllRemaingRationCard(distributedCards));
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}

}
