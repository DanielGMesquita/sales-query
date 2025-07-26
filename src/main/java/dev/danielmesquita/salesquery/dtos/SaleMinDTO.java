package dev.danielmesquita.salesquery.dtos;

import dev.danielmesquita.salesquery.entities.Sale;
import java.time.LocalDate;

public class SaleMinDTO {

  private final Long id;
  private final Double amount;
  private final LocalDate date;
  private final String sellerName;

  public SaleMinDTO(Long id, Double amount, LocalDate date, String sellerName) {
    this.id = id;
    this.amount = amount;
    this.date = date;
    this.sellerName = sellerName;
  }

  public SaleMinDTO(Sale entity) {
    id = entity.getId();
    amount = entity.getAmount();
    date = entity.getDate();
    sellerName = entity.getSeller().getName();
  }

  public Long getId() {
    return id;
  }

  public Double getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getSellerName() {
    return sellerName;
  }
}
