package com.example.test.service;

import com.example.test.entity.Number;
import com.example.test.repository.NumberRepository;
import org.springframework.stereotype.Service;

@Service
public class NumberService {
    private final NumberRepository numberRepository;

    public NumberService(NumberRepository repository) {
        this.numberRepository = repository;
    }

    public void saveNumber(Number number) {
        numberRepository.save(number);
    }
}
