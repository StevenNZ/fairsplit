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

    @PutMapping("{expenseId}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable("userId") UUID userId, @PathVariable("expenseId") UUID expenseId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto updatedExpense = expenseService.updateExpense(expenseDto, userId, expenseId);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("{expenseId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId, @PathVariable("expenseId") UUID expenseId) {
        expenseService.deleteExpense(userId, expenseId);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
