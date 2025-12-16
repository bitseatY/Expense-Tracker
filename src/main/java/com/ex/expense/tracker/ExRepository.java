package com.ex.expense.tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ExRepository extends JpaRepository<Expense,Long> {
    @Query("select  new com.ex.expense.tracker.SummaryDTO(a.category.name,sum(a.amount)) from Expense a where a.date  between :start and :end group by category ")
     List<SummaryDTO>   findExpensesToSummarize(@Param("start") LocalDate start,@Param("end") LocalDate end);

}
