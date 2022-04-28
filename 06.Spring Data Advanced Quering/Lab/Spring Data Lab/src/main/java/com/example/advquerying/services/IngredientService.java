package com.example.advquerying.services;

import java.util.List;

public interface IngredientService {

    void SelectIngredientsByName(String letter);

    void selectIngredientsByNames(List<String> names);
}
