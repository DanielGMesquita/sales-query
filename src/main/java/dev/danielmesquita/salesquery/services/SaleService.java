package dev.danielmesquita.salesquery.services;

import dev.danielmesquita.salesquery.dtos.SaleMinDTO;
import dev.danielmesquita.salesquery.dtos.SaleSummaryDTO;
import dev.danielmesquita.salesquery.repositories.SaleRepository;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

  @Autowired private SaleRepository repository;

  @Transactional(readOnly = true)
  public Page<SaleMinDTO> getReport(
      String minDate, String maxDate, String name, Pageable pageable) {
    Map<String, LocalDate> dateRange = parseDateRange(minDate, maxDate);
    return repository.findByDateBetweenAndName(
        dateRange.get("min"), dateRange.get("max"), pageable, name);
  }

  @Transactional(readOnly = true)
  public Page<SaleSummaryDTO> getSummary(String minDate, String maxDate, Pageable pageable) {
    Map<String, LocalDate> dateRange = parseDateRange(minDate, maxDate);
    return repository.findByDateBetween(dateRange.get("min"), dateRange.get("max"), pageable);
  }

  private Map<String, LocalDate> parseDateRange(String minDate, String maxDate) {
    LocalDate minDateParsed = minDate.isEmpty() ? null : LocalDate.parse(minDate);
    LocalDate maxDateParsed = maxDate.isEmpty() ? null : LocalDate.parse(maxDate);

    if (maxDateParsed == null || maxDateParsed.isAfter(LocalDate.now())) {
      maxDateParsed = LocalDate.now();
    }

    if (minDateParsed == null || minDateParsed.isAfter(maxDateParsed)) {
      minDateParsed = maxDateParsed.minusYears(1L);
    }

    return Map.of("min", minDateParsed, "max", maxDateParsed);
  }
}
