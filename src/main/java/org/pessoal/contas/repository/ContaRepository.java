package org.pessoal.contas.repository;

import org.pessoal.contas.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {


    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataPagamento BETWEEN :startDate AND :endDate AND c.situacao = 'PAGA'")
    BigDecimal sumTotalPagoByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
