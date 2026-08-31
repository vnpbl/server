package com.pablo.cafe.service;

import com.pablo.cafe.model.Beverage;

public interface BeverageService {
    Beverage[] getEmployees() throws Exception;

    Beverage getEmployee(Integer id) throws Exception;

    Beverage create(Beverage product) throws Exception;

    Beverage update(Beverage product) throws Exception;

    void delete(Integer id) throws Exception;
}
