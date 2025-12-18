package Repositories;

import Entities.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface RecurringExpensesRepo extends JpaRepository<RecurringExpense,Long> {
    List<RecurringExpense> findByNextRunDate(LocalDate nextRunDate);
}
