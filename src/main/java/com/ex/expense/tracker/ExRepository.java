package com.ex.expense.tracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExRepository extends JpaRepository<Expense,Long> {
}
