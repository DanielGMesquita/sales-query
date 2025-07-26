package dev.danielmesquita.salesquery.repositories;

import dev.danielmesquita.salesquery.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {}
