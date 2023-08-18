package com.gattuso42.BookStoreAPI.controller;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("bookstoreapi/genre")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;

//    Get all the genres
    @GetMapping("/all")
    ResponseEntity<List<GenreEntity>>getAllGenre(){
        return new ResponseEntity<>(genreService.getAllGenres(),HttpStatus.OK);
    }

//    Get Genre by id
    @GetMapping("/{id}")
    ResponseEntity<GenreEntity>getGenreById(@PathVariable Long id){
        return new ResponseEntity<>(genreService.getGenreById(id),HttpStatus.OK);
    }

//    Save Genre
    @PostMapping()
    ResponseEntity<GenreEntity> saveGenre(@Valid @RequestBody GenreEntity genreEntity){
        genreService.saveGenre(genreEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


//    Upgrade Genre
    @PutMapping("/{id}")
    ResponseEntity<GenreEntity>upgradeGenre(@Valid @RequestBody GenreEntity genreEntity,@PathVariable Long id){
        genreService.updateGenre(genreEntity,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Deleted Genre by id
    @DeleteMapping("/{id}")
    ResponseEntity<GenreEntity>deleteGenreById(@PathVariable Long id){
        genreService.deleteGenreById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Search Implementations***************************

//    Search Genre by name
    @GetMapping("/search/name")
    ResponseEntity<List<GenreEntity>>getGenreByName(@Valid @RequestParam String name){
        return new ResponseEntity<>(genreService.getGenreByName(name),HttpStatus.OK);
    }

//    Search books by Genre
    @GetMapping("/search-books-by-genre/{id}")
    ResponseEntity<List<BookEntity>>getBooksByGenre(@PathVariable Long id){
        return new ResponseEntity<>(genreService.getBooksByGenre(id),HttpStatus.OK);
    }

}
