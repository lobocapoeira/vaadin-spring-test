package com.example.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Number {

    @Id
    @GeneratedValue
    private Long id;

    private Long number;

    public Number() {
        this.number = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return this.number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Number numbers)) return false;

        return this.getId().equals(numbers.getId());
    }

    @Override
    public int hashCode() {
        return getNumber().hashCode();
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
