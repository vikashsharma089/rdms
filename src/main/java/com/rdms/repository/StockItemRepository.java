package com.rdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rdms.model.RationCardModel;
import com.rdms.model.StockItem;



@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Integer>  {
	
	
	@Query(value = "SELECT p FROM StockItem p  where p.village.ID=?1")
	List<StockItem> findAllByVillage(Integer villageId);
	

}
