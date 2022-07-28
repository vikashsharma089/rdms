package com.rdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdms.model.StockDetails;


@Repository
public interface StockDetailRepository extends JpaRepository< StockDetails, Integer>  {

}
