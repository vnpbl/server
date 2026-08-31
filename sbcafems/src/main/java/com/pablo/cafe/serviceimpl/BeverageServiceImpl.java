package com.pablo.cafe.serviceimpl;

import com.pablo.cafe.entity.BeverageData;
import com.pablo.cafe.model.Beverage;
import com.pablo.cafe.repository.BeverageRepository;
import com.pablo.cafe.service.BeverageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BeverageServiceImpl implements BeverageService {
    Logger logger = LoggerFactory.getLogger(BeverageServiceImpl.class);

    @Autowired
    BeverageRepository beverageRepository;

    @Override
    public Beverage[] getEmployees() {
        List<BeverageData> employeesData = new ArrayList<>();
        List<Beverage> beverages = new ArrayList<>();
        beverageRepository.findAll().forEach(employeesData::add);
        Iterator<BeverageData> it = employeesData.iterator();

        while(it.hasNext()) {
            Beverage beverage = new Beverage();
            BeverageData beverageData = it.next();
            beverage.setId(beverageData.getId());
            beverage.setName(beverageData.getName());
            beverage.setDescription(beverageData.getDescription());
            beverage.setAge(beverageData.getAge());
            beverages.add(beverage);
        }

        Beverage[] array = new Beverage[beverages.size()];
        for  (int i = 0; i< beverages.size(); i++){
            array[i] = beverages.get(i);
        }
//        Beverage[] array = (Beverage[])beverages.toArray();
        return array;
    }

    @Override
    public Beverage create(Beverage beverage) {
        logger.info("add: Input"+ beverage.toString());
        BeverageData beverageData = new BeverageData();
        beverageData.setName(beverage.getName());
        beverageData.setDescription(beverage.getDescription());
        beverageData.setAge(beverage.getAge());
        beverageData = beverageRepository.save(beverageData);
        logger.info("add: Input"+ beverageData.toString());

        Beverage newBeverage = new Beverage();
        newBeverage.setId(beverageData.getId());
        newBeverage.setName(beverageData.getName());
        newBeverage.setDescription(beverageData.getDescription());
        newBeverage.setAge(beverageData.getAge());
        return newBeverage;
    }

    @Override
    public Beverage update(Beverage beverage) {
        BeverageData beverageData = new BeverageData();
        beverageData.setId(beverage.getId());
        beverageData.setName(beverage.getName());
        beverageData.setDescription(beverage.getDescription());
        beverageData.setAge(beverage.getAge());
        beverageData = beverageRepository.save(beverageData);

        Beverage newBeverage = new Beverage();
        newBeverage.setId(beverageData.getId());
        newBeverage.setName(beverageData.getName());
        newBeverage.setDescription(beverageData.getDescription());
        newBeverage.setAge(beverageData.getAge());
        return newBeverage;
    }

    @Override
    public Beverage getEmployee(Integer id) {
        logger.info("Input id >> "+  Integer.toString(id) );
        Optional<BeverageData> optional = beverageRepository.findById(id);
        if(optional.isPresent()) {
            logger.info("Is present >> ");
            Beverage beverage = new Beverage();
            BeverageData employeeDatum = optional.get();
            beverage.setId(employeeDatum.getId());
            beverage.setName(employeeDatum.getName());
            beverage.setDescription(employeeDatum.getDescription());
            beverage.setAge(employeeDatum.getAge());
            return beverage;
        }
        logger.info("Failed  >> unable to locate employee" );
        return null;
    }

    @Override
    public void delete(Integer id) {
        logger.info("Input >> " + Integer.toString(id));
         Optional<BeverageData> optional = beverageRepository.findById(id);
         if( optional.isPresent()) {
             BeverageData employeeDatum = optional.get();
             beverageRepository.delete(employeeDatum);
             logger.info("Success >> " + employeeDatum.toString());
         }
         else {
             logger.info("Failed  >> unable to locate employee id: " +  Integer.toString(id));
         }
    }
}
