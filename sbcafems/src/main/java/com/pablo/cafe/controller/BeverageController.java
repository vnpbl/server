package com.pablo.cafe.controller;


import com.pablo.cafe.model.Beverage;
import com.pablo.cafe.service.BeverageService;
import com.pablo.cafe.controller.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class BeverageController {
    Logger logger = LoggerFactory.getLogger(BeverageController.class);

    @Autowired
    private BeverageService beverageService;

    private final StorageService storageService;

    @Autowired
    public BeverageController(StorageService storageService ){
        this.storageService = storageService;
    }


    @GetMapping("/api/employee")
    public ResponseEntity<?> listEmployees()
    {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;

        try {
            Beverage[] beverages = beverageService.getEmployees();
            response =  ResponseEntity.ok().headers(headers).body(beverages);
        }
        catch( Exception ex)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PostMapping("api/employee")
    public ResponseEntity<?> add(@RequestBody Beverage beverage){
        logger.info("Input >> "+  beverage.toString() );
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            Beverage newBeverage = beverageService.create(beverage);
            logger.info("created beverage >> "+  newBeverage.toString() );
            response = ResponseEntity.ok(newBeverage);
        }
        catch( Exception ex)
        {
            logger.error("Failed to retrieve beverage with id : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @PutMapping("api/employee")
    public ResponseEntity<?> update(@RequestBody Beverage beverage){
        logger.info("Update Input >> "+  beverage.toString() );
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            Beverage newBeverage = beverageService.update(beverage);
            response = ResponseEntity.ok(newBeverage);
        }
        catch( Exception ex)
        {
            logger.error("Failed to retrieve beverage with id : {}", ex.getMessage(), ex);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @GetMapping("api/employee/{id}")
    public ResponseEntity<?> get(@PathVariable final Integer id){
        logger.info("Input employee id >> "+  Integer.toString(id));
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            Beverage beverage = beverageService.getEmployee(id);
            response = ResponseEntity.ok(beverage);
        }
        catch( Exception ex)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }

    @DeleteMapping("api/employee/{id}")
    public ResponseEntity<?> delete(@PathVariable final Integer id){
        logger.info("Input >> "+  Integer.toString(id));
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<?> response;
        try {
            beverageService.delete(id);
            response = ResponseEntity.ok(null);
        }
        catch( Exception ex)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }
}
