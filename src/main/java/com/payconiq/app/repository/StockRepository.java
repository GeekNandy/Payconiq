package com.payconiq.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.payconiq.app.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
