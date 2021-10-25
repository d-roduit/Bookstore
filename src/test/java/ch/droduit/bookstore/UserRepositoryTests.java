package ch.droduit.bookstore;

import ch.droduit.bookstore.domain.User;
import ch.droduit.bookstore.domain.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    private static User newTestUser;

    @Test
    @Order(1)
    public void findByUsernameShouldReturnUser() {
        User adminUser = userRepository.findByUsername("admin");
        assertThat(adminUser).isNotNull();
    }

    @Test
    @Order(2)
    public void createNewTestUser() {
        newTestUser = new User("testUser", "$2a$12$DQ9Cmy00E7DdCIjEEZtdWuN2qLJT6tkKuWsykqS0/8LuTTAP6d5Pm", "testUser@bookstore.com", "USER");
        userRepository.save(newTestUser);
        assertThat(newTestUser.getId()).isNotNull();
    }

    @Test
    @Order(3)
    public void deleteNewTestUser() {
        userRepository.delete(newTestUser);
        User user = userRepository.findByUsername("testUser");
        assertThat(user).isNull();
    }
}
