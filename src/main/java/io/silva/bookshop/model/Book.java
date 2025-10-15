package io.silva.bookshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book", schema = "public")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "author", length = 50, nullable = false)
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "loan", nullable = false)
    private Boolean loan;
}
