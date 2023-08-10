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

    private BookService bookService;

//  Get all the books
    @GetMapping("/all")
    ResponseEntity<List<BookEntity>>getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

//    Get books by id
    @GetMapping("/{id}")
    ResponseEntity<BookEntity>getBookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

//    Save a Book
    @PostMapping()
    ResponseEntity<BookEntity>saveBook(@RequestBody BookEntity bookEntity,@RequestParam String authorName,@RequestParam String authorCountry,@RequestParam Set<String> genreName){
        bookService.saveBook(bookEntity,authorName,authorCountry,genreName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Upgrade a Book
    @PutMapping("/{id}")
    ResponseEntity<BookEntity>upgradeBook(@RequestBody BookEntity bookEntity,@PathVariable Long id,@RequestParam String authorName,@RequestParam String authorCountry,@RequestParam Set<String> genreName){
        bookService.updateBook(bookEntity,authorName,authorCountry,genreName,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Delete a Book
    @DeleteMapping("/{id}")
    ResponseEntity<BookEntity>deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    Search Implementations***************************

//    Search books by title
    @GetMapping("search/title")
    ResponseEntity<List<BookEntity>>getBookByTitle(@RequestParam String title){
        return new ResponseEntity<>(bookService.getBookByTitle(title),HttpStatus.OK);
    }

}
