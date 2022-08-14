package com.rdms.repository;

import com.rdms.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdms.model.PurwaModel;

import java.util.List;


@Repository
public interface PurwaRepository extends JpaRepository<PurwaModel, Integer>  {

    List<PurwaModel> findAllByVillage(Village village);

}
