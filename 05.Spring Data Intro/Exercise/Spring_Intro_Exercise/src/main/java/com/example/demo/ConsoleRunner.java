package com.example.demo;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
    }



    @Override
    public void run(String... args) throws Exception {

        // this.seedService.seedAuthors();
        // this.seedService.seedCategories();
        //this.seedService.seedAll();
        this._01_GetAllBooksAfter2000();
    }

    private void _01_GetAllBooksAfter2000(){
        LocalDate yearAfter = LocalDate.of(2000, 1, 1);
        List<Book> booksAfter2000 =
                this.bookRepository.findByReleaseDateAfter(yearAfter);

        booksAfter2000.forEach(b-> System.out.println(b.getTitle()));
    }
}
