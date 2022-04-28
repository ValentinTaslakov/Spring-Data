package com.example.advquerying.services;

import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    @Autowired
    private ShampooRepository shampooRepository;

    @Override
    public void selectShampoosBySize(Size sizeValue) {
        this.shampooRepository.findBySizeOrderById(sizeValue)
        .forEach(s-> System.out.printf("%s %.2flv.%n",s.getBrand(),s.getPrice()));
    }

    @Override
    public void SelectShampoosBySizeOrLabel(Size size, long id) {
        this.shampooRepository.findBySizeOrLabelIdOrderByPrice(size,id).
                forEach(s-> System.out.printf("%s %s %.2flv.%n",s.getBrand(), s.getSize(),s.getPrice()));
    }

    @Override
    public void SelectShampoosByPrice(BigDecimal price) {
        this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc(price)
                .forEach(System.out::println);
    }

    @Override
    public void countShampoosByPrice(BigDecimal price) {
        System.out.println(this.shampooRepository.countByPriceLessThan(price));
    }

    @Override
    public void SelectShampoosByIngredients(Set<String> ingredients) {
        this.shampooRepository.findByIngredients(ingredients)
                .forEach(System.out::println);
    }

    @Override
    public void SelectShampoosByIngredientsCount(int i) {
        this.shampooRepository.findByIngredientCountLessThan(i)
                .forEach(System.out::println);
    }
}
