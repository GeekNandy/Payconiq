package com.payconiq.app.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.payconiq.app.exception.BadRequestException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Stock implements Serializable {

	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private Double currentPrice;
	private Instant lastUpdate;
	
	public Stock() {}
	
	public Stock(Long id, String n, Double p, Instant i) {
		setName(n);
		setCurrentPrice(p);
		setLastUpdate(Instant.now());
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
		setLastUpdate(Instant.now());
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(name == null || "".equals(name.trim())) throw new BadRequestException("Stock name cannot be empty");
		this.name = name;
		setLastUpdate(Instant.now());
	}
	
	public Double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(Double currentPrice) {
		if(currentPrice == null || currentPrice < 1.0) throw new BadRequestException("Invalid stock price");
		this.currentPrice = currentPrice;
		setLastUpdate(Instant.now());
	}
	
	public Instant getLastUpdate() {
		return lastUpdate;
	}
	
	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	  @Override
	  public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Stock stock = (Stock) o;

	    if (id != stock.getId()) return false;
	    if (!Objects.equals(name, stock.name)) return false;
	    if (currentPrice != stock.currentPrice) return false;
	    return true;
	  }
	  @Override
	  public int hashCode() {
	    int result;
	    result = id != null ? id.hashCode() : 0;
	    result = 31 * result + (name != null ? name.hashCode() : 0);
	    result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
	    return result;
	  }

	  public static Builder builder() {
	    return new Builder();
	  }

	  public static class Builder {
		private Long id;
		private String name;
		private Double currentPrice;
		private Instant lastUpdate;

	    public Builder setId(Long id) {
	      this.id = id;
	      return this;
	    }

	    public Builder setName(String name) {
	      this.name = name;
	      return this;
	    }

	    public Builder setCurrentPrice(Double currentPrice) {
	      this.currentPrice = currentPrice;
	      return this;
	    }

	    public Stock build() {
	      return new Stock(id, name, currentPrice, lastUpdate);
	    }
	  }

}
