package ch.droduit.bookstore;

import ch.droduit.bookstore.domain.Book;
import ch.droduit.bookstore.domain.BookRepository;
import ch.droduit.bookstore.domain.Category;
import ch.droduit.bookstore.domain.CategoryRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private static Book newBook;

    @Test
    @Order(1)
    public void findByIdShouldReturnBook() {
        List<Book> books = bookRepository.findByTitle("Romeo and Juliet");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Shakespear");
    }

    @Test
    @Order(2)
    public void createNewBook() {
        Optional<Category> fantasyCategory = categoryRepository.findByName("Fantasy");
        newBook = new Book("Harry Potter", "J.K Rowling", Year.of(2007), "0000000000020", new BigDecimal("35.50"), fantasyCategory.get());
        bookRepository.save(newBook);
        assertThat(newBook.getId()).isNotNull();
    }

    @Test
    @Order(3)
    public void deleteNewBook() {
        bookRepository.delete(newBook);
        List<Book> books = bookRepository.findByTitle("Harry Potter");
        assertThat(books).hasSize(0);
    }
}
