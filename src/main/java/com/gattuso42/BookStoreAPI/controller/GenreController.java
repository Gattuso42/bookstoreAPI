package com.gattuso42.BookStoreAPI.controller;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("bookstoreapi/genre")
public class GenreController {

//    Get all the genres
    @GetMapping("/all")
    ResponseEntity<GenreEntity>getAllGenre(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Get Genre by id
    @GetMapping("/{id}")
    ResponseEntity<GenreEntity>getGenreById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Save Genre
    @PostMapping()
    ResponseEntity<GenreEntity> saveGenre(@RequestBody GenreEntity genreEntity){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


//    Upgrade Genre
    @PutMapping("/{id}")
    ResponseEntity<GenreEntity>upgradeGenre(@RequestBody GenreEntity genreEntity,@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Deleted Genre by id
    @DeleteMapping("/{id}")
    ResponseEntity<GenreEntity>deleteGenreById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Search Implementations***************************

//    Search Genre by name
    @GetMapping("/search/name")
    ResponseEntity<Set<GenreEntity>>getGenreByName(@RequestParam String name){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Search books by Genre
    @GetMapping("/search-books-by-genre/{id}")
    ResponseEntity<Set<BookEntity>>getBooksByGenre(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
