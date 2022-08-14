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
			 "p.cardNumber LIKE %?1 and p.village.ID=?2" )
	List<RationCardModel> searchRationCard(String searcyKey, Integer villageId);
	
	@Query(value = "SELECT p FROM RationCardModel p  where p.cardNumber not in(?1) and p.village.ID=?2 ")
	public List<RationCardModel> getAllRemaingRationCard(List<String> cardList, Integer villageId);
	
	

}
