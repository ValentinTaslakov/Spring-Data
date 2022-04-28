package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findBookByAgeRestriction(AgeRestriction restriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(EditionType type, int i);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal i, BigDecimal greaterPrice);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate startDate, LocalDate endDate);
};
