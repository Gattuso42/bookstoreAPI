package com.gattuso42.BookStoreAPI.controller;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.exception.CustomExceptionResponse;
import com.gattuso42.BookStoreAPI.service.AuthorService;
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
@Tag(name = "Author Controller",description = "It manages CRUD Author operations")
@RequestMapping("bookstoreapi/author")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

//    Get all the authors

    @Operation(summary = "Get all Authors",description = "Retrieves a list with all Authors stored")
    @ApiResponse(responseCode = "200",description = "Authors were retrieved successfully")
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AuthorEntity>>getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthor(),HttpStatus.OK);
    }

//    Get author by id

    @Operation(summary = "Get Author by id",description = "Get a Author using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get a Author entity successfully",content = @Content(schema = @Schema(implementation = AuthorEntity.class))),
            @ApiResponse(responseCode = "404",description = "Author entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthorEntity>getAuthorById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getAuthorById(id),HttpStatus.OK);
    }

//    Save author

    @Operation(summary = "Save a Author",description = "Save a Author with its features")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Author was save successfully"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthorEntity>saveAuthor(@Valid @RequestBody AuthorEntity authorEntity){
        authorService.saveAuthor(authorEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Upgrade author

    @Operation(summary = "Update a Author",description = "Update a Author and its features")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Update a Author entity successfully"),
            @ApiResponse(responseCode = "404",description = "Author entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthorEntity>updateAuthor(@Valid @RequestBody AuthorEntity authorEntity,@PathVariable Long id){
        authorService.updateAuthor(authorEntity,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Delete author by id

    @Operation(summary = "Delete a Author by id",description = "Delete a Author using its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",description = "Author entity was deleted successfully"),
            @ApiResponse(responseCode = "404",description = "Author entity not found",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class))),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or another Client Bad Request ",content = @Content(schema = @Schema(implementation = CustomExceptionResponse.class)))
    })
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthorEntity>deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    Search Implementations********************************

//    Search author by name
    @Operation(summary = "Search Author by name",description = "Allows searching authors using its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Author's retrieval was successful"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or Client Bad Request")
    })
    @GetMapping("/search/name")
    ResponseEntity<List<AuthorEntity>>getAuthorByName(@Valid @RequestParam String name){
        return new ResponseEntity<>(authorService.getAuthorByName(name),HttpStatus.OK);
    }

//    Get all the books by author

    @Operation(summary = "Search Book by Author",description = "Retrieves all the books by one author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Books' retrieval  was successful"),
            @ApiResponse(responseCode = "400",description = "Possible Validation error or Client Bad Request")
    })
    @GetMapping("/search-books-by-author/{id}")
    ResponseEntity<List<BookEntity>>getBooksByAuthor( @PathVariable Long id){
        return new ResponseEntity<>(authorService.getBooksByAuthor(id),HttpStatus.OK);
    }

}
