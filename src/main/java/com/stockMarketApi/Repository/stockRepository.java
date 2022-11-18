package com.stockMarketApi.Repository;


import com.stockMarketApi.Model.stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface stockRepository extends JpaRepository<stock,Integer> {
        @Query("SELECT ud from stockDetails ud where ud.companyCode=?1")
        public List<stock> getStocksBycode(Integer companyCode);


//        @Query("SELECT ud from stockDetails ud where ud.companyName=?1")
//        public List<stock> getStocksByName(String companyName);

//        @Query("DELETE ud from stockDetails ud where ud.companyCode=?1")
//        public void deletStocksByCompanyCode(Integer companyCode);
        @Modifying
        @Transactional
        @Query(value = "DELETE FROM stock_details WHERE company_code = :companyCode",nativeQuery = true)
        public void deletStocksByCompanyCode(Integer companyCode);



}
