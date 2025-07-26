package dev.danielmesquita.salesquery.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import dev.danielmesquita.salesquery.dtos.SaleMinDTO;
import dev.danielmesquita.salesquery.dtos.SaleSummaryDTO;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

@DataJpaTest
public class SaleRepositoryTest {

  @Autowired private SaleRepository saleRepository;

  @Test
  void testFindSalesSummaryByDateRange() {
    LocalDate minDate = LocalDate.of(2022, 1, 1);
    LocalDate maxDate = LocalDate.of(2022, 6, 30);
    Page<SaleSummaryDTO> page =
        saleRepository.findByDateBetween(
            minDate, maxDate, org.springframework.data.domain.PageRequest.of(0, 100));
    List<SaleSummaryDTO> summary = page.getContent();
    assertThat(summary).isNotEmpty();
    assertThat(summary)
        .extracting("sellerName")
        .contains("Anakin", "Logan", "Loki Odinson", "Padme", "Thor Odinson");
  }

  @Test
  void testFindSalesReportByDateRangeAndName() {
    LocalDate minDate = LocalDate.of(2022, 5, 1);
    LocalDate maxDate = LocalDate.of(2022, 5, 31);
    String name = "odinson";
    Page<SaleMinDTO> page =
        saleRepository.findByDateBetweenAndName(
            minDate, maxDate, org.springframework.data.domain.PageRequest.of(0, 100), name);
    List<SaleMinDTO> report = page.getContent();
    assertThat(report).isNotEmpty();
    assertThat(report)
        .extracting(SaleMinDTO::getSellerName)
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
