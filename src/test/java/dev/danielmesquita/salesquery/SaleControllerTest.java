package dev.danielmesquita.salesquery;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void testSalesSummaryWithDateRange() throws Exception {
    mockMvc
        .perform(
            get("/sales/summary").param("minDate", "2022-01-01").param("maxDate", "2022-06-30"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(5))))
        .andExpect(
            jsonPath(
                "$.content[*].sellerName",
                hasItems("Anakin", "Logan", "Loki Odinson", "Padme", "Thor Odinson")));
  }

  @Test
  void testSalesSummaryLast12Months() throws Exception {
    mockMvc
        .perform(get("/sales/summary"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
        .andExpect(jsonPath("$.content[0].sellerName", notNullValue()))
        .andExpect(jsonPath("$.content[0].amount", notNullValue()));
  }

  @Test
  void testSalesReportLast12Months() throws Exception {
    mockMvc
        .perform(get("/sales/report"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
        .andExpect(jsonPath("$.content[0].id", notNullValue()))
        .andExpect(jsonPath("$.content[0].date", notNullValue()))
        .andExpect(jsonPath("$.content[0].amount", notNullValue()))
        .andExpect(jsonPath("$.content[0].sellerName", notNullValue()));
  }

  @Test
  void testSalesReportWithDateRangeAndName() throws Exception {
    mockMvc
        .perform(
            get("/sales/report")
                .param("minDate", "2022-05-01")
                .param("maxDate", "2022-05-31")
                .param("name", "odinson"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(3)))
        .andExpect(
            jsonPath("$.content[0].sellerName", anyOf(is("Loki Odinson"), is("Thor Odinson"))))
        .andExpect(
            jsonPath("$.content[1].sellerName", anyOf(is("Loki Odinson"), is("Thor Odinson"))))
        .andExpect(
            jsonPath("$.content[2].sellerName", anyOf(is("Loki Odinson"), is("Thor Odinson"))));
  }
}
