package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySizeOrderById(Size sizeValue);

    List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size sizeValue, long id);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s " +
            " JOIN s.ingredients AS i" +
            " WHERE i.name IN :ingredientsName")
    List<Shampoo> findByIngredients(Set<String> ingredientsName);

    @Query("SELECT s FROM Shampoo s " +
            " WHERE ingredients.size < :i")
    List<Shampoo> findByIngredientCountLessThan(int i);
}
