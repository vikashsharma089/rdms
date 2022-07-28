package com.rdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdms.model.PurwaModel;


@Repository
public interface PurwaRepository extends JpaRepository<PurwaModel, Integer>  {

}
