package com.rdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rdms.model.YearModel;

@Repository
public interface YearRepository extends JpaRepository<YearModel, Integer>  {

}
