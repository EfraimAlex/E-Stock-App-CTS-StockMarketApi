package com.stockMarketApi.Controller;


import com.stockMarketApi.Model.stock;
import com.stockMarketApi.Repository.stockRepository;
import com.stockMarketApi.Service.stockService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1.0/market")
public class stockController {
//    @Autowired
//    stockRepository stockRepository1;
    @Autowired
    private stockService service;

    public stockController(stockService service) {
    }


    @GetMapping("/stock/{companyCode}")
    public Iterable<stock> getStockByCompanyCode(@PathVariable Integer companyCode )
    {
        return service.CompanyByCompanyCode(companyCode);
    }


    @GetMapping("/stock/get")
    public  Iterable<stock> fetchStockList()
    {   return service.listAll();
    }

    @PostMapping("/stock/add")
    public stock saveStocks(@RequestBody stock stocks)
    {          return service.save(stocks);
//        return "The data is added";
    }
//    @DeleteMapping("/delete/{companyCode}")
    @KafkaListener(topics = "${kafka.topic.name}", groupId  = "${kafka.consumer.group.id}")
    public String deleteStockByCompanyCode(@PathVariable("companyCode") Integer companyCode)
    {
        service.deleteStockByCompanyCode(companyCode);
        return "Deleted stocks Successfully";
    }


}
