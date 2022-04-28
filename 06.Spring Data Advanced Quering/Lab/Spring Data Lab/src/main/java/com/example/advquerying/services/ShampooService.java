package com.example.advquerying.services;

import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {

    void selectShampoosBySize(Size sizeValue);
    void SelectShampoosBySizeOrLabel(Size sizeValue, long id);

    void SelectShampoosByPrice(BigDecimal price);

    void countShampoosByPrice(BigDecimal price);

    void SelectShampoosByIngredients(Set<String> berry);

    void SelectShampoosByIngredientsCount(int i);
}
