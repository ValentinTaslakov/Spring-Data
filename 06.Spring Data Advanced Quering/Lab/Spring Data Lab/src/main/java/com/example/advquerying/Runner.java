package com.example.advquerying;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {


    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Runner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scan = new Scanner(System.in);

//        Size size = Size.valueOf(scan.nextLine());
//        long id = Long.parseLong(scan.nextLine());
//        double price = Double.parseDouble(scan.nextLine());
//        String letter = scan.nextLine();

//        this.shampooService.selectShampoosBySize(size);

//        this.shampooService.SelectShampoosBySizeOrLabel(size,id);

//        this.shampooService.SelectShampoosByPrice(price);

//        this.ingredientService.SelectIngredientsByName(letter);

//        this.ingredientService.selectIngredientsByNames(List.of("Lavender", "Herbs", "Apple"));

//        this.shampooService.countShampoosByPrice(BigDecimal.valueOf(8.5));

//        this.shampooService.SelectShampoosByIngredients(Set.of("Berry", "Mineral-Collagen"));

        this.shampooService.SelectShampoosByIngredientsCount(2);
    }
}
