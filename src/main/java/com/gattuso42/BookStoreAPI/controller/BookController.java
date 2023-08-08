package com.gattuso42.BookStoreAPI.controller;


import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("bookstoreapi/book")
@AllArgsConstructor
public class BookController {

    BookService bookService;

//  Get all the books
    @GetMapping("/all")
    ResponseEntity<Set<BookEntity>>getAllBooks(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Get books by id
    @GetMapping("/{id}")
    ResponseEntity<BookEntity>getBookById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Save a Book
    @PostMapping()
    ResponseEntity<BookEntity>saveBook(@RequestBody BookEntity bookEntity,String authorName,String authorCountry,Set<String> genreName){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Upgrade a Book
    @PutMapping("/{id}")
    ResponseEntity<BookEntity>upgradeBook(@RequestBody BookEntity bookEntity,@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Delete a Book
    @DeleteMapping("/{id}")
    ResponseEntity<BookEntity>deleteBookById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    Search Implementations***************************

//    Search books by title
    @GetMapping("search/title")
    ResponseEntity<List<BookEntity>>getBookByTitle(@RequestParam String title){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
