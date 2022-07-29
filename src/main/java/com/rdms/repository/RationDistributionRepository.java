package com.rdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.rdms.model.RationDistribution;

@Repository
public interface RationDistributionRepository extends JpaRepository< RationDistribution, Integer>  {

	
	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1")
	public List<RationDistribution> findByStockId(Integer monthId);
	
	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1 and p.rationCard.id =?2")
	public List<RationDistribution> findByStockAndRationCardId(Integer stockId,Integer rationCardId);
	
	@Query(value = "SELECT p.rationCard.cardNumber FROM RationDistribution  p  where p.stock.ID =?1 and p.village.id =?2")
	public List<String> findDistributedCardByStockAndVillageId(Integer stockId,Integer rationCardId);
	
	
}

