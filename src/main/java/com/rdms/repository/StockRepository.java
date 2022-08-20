package com.rdms.repository;

import java.util.List;

import com.rdms.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rdms.model.StockModel;
import com.rdms.model.RationCardModel;

@Repository
public interface StockRepository extends JpaRepository<StockModel, Integer>  {
	
	@Query(value = "SELECT p FROM StockModel p  where p.distributed=false and  p.village.ID=?1")
	List<StockModel> loadAllPendingMonth(Integer villageId);


	List<StockModel> findAllByVillageOrderByIDDesc(Village village);

}
