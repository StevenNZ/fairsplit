package com.fairsplit.controller;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users/{userId}/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@PathVariable("userId") UUID userId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto savedExpense = expenseService.createExpense(expenseDto, userId);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getExpenses(@PathVariable("userId") UUID userId) {
        List<ExpenseDto> expenses = expenseService.getExpenses(userId);
        return ResponseEntity.ok(expenses);
    }
}
