package com.rdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdms.model.StockModel;
import com.rdms.model.Village;

import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, Integer>  {

    Optional<Village> findByVillageName(String villageName);

}
