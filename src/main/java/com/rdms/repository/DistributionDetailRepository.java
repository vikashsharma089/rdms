package com.rdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdms.model.DistributionDetails;
import com.rdms.model.MonthModel;

@Repository
public interface DistributionDetailRepository extends JpaRepository<DistributionDetails, Integer>  {

}
