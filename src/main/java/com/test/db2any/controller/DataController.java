package com.test.db2any.controller;

import com.test.db2any.model.Data;
import com.test.db2any.model.DataJsonResponse;
import com.test.db2any.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);
    DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping(value = "db2any/bykey/getjson")
    public String getByKey(@RequestParam("key") Long key) {

        Data dataForResponse = dataService.getDataById(key);

        logger.info("Method is done");

        return DataJsonResponse.builder().
                data(dataForResponse).
                status(HttpStatus.OK).
                requestParam(dataForResponse.getId().toString()).
                build().
                toString();
    }
}
