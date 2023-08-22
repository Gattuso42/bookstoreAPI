package com.gattuso42.BookStoreAPI.controller;


import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.exception.CustomExceptionResponse;
import com.gattuso42.BookStoreAPI.service.BookService;
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
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bookstoreapi/book")
@AllArgsConstructor
@Tag(name = "Book Controller",description = "It manages CRUD Book operations")
public class BookController {

    private BookService bookService;

//  Get all the books
    @Operation(summary = "Get all books",description = "Retrieves a list with all books stored")
    @ApiResponse(responseCode = "200",description = "Books were retrieved successfully")
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BookEntity>>getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

//    Get books by id
    @Operation(summary = "Get book by id",description = "Get a book using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get a Book entity successfully",content = @Content(schema = @Schema(implementation = BookEntity.class))),
            @ApiResponse(responseCode = "404",description = "Book entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookEntity>getBookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

//    Save a Book
    @Operation(summary = "Save a Book",description = "Save a book with its features",parameters = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Book was save successfully"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookEntity>saveBook(@Valid @RequestBody BookEntity bookEntity,@Valid @RequestParam String authorName,@Valid @RequestParam String authorCountry,@Valid @RequestParam List<String> genreName){
        bookService.saveBook(bookEntity,authorName,authorCountry,genreName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Upgrade a Book
    @Operation(summary = "Update a book",description = "Update a book and its features")
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Update a Book entity successfully"),
            @ApiResponse(responseCode = "404",description = "Book entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    ResponseEntity<BookEntity>upgradeBook(@RequestBody BookEntity bookEntity,@PathVariable Long id,@RequestParam String authorName,@RequestParam String authorCountry,@RequestParam List<String> genreName){
        bookService.updateBook(bookEntity,authorName,authorCountry, genreName,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


//    Delete a Book
    @Operation(summary = "Delete a book by id",description = "Delete a book using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Book entity was deleted successfully"),
            @ApiResponse(responseCode = "404",description = "Book entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BookEntity>deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


//    Search Implementations***************************

//    Search books by title
    @Operation(summary = "Get book by title",description = "Get a book using its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Books entities successfully",content = @Content(schema = @Schema(implementation = BookEntity.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @GetMapping(value = "search/title",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BookEntity>>getBookByTitle(@Valid @RequestParam String title){
        return new ResponseEntity<>(bookService.getBookByTitle(title),HttpStatus.OK);
    }

}
