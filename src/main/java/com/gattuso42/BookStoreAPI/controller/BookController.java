package com.gattuso42.BookStoreAPI.controller;


import com.gattuso42.BookStoreAPI.entity.BookEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("bookstoreapi/book")
public class BookController {

//  Get all the books
    @GetMapping("/all")
    ResponseEntity<BookEntity>getAllBooks(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Get books by id
    @GetMapping("/{id}")
    ResponseEntity<BookEntity>getBookById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Save a Book
    @PostMapping()
    ResponseEntity<BookEntity>saveBook(@RequestBody BookEntity bookEntity){
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
    ResponseEntity<Set<BookEntity>>getBookByTitle(@RequestParam String title){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
