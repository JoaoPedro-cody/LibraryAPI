package io.silva.bookshop.repository;

import io.silva.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByNameContainingIgnoreCase(String name);
    User findByEmail(String email);
    void deleteUserByEmail(String email);
}
