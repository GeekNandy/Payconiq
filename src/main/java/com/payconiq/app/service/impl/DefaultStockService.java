package com.payconiq.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.app.exception.BadRequestException;
import com.payconiq.app.model.Stock;
import com.payconiq.app.repository.StockRepository;
import com.payconiq.app.service.StockService;

@Service
public class DefaultStockService implements StockService {
	
	private final StockRepository stockRepository;
	
	@Autowired
	DefaultStockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	@Override
	public List<Stock> getStockById(Long id) {
		return stockRepository.findAll().stream()
		        .filter((stock) -> id.equals(stock.getId()))
		        .collect(Collectors.toList());
	}

	@Override
	public Stock createNewStock(Stock stock) {
		if (stock.getId() != null) {
			throw new BadRequestException("The ID must not be provided when creating a new Stock");
	    }
	    return stockRepository.save(stock);
	}

	@Override
	public Stock updateStock(Long id, Double price) {
		Stock stock = getStockById(id).get(0);
		stock.setCurrentPrice(price);
	    return stockRepository.save(stock);
	}

}
