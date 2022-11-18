package com.stockMarketApi;

import com.stockMarketApi.Model.stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<stock,Integer> {
}
