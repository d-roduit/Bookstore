package ch.droduit.bookstore;

import ch.droduit.bookstore.domain.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository categoryRepository;

    private static Category newCategory;

    @Test
    @Order(1)
    public void findByNameShouldReturnCategory() {
        Optional<Category> fantasyCategory = categoryRepository.findByName("Fantasy");
        assertThat(fantasyCategory.get()).isNotNull();
        fantasyCategory.ifPresent(category -> assertThat(category.getName()).isEqualTo("Fantasy"));
    }

    @Test
    @Order(2)
    public void createNewTestCategory() {
        newCategory = new Category("testCategory");
        categoryRepository.save(newCategory);
        assertThat(newCategory.getId()).isNotNull();
    }

    @Test
    @Order(3)
    public void deleteNewTestCategory() {
        categoryRepository.delete(newCategory);
        Optional<Category> newCategory = categoryRepository.findByName("testCategory");
        assertThat(newCategory.isPresent()).isFalse();
    }
}
