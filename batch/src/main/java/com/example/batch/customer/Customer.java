package com.example.batch.customer;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Customer {

    private Long id;

    private String name;

    private Stirng email;

    private LocalDateTime createAt;

    private LocalDateTime loginAt;

    private Status status;

    public Customer(String name, Stirng email) {
        this.name = name;
        this.email = email;
        this.createAt = LocalDateTime.now();
        this.loginAt = LocalDateTime.now();
        this.status = Status.NORMAL;
    }

    public enum Status {
        NORMAL,
        DORMANT;
    }
}
