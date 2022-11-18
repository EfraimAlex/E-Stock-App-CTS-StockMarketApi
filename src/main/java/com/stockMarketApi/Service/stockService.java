package com.stockMarketApi.Service;



import com.stockMarketApi.Model.stock;
import com.stockMarketApi.Repository.stockRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stockService {
    @Autowired
    private stockRepository repo;

    public stockService(stockRepository stockRepository) {
    }

    public Iterable<stock>CompanyByCompanyCode(Integer companyCode){

        return this.repo.getStocksBycode(companyCode);
    }
//    public List<stock> CompanyByCompanyName(String companyName){
//        return this.repo.getStocksByName(companyName);
//    }
//public Iterable<stock>getStocksByName(String companyName){
//
//    return this.repo.getStocksByName(companyName);

    public Iterable<stock> listAll(){
        return this.repo.findAll();
    }
    public stock save(stock stocks) {

        return repo.save(stocks);
    }

    public void deleteStockByCompanyCode(Integer companyCode)
    {
       repo.deletStocksByCompanyCode(companyCode);

    }
//    public void deleteStockById(Integer id)
//    {
//        repo.deleteById(id);
//
//    }

}

