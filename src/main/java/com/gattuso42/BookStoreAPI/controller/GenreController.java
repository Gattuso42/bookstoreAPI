package com.gattuso42.BookStoreAPI.controller;


import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.exception.CustomExceptionResponse;
import com.gattuso42.BookStoreAPI.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Genre Controller",description = "It manages CRUD Genre operations")
@RequestMapping("bookstoreapi/genre")
@AllArgsConstructor
public class GenreController {

    private GenreService genreService;

//    Get all the genres

    @Operation(summary = "Get all Genres",description = "Retrieves a list with all Genres stored")
    @ApiResponse(responseCode = "200",description = "Genres were retrieved successfully")
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GenreEntity>>getAllGenre(){
        return new ResponseEntity<>(genreService.getAllGenres(),HttpStatus.OK);
    }

//    Get Genre by id

    @Operation(summary = "Get Genre by id",description = "Get a Genre using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get a Genre entity successfully",content = @Content(schema = @Schema(implementation = GenreEntity.class))),
            @ApiResponse(responseCode = "404",description = "Genre entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<GenreEntity>getGenreById(@PathVariable Long id){
        return new ResponseEntity<>(genreService.getGenreById(id),HttpStatus.OK);
    }

//    Save Genre

    @Operation(summary = "Save a Genre",description = "Save a Genre with its features")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Genre was save successfully"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @PostMapping()
    ResponseEntity<GenreEntity> saveGenre(@Valid @RequestBody GenreEntity genreEntity){
        genreService.saveGenre(genreEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


//    Upgrade Genre

    @Operation(summary = "Update a Genre",description = "Update a Genre and its features")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Update a Genre entity successfully"),
            @ApiResponse(responseCode = "404",description = "Genre entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    ResponseEntity<GenreEntity>upgradeGenre(@Valid @RequestBody GenreEntity genreEntity,@PathVariable Long id){
        genreService.updateGenre(genreEntity,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Deleted Genre by id

    @Operation(summary = "Delete a Genre by id",description = "Delete a Genre using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Genre entity was deleted successfully"),
            @ApiResponse(responseCode = "404",description = "Genre entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<GenreEntity>deleteGenreById(@PathVariable Long id){
        genreService.deleteGenreById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Search Implementations***************************

//    Search Genre by name

    @Operation(summary = "Search Genre by name",description = "Allows searching authors using its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Genre's retrieval was successful"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or Client Bad Request")
    })
    @GetMapping("/search/name")
    ResponseEntity<List<GenreEntity>>getGenreByName(@Valid @RequestParam String name){
        return new ResponseEntity<>(genreService.getGenreByName(name),HttpStatus.OK);
    }

//    Search books by Genre

    @Operation(summary = "Search Book by Genre",description = "Retrieves all the books by one Genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Books' retrieval  was successful"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or Client Bad Request")
    })
    @GetMapping("/search-books-by-genre/{id}")
    ResponseEntity<List<BookEntity>>getBooksByGenre(@PathVariable Long id){
        return new ResponseEntity<>(genreService.getBooksByGenre(id),HttpStatus.OK);
    }

}
