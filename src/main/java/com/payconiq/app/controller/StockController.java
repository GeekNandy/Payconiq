package com.payconiq.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.payconiq.app.exception.BadRequestException;
import com.payconiq.app.exception.ElementNotFoundException;
import com.payconiq.app.model.PriceUpdateRequest;
import com.payconiq.app.model.Stock;
import com.payconiq.app.service.StockService;
import com.payconiq.app.utils.RequestElementsResolverUtils;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	
	  private final StockService stockService;
	  private static final RequestElementsResolverUtils resolvers = null;

	  @Autowired
	  public StockController(StockService stockService) {
	    this.stockService = stockService;
	  }

	  @GetMapping
	  @ResponseStatus(HttpStatus.OK)
	  public List<Stock> getAllStocks() {
	    return stockService.getAllStocks();
	  }
	  
	  @GetMapping(value = "/{stockId}")
	  @ResponseStatus(HttpStatus.OK)
	  public List<Stock> getStockByID(@PathVariable Long stockId) {
	    List<Stock> result = stockService.getStockById(stockId);
	    if(result.size() < 1) throw new ElementNotFoundException("No stock found for given ID = "+stockId);
	    return result;
	  }
	  

	  @PostMapping
	  @ResponseStatus(HttpStatus.CREATED)
	  public Stock createStock(@RequestBody Stock stock) {
		  if (stock.getId() != null) {
		      throw new BadRequestException("The ID must not be provided when creating a new Stock");
		  }
		  return stockService.createNewStock(stock);
	  }
	  
	  @SuppressWarnings("static-access")
	  @PutMapping(value = "/{id}")
	  @ResponseStatus(HttpStatus.CREATED)
	  public Stock updateStock(@PathVariable Long id, @RequestBody String json) {
		  PriceUpdateRequest req = null;
		  Double price = 0.0;
		  try {
		   	  req = (PriceUpdateRequest) resolvers.parseJSON(PriceUpdateRequest.class, json);
			  price = req.getCurrentPrice();
		  } 
		  catch (JsonProcessingException e) {
			  e.printStackTrace();
	   	  }
	      return stockService.updateStock(id, price);
	  }

}
