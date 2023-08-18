package com.gattuso42.BookStoreAPI.controller;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("bookstoreapi/author")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

//    Get all the authors
    @GetMapping("/all")
    ResponseEntity<List<AuthorEntity>>getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthor(),HttpStatus.OK);
    }

//    Get author by id
    @GetMapping("/{id}")
    ResponseEntity<AuthorEntity>getAuthorById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id),HttpStatus.OK);
    }

//    Save author
    @PostMapping()
    ResponseEntity<AuthorEntity>saveAuthor(@Valid @RequestBody AuthorEntity authorEntity){
        authorService.saveAuthor(authorEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Upgrade author
    @PutMapping("/{id}")
    ResponseEntity<AuthorEntity>updateAuthor(@Valid @RequestBody AuthorEntity authorEntity,@PathVariable Long id){
        authorService.updateAuthor(authorEntity,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Delete author by id
    @DeleteMapping("/{id}")
    ResponseEntity<AuthorEntity>deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Search Implementations********************************

//    Search author by name
    @GetMapping("/search/name")
    ResponseEntity<List<AuthorEntity>>getAuthorByName(@Valid @RequestParam String name){
        return new ResponseEntity<>(authorService.getAuthorByName(name),HttpStatus.OK);
    }

//    Get all the books by author
    @GetMapping("/search-books-by-author/{id}")
    ResponseEntity<List<BookEntity>>getBooksByAuthor( @PathVariable Long id){
        return new ResponseEntity<>(authorService.getBooksByAuthor(id),HttpStatus.OK);
    }

}
