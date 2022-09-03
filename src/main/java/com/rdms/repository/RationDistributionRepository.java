package com.rdms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.rdms.model.RationDistribution;

@Repository
public interface RationDistributionRepository extends JpaRepository< RationDistribution, Integer>  {


	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1 and p.village.ID =?2 order by p.ID DESC")
	public List<RationDistribution> findByStockId(Integer monthId, Integer villageId);

	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1 and p.village.ID =?2 order by p.ID DESC")
	public Page<RationDistribution> findByStockIdPagination(Integer monthId, Integer villageId, PageRequest pageable);
	
	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1 and p.rationCard.ID =?2 and p.village.ID =?3")
	public List<RationDistribution> findByStockAndRationCardId(Integer stockId,Integer rationCardId,Integer villageId );
	
	@Query(value = "SELECT p.rationCard.cardNumber FROM RationDistribution  p  where p.stock.ID =?1 and p.village.ID =?2")
	public List<String> findDistributedCardByStockAndVillageId(Integer stockId,Integer rationCardId);

	@Query(value = "SELECT p.rationCard.cartType, COUNT(p.rationCard.cartType) FROM RationDistribution AS p where p.stock.ID =?1 and p.village.ID =?2 GROUP BY p.rationCard.cartType ")
	List<Object[]> countDistributedRation(Integer stockId, Integer villageId);

	@Query(value = "SELECT p.totalAmount as amount, SUM(p.totalAmount) FROM RationDistribution AS p where p.stock.ID =?1 and p.village.ID =?2")
	List<Object[]> getTotalDistributedAmmount(Integer stockId, Integer villageId);



}

