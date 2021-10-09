package ch.droduit.bookstore.web;

import ch.droduit.bookstore.domain.Book;
import ch.droduit.bookstore.domain.BookRepository;
import ch.droduit.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("logout")
    public String postLogout() {
        return "login";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("bookList")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @PostMapping("save")
    public String saveBook(@ModelAttribute Book book) {
        System.out.println(book);
        bookRepository.save(book);
        return "redirect:bookList";
    }

    @GetMapping("edit/{id}")
    public String editBook(@PathVariable("id") long bookId, Model model) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("categories", categoryRepository.findAll());
            return "editBook";
        }
        return "redirect:../bookList";
    }


    @GetMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:../bookList";
    }

    @GetMapping("api/books")
    @ResponseBody
    public List<Book> getBooks() {
        Iterable<Book> booksIterable = bookRepository.findAll();
        List<Book> bookList = new ArrayList<>();
        booksIterable.forEach(bookList::add);
        return bookList;
    }

    @GetMapping("api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable("id") long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

}
