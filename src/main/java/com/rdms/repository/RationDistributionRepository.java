package com.rdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.rdms.model.RationDistribution;

@Repository
public interface RationDistributionRepository extends JpaRepository< RationDistribution, Integer>  {

	
	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1")
	public List<RationDistribution> findByMonthId(Integer monthId);
	
	@Query(value = "SELECT p FROM RationDistribution  p  where p.stock.ID =?1 and p.rationCard.id =?2")
	public List<RationDistribution> findByMonthAndRationCardId(Integer monthId,Integer rationCardId);
}

