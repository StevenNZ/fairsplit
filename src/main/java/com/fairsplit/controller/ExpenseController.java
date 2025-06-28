package com.fairsplit.controller;

import com.fairsplit.dto.ExpenseDto;
import com.fairsplit.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users/{userID}/expenses")
public class ExpenseController {
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@PathVariable("userID") UUID userID, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto savedExpense = expenseService.createExpense(expenseDto, userID);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }
}
