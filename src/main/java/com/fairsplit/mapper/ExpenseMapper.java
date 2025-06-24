package com.fairsplit.mapper;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.model.Expense;
import com.fairsplit.model.User;

public class ExpenseMapper {
    public static ExpenseDto mapToExpenseDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getLocalDate(),
                expense.getUser() != null ? expense.getUser().getId() : null
        );
    }

    public static Expense mapToExpense(ExpenseDto dto, User user) {
        return new Expense(
                dto.getId(),
                dto.getAmount(),
                dto.getDescription(),
                dto.getLocalDate(),
                user
        );
    }
}
