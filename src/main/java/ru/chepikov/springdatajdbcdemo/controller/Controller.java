package ru.chepikov.springdatajdbcdemo.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chepikov.springdatajdbcdemo.entity.Book;
import ru.chepikov.springdatajdbcdemo.repository.BookRepository;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/books")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Controller {

    BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody Book book) {
        bookRepository.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> list = bookRepository.getAllBooks();
        if (list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{longId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long longId) {
        bookRepository.deleteBook(longId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{longId}")
    public ResponseEntity<Void> updateById(@PathVariable Long longId, @RequestBody Book book) {
        bookRepository.updateBook(longId, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@GetMapping("/{longId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long longId) {
        Book book = bookRepository.getBookById(longId);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
