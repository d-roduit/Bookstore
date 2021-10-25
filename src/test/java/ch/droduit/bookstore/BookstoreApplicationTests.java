package ch.droduit.bookstore;

import ch.droduit.bookstore.domain.BookRepository;
import ch.droduit.bookstore.domain.CategoryRepository;
import ch.droduit.bookstore.domain.UserRepository;
import ch.droduit.bookstore.web.BookController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookController bookController;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(bookRepository).isNotNull();
		assertThat(categoryRepository).isNotNull();
		assertThat(userRepository).isNotNull();
	}
}