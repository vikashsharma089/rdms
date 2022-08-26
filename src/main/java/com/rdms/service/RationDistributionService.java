package com.rdms.service;

import java.time.Instant;
import java.util.*;

import com.rdms.model.DistributionDetails;
import com.rdms.model.StockDetails;
import com.rdms.model.StockModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdms.model.RationDistribution;
import com.rdms.repository.RationDistributionRepository;

@Service
public class RationDistributionService {
	
	@Autowired
	private RationDistributionRepository rationDistributionRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private DistributionDetailService distributionDetailService;

	@Autowired
	private StockService stockService;

	@Autowired
	private StockDetailService stockDetailService;


	public RationDistribution save(RationDistribution model) {
		return rationDistributionRepository.save(model);
	}
	
	public List<RationDistribution> findByStockId(Integer stockId) {
		Integer villageId = userService.getVillage().getID();
		return rationDistributionRepository.findByStockId(stockId,villageId);
	}
	
	public List<RationDistribution> findByStockAndRationCardId(Integer stockId,Integer rationCardId) {
		 Integer villageId = userService.getVillage().getID();
		return rationDistributionRepository.findByStockAndRationCardId(stockId, rationCardId, villageId);
	}

	public List<String> findDistributedCardByStockAndVillageId(Integer stockId) {
		Integer villageId =userService.getVillage().getID();
		return rationDistributionRepository.findDistributedCardByStockAndVillageId(stockId, villageId);
	}

	public void Distribute(RationDistribution distributionModel, Map< String, Object > response){



			if (findByStockAndRationCardId(distributionModel.getStock().getID(), distributionModel.getRationCard().getID()).isEmpty()) {

				Map < Integer, Double > quantityMap = new HashMap();
				distributionModel.setDistributedOn(Instant.now());
				distributionModel.setVillage(userService.getVillage());
				RationDistribution rationDistributionModel = save(distributionModel);

				for (DistributionDetails distributionDetails: rationDistributionModel.getDetails()) {
					quantityMap.put(distributionDetails.getStockItem().getID(), distributionDetails.getQuantity());
					distributionDetails.setDistribution(rationDistributionModel);

				}
				distributionDetailService.saveAll(rationDistributionModel.getDetails());

				// to update stock after distribution
				Optional<StockModel> stockModel = stockService.findMonthById(distributionModel.getStock().getID());
				if (!stockModel.isEmpty()) {
					StockModel model = stockModel.get();
					Set<StockDetails> stockItems = model.getItems();
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

	}

}
