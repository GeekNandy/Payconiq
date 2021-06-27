package com.payconiq.app.model;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class PriceUpdateRequest implements Serializable {
	
	private static final long serialVersionUID = 3L;
	
	@Autowired
	private Double currentPrice;
	
	public PriceUpdateRequest() {}
	
	public PriceUpdateRequest(Double d) {
		setCurrentPrice(d);
	}
	
	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

}
