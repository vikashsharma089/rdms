package com.rdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rdms.model.RationCardModel;



public interface RationCardRepository extends JpaRepository<RationCardModel, Integer>  {
	
	
	@Query(value = "SELECT p.cardNumber FROM RationCardModel p  where p.village.ID=?1")
	List<String> getAllRationCardNumberByVillageId(Integer villageId);
	
	@Query(value = "SELECT p FROM RationCardModel p  where p.village.ID=?1")
	List<RationCardModel> getAllRationCardByVillageId(Integer villageId);
	
	/*
	 * @Query("SELECT p FROM RationCardModel p WHERE " +
	 * "p.cardNumber LIKE CONCAT('%',:searcyKey, '%')" )
	 */
	@Query("SELECT p FROM RationCardModel p WHERE " +
			 "p.cardNumber LIKE %?1" )
	List<RationCardModel> searchRationCard(String searcyKey);
	
	

}
