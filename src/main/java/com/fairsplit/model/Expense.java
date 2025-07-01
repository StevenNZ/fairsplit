package com.fairsplit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue
    private UUID id;

    private int amount;

    private String description;

    @Column(name = "local_date", columnDefinition = "DATE")
    private LocalDate localDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
