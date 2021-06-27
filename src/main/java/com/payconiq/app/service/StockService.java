package com.payconiq.app.service;

import java.util.List;

import com.payconiq.app.model.Stock;

public interface StockService {
	
	List<Stock> getAllStocks();
	  
	List<Stock> getStockById(Long id);

	Stock createNewStock(Stock stock);
	  
	Stock updateStock(Long id, Double price);

}
