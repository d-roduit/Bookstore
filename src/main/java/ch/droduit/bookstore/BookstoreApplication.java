package ch.droduit.bookstore;

import ch.droduit.bookstore.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BookstoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        return (args) -> {
            List<Category> categories = new ArrayList<>();
            Category actionCategory = new Category("Action & Adventure");
            Category classicsCategory = new Category("Classics");
            Category comicsCategory = new Category("Comics");
            Category fantasyCategory = new Category("Fantasy");
            Category horrorCategory = new Category("Horror");
            Category romanceCategory = new Category("Romance");
            Category scienceFictionCategory = new Category("Science Fiction");
            categories.add(actionCategory);
            categories.add(classicsCategory);
            categories.add(comicsCategory);
            categories.add(fantasyCategory);
            categories.add(horrorCategory);
            categories.add(romanceCategory);
            categories.add(scienceFictionCategory);
            categoryRepository.saveAll(categories);

            List<Book> books = new ArrayList<>();
            books.add(new Book("Brave New World", "Aldous Huxley", Year.of(1932), "0000000000001", new BigDecimal(20), fantasyCategory));
            books.add(new Book("L'Avare", "Molière", Year.of(1668), "0000000000002", new BigDecimal(12), classicsCategory));
            books.add(new Book("Romeo and Juliet", "Shakespear", Year.of(1597), "0000000000003", new BigDecimal("5.50"), romanceCategory));
            bookRepository.saveAll(books);

            List<User> users = new ArrayList<>();
            users.add(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@bookstore.com", "USER"));
            users.add(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@bookstore.com", "ADMIN"));
            userRepository.saveAll(users);
        };
    }

}
