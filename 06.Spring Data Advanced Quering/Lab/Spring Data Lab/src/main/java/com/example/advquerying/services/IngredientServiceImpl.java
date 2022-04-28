package com.example.advquerying.services;

import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void SelectIngredientsByName(String letter) {
        this.ingredientRepository.findByNameStartingWith(letter)
                .forEach(System.out::println);
    }

    @Override
    public void selectIngredientsByNames(List<String> names) {
        this.ingredientRepository.findByNameInOrderByPriceAsc(names)
                .forEach(System.out::println);
    }
}
