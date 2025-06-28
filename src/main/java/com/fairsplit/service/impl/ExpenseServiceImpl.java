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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto, UUID userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with this id :" + userID));

        Expense expense = ExpenseMapper.mapToExpense(expenseDto, user);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public List<ExpenseDto> getExpenses(UUID userID) {
        if (!userRepository.existsById(userID)) {
            throw new ResourceNotFoundException("User does not exist with this id :" + userID);
        }

        List<Expense> expenses = expenseRepository.findByUserId(userID);
        return expenses.stream()
                .map(ExpenseMapper::mapToExpenseDto)
                .collect(Collectors.toList());
    }
}
