package com.fairsplit.service.impl;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.exception.ResourceNotFoundException;
import com.fairsplit.mapper.ExpenseMapper;
import com.fairsplit.model.Expense;
import com.fairsplit.model.User;
import com.fairsplit.repository.ExpenseRepository;
import com.fairsplit.repository.UserRepository;
import com.fairsplit.security.util.SecurityUtils;
import com.fairsplit.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto, UUID userId) {
        securityUtils.checkOwnership(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with this id :" + userId));

        Expense expense = ExpenseMapper.mapToExpense(expenseDto, user);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public List<ExpenseDto> getExpenses(UUID userId) {
        securityUtils.checkOwnership(userId);
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User does not exist with this id :" + userId);
        }

        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(ExpenseMapper::mapToExpenseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(ExpenseDto expenseDto, UUID userId, UUID expenseId) {
        securityUtils.checkOwnership(userId);
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense does not exist with this id :" + expenseId));

        if (!expense.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Expense does not belong to this user with id :" + userId);
        }

        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expense.setLocalDate(expenseDto.getLocalDate());

        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(UUID userId, UUID expenseId) {
        securityUtils.checkOwnership(userId);
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense does not exist with this id :" + expenseId));

        if (!expense.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Expense does not belong to this user with id :" + userId);
        }

        expenseRepository.delete(expense);
    }
}
