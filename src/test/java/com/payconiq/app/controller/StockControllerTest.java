package com.payconiq.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.app.SlowTest;
import com.payconiq.app.model.PriceUpdateRequest;
import com.payconiq.app.model.Stock;
import com.payconiq.app.repository.StockRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@SlowTest
class StockControllerTest {
	
  @Autowired 
  private MockMvc mockMvc;
  @Autowired 
  private ObjectMapper mapper;

  @Autowired 
  private StockRepository repository;
  

  @Test
  @DisplayName("When all stocks are requested then they are all returned")
  void allStocksRequested() throws Exception {
    mockMvc
        .perform(get("/api/stocks"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$", hasSize((int) repository.count())));
  }

  @Test
  @DisplayName("When specific stock by given id is requested then only one is returned")
  void stockRequestedById() throws Exception {
    mockMvc
        .perform(get("/api/stocks/1"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  @DisplayName("When a stock creation is requested then it is persisted")
  void stockCreatedCorrectly() throws Exception {
    String name = "MSFT";
    Double price = 250.0;
    Stock newStock = Stock.builder().setName(name).setCurrentPrice(price).build();
    
    Long StockId =
        mapper
            .readValue(
                mockMvc
                    .perform(
                        post("/api/stocks/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(newStock)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse()
                    .getContentAsString(),
                Stock.class)
            .getId();

    newStock.setId(StockId); // Populate the ID of the stock after successful creation

    assertNotNull(
        repository
            .findById(StockId)
            .orElseThrow(
                () -> new IllegalStateException("New Stock has not been saved in the repository"))
    );
  }

  @Test
  @DisplayName("When specific stock's price by given id is updated")
  void stockUpdatedById() throws Exception {
	  Double price = 20.0;
	  mapper
      .readValue(
          mockMvc
              .perform(
                  put("/api/stocks/1")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(mapper.writeValueAsString(new PriceUpdateRequest(price))))
              .andExpect(status().isCreated())
              .andReturn()
              .getResponse()
              .getContentAsString(),
          Stock.class)
      .getId();
	  
	  assertEquals(repository.findById(1L).get().getCurrentPrice(), price);
  }
  
}
