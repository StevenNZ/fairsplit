package com.fairsplit.service;

import com.fairsplit.dto.ExpenseDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto, UUID userId);
    List<ExpenseDto> getExpenses(UUID userId);
    ExpenseDto updateExpense(ExpenseDto expenseDto, UUID userId, UUID expenseId);
    void deleteExpense(UUID userId, UUID expenseId);
}
