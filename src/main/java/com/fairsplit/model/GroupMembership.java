package com.fairsplit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_membership")
public class GroupMembership {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String password;
}
