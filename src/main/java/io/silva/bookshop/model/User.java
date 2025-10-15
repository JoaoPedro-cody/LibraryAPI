package io.silva.bookshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "bs_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "user_name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 50, nullable = false)
    private String email;
}
