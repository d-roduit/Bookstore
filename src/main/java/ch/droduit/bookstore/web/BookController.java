package ch.droduit.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ch.droduit.bookstore.domain.Book;
import ch.droduit.bookstore.domain.BookRepository;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Bean
    public CommandLineRunner insertBooksData(BookRepository bookRepository) {
        return (args) -> {
            List<Book> bookList = new ArrayList<>();
            bookList.add(new Book("Brave New World", "Aldous Huxley", Year.of(1932), "0000000000001", new BigDecimal(20)));
            bookList.add(new Book("L'Avare", "Molière", Year.of(1668), "0000000000002", new BigDecimal(12)));
            bookList.add(new Book("Romeo and Juliet", "Shakespear", Year.of(1597), "0000000000003", new BigDecimal("5.50")));
            bookRepository.saveAll(bookList);
        };
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("bookList")
    public String bookList(Model model) {
        model.addAttribute("bookList", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("save")
    public String saveBook(@ModelAttribute Book book) {
        System.out.println(book);
        bookRepository.save(book);
        return "redirect:bookList";
    }

    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "editBook";
        }
        return "redirect:../bookList";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:../bookList";
    }

}
