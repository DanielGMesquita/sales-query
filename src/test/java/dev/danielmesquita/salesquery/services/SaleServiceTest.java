package dev.danielmesquita.salesquery.services;

import static org.assertj.core.api.Assertions.assertThat;

import dev.danielmesquita.salesquery.dtos.SaleMinDTO;
import dev.danielmesquita.salesquery.dtos.SaleSummaryDTO;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class SaleServiceTest {

  @Autowired private SaleService saleService;

  @Test
  void testGetSummaryWithDateRange() {
    Page<SaleSummaryDTO> summary =
        saleService.getSummary("2022-01-01", "2022-06-30", PageRequest.of(0, 100));
    assertThat(summary.getContent()).isNotEmpty();
    assertThat(summary.getContent())
        .extracting("sellerName")
        .contains("Anakin", "Logan", "Loki Odinson", "Padme", "Thor Odinson");
  }

  @Test
  void testGetSummaryLast12Months() {
    Page<SaleSummaryDTO> summary = saleService.getSummary("", "", PageRequest.of(0, 100));
    assertThat(summary.getContent()).isNotEmpty();
    assertThat(summary.getContent().get(0).getSellerName()).isNotNull();
    assertThat(summary.getContent().get(0).getAmount()).isNotNull();
  }

  @Test
  void testGetReportLast12Months() {
    Page<SaleMinDTO> report = saleService.getReport("", "", "", PageRequest.of(0, 100));
    assertThat(report.getContent()).isNotEmpty();
    assertThat(report.getContent().get(0).getId()).isNotNull();
    assertThat(report.getContent().get(0).getDate()).isNotNull();
    assertThat(report.getContent().get(0).getAmount()).isNotNull();
    assertThat(report.getContent().get(0).getSellerName()).isNotNull();
  }

  @Test
  void testGetReportWithDateRangeAndName() {
    Page<SaleMinDTO> report =
        saleService.getReport("2022-05-01", "2022-05-31", "odinson", PageRequest.of(0, 100));

    LocalDate minDate = LocalDate.of(2022, 5, 1);
    LocalDate maxDate = LocalDate.of(2022, 5, 31);
    assertThat(report.getContent()).isNotEmpty();
    assertThat(report.getContent())
        .extracting("sellerName")
        .contains("Loki Odinson", "Thor Odinson")
        .doesNotContain("Anakin", "Logan", "Padme");
    assertThat(report)
        .extracting(SaleMinDTO::getDate)
        .allSatisfy(
            date -> {
              assertThat(date).isAfterOrEqualTo(minDate);
              assertThat(date).isBeforeOrEqualTo(maxDate);
            });
  }
}
