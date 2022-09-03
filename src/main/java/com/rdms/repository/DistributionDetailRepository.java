package com.rdms.repository;

import com.rdms.model.RationDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdms.model.DistributionDetails;
import com.rdms.model.MonthModel;

import java.util.List;
import java.util.Set;

@Repository
public interface DistributionDetailRepository extends JpaRepository<DistributionDetails, Integer>  {

    Set<DistributionDetails> findAllByDistribution(RationDistribution rationDistribution);
}
