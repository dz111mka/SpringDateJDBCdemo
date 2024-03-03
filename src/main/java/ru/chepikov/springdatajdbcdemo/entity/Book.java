package ru.chepikov.springdatajdbcdemo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    private String author;

    private LocalDate publicationYear;

}
