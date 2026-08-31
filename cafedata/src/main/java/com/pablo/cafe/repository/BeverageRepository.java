package com.pablo.cafe.repository;

import com.pablo.cafe.entity.BeverageData;
import org.springframework.data.repository.CrudRepository;

public interface BeverageRepository extends CrudRepository<BeverageData,Integer> {}
