package com.fairsplit.service.impl;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.dto.UserDto;
import com.fairsplit.exception.ResourceNotFoundException;
import com.fairsplit.mapper.ExpenseMapper;
import com.fairsplit.mapper.UserMapper;
import com.fairsplit.model.Expense;
import com.fairsplit.model.User;
import com.fairsplit.repository.ExpenseRepository;
import com.fairsplit.repository.UserRepository;
import com.fairsplit.service.ExpenseService;
import com.fairsplit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto, User user) {
        Expense expense = ExpenseMapper.mapToExpense(expenseDto, user);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }
}
