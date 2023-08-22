package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void getAllAuthorsSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");
        AuthorEntity author2 = new AuthorEntity();
        author2.setId(2L);
        author2.setName("Jules Verne");
        author2.setCountry("France");
//        Mock
        when(authorRepository.findAll()).thenReturn(List.of(author1,author2));
//        Perform
        List<AuthorEntity>authorResult = authorService.getAllAuthor();
//        Assertions
        assertEquals("Arthur Conan Doyle",authorResult.get(0).getName());
        assertEquals("England",authorResult.get(0).getCountry());
        assertEquals(1L,authorResult.get(0).getId());

        assertEquals(2L,authorResult.get(1).getId());
        assertEquals("Jules Verne",authorResult.get(1).getName());
        assertEquals("France",authorResult.get(1).getCountry());
//        Verifications
        verify(authorRepository).findAll();
    }
    @Test
    public void getAllAuthorUnsuccessful(){
//        Samples
//        Mock
        when(authorRepository.findAll()).thenReturn(new ArrayList<AuthorEntity>());
//        Perform
        List<AuthorEntity>authorResult = authorService.getAllAuthor();
//        Assertions
        assertEquals(new ArrayList<AuthorEntity>(),authorResult);
//        Verifications
        verify(authorRepository).findAll();
    }
    @Test
    public void getAuthorByIdSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");


        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);

        author1.setBookEntities(List.of(book1));
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.of(author1));
//        Perform
        AuthorEntity authorSample = authorService.getAuthorById(1L);
        List<BookEntity>bookEntities = authorSample.getBookEntities();

//        Assertions
        assertEquals("Arthur Conan Doyle",authorSample.getName());
        assertEquals("England",authorSample.getCountry());
        assertEquals(1L,authorSample.getId());
        assertEquals("Sherlock Holmes",bookEntities.get(0).getTitle());
        assertEquals("A good book for reading",bookEntities.get(0).getDescription());
        assertEquals(50.0,bookEntities.get(0).getPrice());
        assertEquals(LocalDate.parse("1850-04-05"),bookEntities.get(0).getPublishedDay());
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
    }
    @Test
    public void getAuthorByIdUnsuccessful(){
//        Samples
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            authorService.getAuthorById(1L);
        }
        catch(EntityNotFoundException ignored){
        }
//        Assertions
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
    }

    @Test
    public void getAuthorByNameSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);

        author1.setBookEntities(List.of(book1));
//        Mock
        when(authorRepository.findByNameContaining("Sherlock")).thenReturn(List.of(author1));
//        Perform
        List<AuthorEntity>authorSample = authorService.getAuthorByName("Sherlock");
        List<BookEntity>bookEntities = authorSample.get(0).getBookEntities();
//        Assertions
        assertEquals("Arthur Conan Doyle",authorSample.get(0).getName());
        assertEquals("England",authorSample.get(0).getCountry());
        assertEquals(1L,authorSample.get(0).getId());
        assertEquals("Sherlock Holmes",bookEntities.get(0).getTitle());
        assertEquals("A good book for reading",bookEntities.get(0).getDescription());
        assertEquals(50.0,bookEntities.get(0).getPrice());
        assertEquals(LocalDate.parse("1850-04-05"),bookEntities.get(0).getPublishedDay());
//        Verifications
        verify(authorRepository).findByNameContaining("Sherlock");
    }

    @Test
    public void getAuthorByNameUnsuccessful(){
//        Samples
//        Mock
        when(authorRepository.findByNameContaining("Sherlock")).thenReturn(new ArrayList<AuthorEntity>());
//        Perform
        List<AuthorEntity>authorResult = authorService.getAuthorByName("Sherlock");
//        Assertions
        assertEquals(new ArrayList<AuthorEntity>(),authorResult);
//        Verifications
        verify(authorRepository).findByNameContaining("Sherlock");
    }

    @Test
    public void getBooksByAuthorSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);

        author1.setBookEntities(List.of(book1));
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.of(author1));
//        Perform
        List<BookEntity>bookEntities = new ArrayList<>(authorService.getBooksByAuthor(1L));
//        Assertions
        assertEquals("Sherlock Holmes",bookEntities.get(0).getTitle());
        assertEquals("A good book for reading",bookEntities.get(0).getDescription());
        assertEquals(1L,bookEntities.get(0).getId());
        assertEquals(1,bookEntities.size());
        assertEquals(50.0,bookEntities.get(0).getPrice());
        assertEquals("Arthur Conan Doyle",bookEntities.get(0).getAuthorEntity().getName());
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
    }

    @Test
    public void getBooksByAuthorUnsuccessful(){
//        Samples
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            authorService.getBooksByAuthor(1L);
        }
        catch(EntityNotFoundException ignored){
        }
//        Assertions
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
    }

    @Test
    public void saveAuthorSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");
//        Mock
//        Perform
        authorService.saveAuthor(author1);
//        Assertions
//        Verifications
        verify(authorRepository).save(author1);
    }

    @Test
    public void getUpdateAuthorSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.of(author1));
//        Perform
        authorService.updateAuthor(author1,1L);
//        Assertions
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
        verify(authorRepository).save(author1);
    }

    @Test
    public void updateAuthorUnsuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            authorService.updateAuthor(author1,1L);
        }
        catch(EntityNotFoundException ignored){

        }
//        Assertions
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
        verify(authorRepository,never()).save(any());
    }

    @Test
    public void deletedAuthorByIdSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.of(author1));
//        Perform
        authorService.deleteAuthorById(1L);
//        Assertions
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
        verify(authorRepository).deleteById(1L);
    }

    @Test
    public void deletedAuthorByIdUnsuccessful(){
//        Samples
//        Mock
        when(authorRepository.findAuthorEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            authorService.deleteAuthorById(1L);
        }
        catch(EntityNotFoundException ignored){
        }
//        Assertions
//        Verifications
        verify(authorRepository).findAuthorEntityById(1L);
        verify(authorRepository,never()).deleteById(1L);

    }
}
