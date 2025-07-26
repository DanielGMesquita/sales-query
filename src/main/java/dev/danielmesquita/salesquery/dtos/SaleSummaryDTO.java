package dev.danielmesquita.salesquery.dtos;

public class SaleSummaryDTO {
  private final String sellerName;
  private final Double amount;

  public SaleSummaryDTO(Double amount, String sellerName) {
    this.sellerName = sellerName;
    this.amount = amount;
  }

  public String getSellerName() {
    return sellerName;
  }

  public Double getAmount() {
    return amount;
  }
}
