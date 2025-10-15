package io.silva.bookshop.repository;

import io.silva.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    void deleteUserByEmail(String email);
}
