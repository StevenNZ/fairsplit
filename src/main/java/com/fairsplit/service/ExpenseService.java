package com.fairsplit.service;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.model.User;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto, User user);
}
