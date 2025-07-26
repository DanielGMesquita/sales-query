package dev.danielmesquita.salesquery.repositories;

import dev.danielmesquita.salesquery.dtos.SaleMinDTO;
import dev.danielmesquita.salesquery.dtos.SaleSummaryDTO;
import dev.danielmesquita.salesquery.entities.Sale;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(
      value =
          "SELECT new dev.danielmesquita.salesquery.dtos.SaleMinDTO(s.id, s.amount, s.date, sel.name) "
              + "FROM Sale s JOIN s.seller sel "
              + "WHERE s.date BETWEEN :minDate AND :maxDate AND LOWER(sel.name) LIKE LOWER(CONCAT('%', :name, '%'))",
      countQuery =
          "SELECT COUNT(s) FROM Sale s JOIN s.seller sel "
              + "WHERE s.date BETWEEN :minDate AND :maxDate AND LOWER(sel.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  Page<SaleMinDTO> findByDateBetweenAndName(
      LocalDate minDate, LocalDate maxDate, Pageable pageable, String name);

  @Query(
      value =
          "SELECT new dev.danielmesquita.salesquery.dtos.SaleSummaryDTO(s.amount, sel.name) "
              + "FROM Sale s JOIN s.seller sel "
              + "WHERE s.date BETWEEN :minDate AND :maxDate ",
      countQuery =
          "SELECT COUNT(s) FROM Sale s JOIN s.seller sel WHERE s.date BETWEEN :minDate AND :maxDate")
  Page<SaleSummaryDTO> findByDateBetween(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
