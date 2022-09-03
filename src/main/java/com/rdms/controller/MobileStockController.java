package com.rdms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdms.model.*;
import com.rdms.service.RationCardService;
import com.rdms.service.RationDistributionService;
import com.rdms.service.RuleService;
import com.rdms.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/mobile")
public class MobileStockController {
    private static final int IMG_WIDTH = 300;
    private static final int IMG_HEIGHT = 150;

    @Autowired
    private StockService stockService;
    @Autowired
    private RuleService ruleService;

    @Autowired
    private RationDistributionService rationDistributionService;

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

        Map<String, Object> rules = new HashMap();
        Map<String, Object> finalresonse2 = new HashMap();
        Integer stockeId = Integer.valueOf(mobleRationSearchInput.getStockId());
        String SearchKey = mobleRationSearchInput.getCardNumber();

        List<Map<String, Object>> finalresponse = new ArrayList<>();
        List<Rules> rulesList = ruleService.findByVillageId();
        for (Rules ruleModel : rulesList) {
            rules.put(ruleModel.getRationCardType() + "_" + ruleModel.getStockItem().getItemName(), ruleModel);
        }

        List<RationCardModel> rationcardList = rationCardService.searchRationCard(SearchKey);
        for (RationCardModel rationcardModel : rationcardList) {
            List<Map<String, Object>> itemList = new ArrayList<>();

            Map<String, Object> response = new HashMap();
            response.put("data", rationcardModel);


            Optional<StockModel> stockModelOptional = stockService.findMonthById(stockeId);
            Double totalAmount = 0.0;
            if (!stockModelOptional.isEmpty()) {
                StockModel stockModel = stockModelOptional.get();
                for (StockDetails model : stockModel.getItems()) {
                    if (model.getInitalQuantiy() != null && model.getInitalQuantiy() > 0) {
                        Map<String, Object> itemDetail = new HashMap<>();
                        Object config = rules.get(rationcardModel.getCartType() + "_" + model.getStockItem().getItemName());
                        Integer quantityGk = getCalculatedQuantity(config, rationcardModel.getUnit());
                        Double amount = getCalculatedAmount(config, quantityGk);
                        totalAmount = totalAmount + amount;
                        itemDetail.put("id", model.getStockItem().getID());
                        itemDetail.put("name", model.getStockItem().getItemName());
                        itemDetail.put("quantity", quantityGk);
                        itemDetail.put("amount", amount);
                        itemList.add(itemDetail);
                    }
                }


            }
            response.put("items", itemList);
            response.put("totalAmount", totalAmount);
            finalresponse.add(response);
        }

        finalresonse2.put("results", finalresponse);
        return new ResponseEntity<>(finalresonse2, HttpStatus.OK);
    }


    @RequestMapping(value = "/addDistribute", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map<String, Object>> searchRationCard(@RequestBody MobileRationDistributeInput mobleRationSearchInput) {
        Map<String, Object> response = new HashMap<>();
        try {
            RationDistribution rationCard = prepareData(mobleRationSearchInput, null);
            rationDistributionService.Distribute(rationCard, response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("status", "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/distributeByImage" , method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE  })
    public ResponseEntity<Map<String,Object>> submitInspection(@RequestParam String inputJsonString, @RequestParam(value="file") MultipartFile file) throws IOException {

        Map<String,Object> response = new HashMap ();
        try {
            ObjectMapper mapper  = new ObjectMapper();
            Map<String, MultipartFile> imageData = new HashMap();
            MobileRationDistributeInput inputJson = mapper.readValue(inputJsonString, MobileRationDistributeInput.class);
            RationDistribution rationCard = prepareData(inputJson, file);
            rationDistributionService.Distribute(rationCard, response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("status", "error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public RationDistribution prepareData(MobileRationDistributeInput mobleRationSearchInput, MultipartFile file) throws IOException {
        Integer rationCardId = Integer.valueOf(mobleRationSearchInput.getCardid());
        Integer StockId = Integer.valueOf(mobleRationSearchInput.getStockid());
        Double totalAmount = Double.valueOf(mobleRationSearchInput.getTotal_amount());
        RationDistribution rationCard = new RationDistribution();
        RationCardModel ration = new RationCardModel(rationCardId);

        rationCard.setRationCard(ration);
        rationCard.setTotalAmount(totalAmount);
        rationCard.setStock(new StockModel(StockId));

        Set<DistributionDetails> details = new HashSet<>();

        for (MobilestockItems items : mobleRationSearchInput.getItems()) {
            Integer id = Integer.valueOf(items.getId());
            Double quantity = Double.valueOf(items.getQuantity());
            Double amount = Double.valueOf(items.getAmount());
            DistributionDetails itemModel = new DistributionDetails();

            itemModel.setStockItem(new StockItem(id));
            itemModel.setQuantity(quantity);
            itemModel.setAmount(amount);
            details.add(itemModel);

        }
        rationCard.setDetails(details);
        if(file != null){
            Long size = file.getSize();
            System.out.println("File Size "+size);

            BufferedImage bi = ImageIO.read(file.getInputStream());
            Image image = bi.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
            BufferedImage outputImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(image, 0, 0, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(outputImage, "png", baos);
            byte[] imageByps = baos.toByteArray();
            rationCard.setSignature(imageByps);
        }
        return rationCard;
    }

    @RequestMapping(value = "/viewCount/{stockId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity < Map < String, Object >> countDistributedRation(@PathVariable("stockId") String stockId) {
        Integer monthId = Integer.valueOf(stockId);
        Map < String, Object > response = new HashMap();
        List<Object[]> finalList = new ArrayList<>();
        finalList= rationDistributionService.countDistributedRation(monthId);
        ;
        response.put("data", finalList);
        response.put("amount", rationDistributionService.getTotalDistributedAmmount(monthId).get(0));
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

    public Integer getCalculatedQuantity(Object config, Integer unit) {
        Rules rules = (Rules) config;
        Integer totalKg = 0;
        if (rules.getPerUnitOrCard().equalsIgnoreCase("unit")) {
            totalKg = unit * rules.getKgPerUnitOrCard();
        }
        if (rules.getPerUnitOrCard().equalsIgnoreCase("card")) {
            totalKg = 1 * rules.getKgPerUnitOrCard();
        }
        return totalKg;
    }

    public Double getCalculatedAmount(Object config, Integer quantity) {
        Rules rules = (Rules) config;
        double amount = 0;
        amount = quantity * rules.getRate();
        return amount;
    }


}