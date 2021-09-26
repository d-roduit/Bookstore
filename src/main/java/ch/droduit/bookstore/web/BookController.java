package ch.droduit.bookstore.web;

import ch.droduit.bookstore.domain.Book;
import ch.droduit.bookstore.domain.BookRepository;
import ch.droduit.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public String deleteBook(@PathVariable("id") long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:../bookList";
    }

}
