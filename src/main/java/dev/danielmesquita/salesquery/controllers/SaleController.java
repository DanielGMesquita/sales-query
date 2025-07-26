package dev.danielmesquita.salesquery.controllers;

import dev.danielmesquita.salesquery.dtos.SaleMinDTO;
import dev.danielmesquita.salesquery.dtos.SaleSummaryDTO;
import dev.danielmesquita.salesquery.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

  @Autowired private SaleService service;

  @GetMapping(value = "/report")
  public ResponseEntity<Page<SaleMinDTO>> getReport(
      @RequestParam(name = "minDate", defaultValue = "") String minDate,
      @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
      @RequestParam(name = "name", defaultValue = "") String name,
      Pageable pageable) {
    Page<SaleMinDTO> page = service.getReport(minDate, maxDate, name, pageable);
    return ResponseEntity.ok(page);
  }

  @GetMapping(value = "/summary")
  public ResponseEntity<Page<SaleSummaryDTO>> getSummary(
      @RequestParam(name = "minDate", defaultValue = "") String minDate,
      @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
      Pageable pageable) {
    Page<SaleSummaryDTO> page = service.getSummary(minDate, maxDate, pageable);
    return ResponseEntity.ok(page);
  }
}
