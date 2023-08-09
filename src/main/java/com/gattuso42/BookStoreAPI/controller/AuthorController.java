package com.gattuso42.BookStoreAPI.controller;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("bookstoreapi/author")
public class AuthorController {

//    Get all the authors
    @GetMapping("/all")
    ResponseEntity<AuthorEntity>getAllAuthor(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Get author by id
    @GetMapping("/{id}")
    ResponseEntity<AuthorEntity>getAuthorById(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Save author
    @PostMapping()
    ResponseEntity<AuthorEntity>saveAuthor(@RequestBody AuthorEntity authorEntity){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Upgrade author
    @PutMapping("/{id}")
    ResponseEntity<AuthorEntity>updateAuthor(@RequestBody AuthorEntity authorEntity,@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Delete author by id
    @DeleteMapping("/{id}")
    ResponseEntity<AuthorEntity>deleteAuthorById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Search Implementations********************************

//    Search author by name
    @GetMapping("/search/name")
    ResponseEntity<Set<AuthorEntity>>getAuthorByName(@RequestParam String name){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Get all the books by author
    @GetMapping("/search-books-by-author/{id}")
    ResponseEntity<Set<BookEntity>>getBooksByAuthor(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
