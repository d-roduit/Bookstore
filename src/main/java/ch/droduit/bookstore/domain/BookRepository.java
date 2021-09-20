package ch.droduit.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
//    Optional<Book> getBookById(Long id);
//    List<Book> getAllBooksByTitle(String title);
//    List<Book> getAllBooksByAuthor(String author);
//    List<Book> getAllBooksByYear(Year year);
//    Optional<Book> getBookByIsbn(String isbn);
//    List<Book> getAllBooks();
//    void saveBook(Book book);
//    void deleteBookById(Long id);
//    void saveAllBooks(List<Book> bookList);
}
