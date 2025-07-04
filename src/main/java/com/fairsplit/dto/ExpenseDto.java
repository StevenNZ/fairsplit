package com.fairsplit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private UUID id;
    private int amount;
    private String description;
    private LocalDate localDate;
    private UUID userId;
}
