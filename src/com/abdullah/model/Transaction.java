package com.abdullah.model;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final double amount;
    private final LocalDate date;
    private final Category category;
    private final String description;

    public Transaction(double amount, LocalDate date, Category category, String description) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public Transaction(UUID id, double amount, LocalDate DATE, Category CATEGORY, String DESCRIPTION) {
        this.id = id;
        this.amount = amount;
        this.date = DATE;
        this.category = CATEGORY;
        this.description = DESCRIPTION;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return
                "id : " + id +
                        "\n amount : " + amount +
                        "\n date : " + date +
                        "\n category : " + category +
                        "\n description : " + description;
    }
}
