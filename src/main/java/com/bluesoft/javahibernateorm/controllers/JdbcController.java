package com.bluesoft.javahibernateorm.controllers;

import com.bluesoft.javahibernateorm.domain.Book;
import com.bluesoft.javahibernateorm.services.BookStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class JdbcController {

    private final BookStoreService bookStoreService;

    JdbcController(final BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/")
    String getIndex(){
        return "Jdbc Conntroller";
    }

    @GetMapping("/books/{isbn}")
    ResponseEntity<Book> getBookByISBN(@PathVariable String isbn){
        return ResponseEntity.ok(bookStoreService.retrieveObjectGraph(isbn));
    }

    @PostMapping("/books")
    ResponseEntity<?> createBook(@RequestBody Book book){
        //System.out.println(book);
        bookStoreService.persistObjectGraph(book);
        return ResponseEntity.status(201).build();
    }






}
