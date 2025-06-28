package com.fairsplit.service;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.model.User;

import java.util.UUID;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto, UUID userID);
}
