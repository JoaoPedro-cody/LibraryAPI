package io.silva.bookshop.repository;

import io.silva.bookshop.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void saveTest(){
        User user = new User();
        user.setName("joao");
        user.setEmail("joao@gmail.com");

        userRepository.save(user);
    }
}
