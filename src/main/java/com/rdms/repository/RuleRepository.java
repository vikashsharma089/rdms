package com.rdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rdms.model.Rules;


@Repository
public interface RuleRepository extends JpaRepository<Rules, Integer>  {
	
	@Query(value = "SELECT p FROM Rules p  where p.village.ID=?1")
	List<Rules> findByVillageId(Integer villageId);
	
	

}
