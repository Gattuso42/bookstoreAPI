package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.repository.AuthorRepository;
import com.gattuso42.BookStoreAPI.repository.BookRepository;
import com.gattuso42.BookStoreAPI.repository.GenreRepository;
import org.junit.Test;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void getAllBooksSuccessful(){
        //        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        AuthorEntity author2 = new AuthorEntity();
        author2.setId(2L);
        author2.setName("Author2");
        author2.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");



        BookEntity book1 = new BookEntity();
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for read");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);
        book1.setGenreEntities(Set.of(genre1));

        BookEntity book2 = new BookEntity();
        book2.setTitle("Title2");
        book2.setDescription("A good book for read as well");
        book2.setPublishedDay(LocalDate.parse("1850-04-10"));
        book2.setIsbn("000000000000");
        book2.setPrice(100.0);
        book2.setQuantityInStock(5);
        book2.setAuthorEntity(author2);

//        Mock
        when(bookRepository.findAll()).thenReturn(List.of(
                        book1,
                        book2
                )
        );

//      Perform
        List<BookEntity> bookResult = bookService.getAllBooks();

//       Assertions
        assertEquals("Sherlock Holmes",bookResult.get(0).getTitle());
        assertEquals("A good book for read",bookResult.get(0).getDescription());
        assertEquals(50.0,bookResult.get(0).getPrice());
        assertEquals(4,bookResult.get(0).getQuantityInStock());
        assertEquals("Arthur Conan Doyle",bookResult.get(0).getAuthorEntity().getName());

        assertEquals("Title2",bookResult.get(1).getTitle());
        assertEquals("A good book for read as well",bookResult.get(1).getDescription());
        assertEquals(100.0,bookResult.get(1).getPrice());
        assertEquals(5,bookResult.get(1).getQuantityInStock());
        assertEquals("Author2",bookResult.get(1).getAuthorEntity().getName());

        //        Verifications
        verify(bookRepository,times(1)).findAll();


    }

    @Test
    public void getAllBooksUnsuccessful(){
//        Sample
//        Mock
        when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());
//        Perform
        List<BookEntity>bookResult = bookService.getAllBooks();
//        Assertions
        assertEquals(new ArrayList<BookEntity>(),bookResult);
//        Verifications
        verify(bookRepository,times(1)).findAll();
    }
    @Test
    public void getBookByIdSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Detective");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);
        book1.setGenreEntities(Set.of(genre1));
//        Mock
        when(bookRepository.findBookEntityById(1L)).thenReturn(Optional.of(book1));
//        Perform
        BookEntity bookResult = bookService.getBookById(1L);
//        Assertions
        assertEquals("Sherlock Holmes",bookResult.getTitle());
        assertEquals("A good book for reading",bookResult.getDescription());
        assertEquals(50.0,bookResult.getPrice());
        assertEquals(1L,bookResult.getId());
        assertEquals(4,bookResult.getQuantityInStock());
        assertEquals("Arthur Conan Doyle",bookResult.getAuthorEntity().getName());
        assertEquals("England",bookResult.getAuthorEntity().getCountry());
//        Verifications
        verify(bookRepository).findBookEntityById(1L);
    }
    @Test
    public void getBookByIdUnsuccessful(){
//        Samples
        BookEntity bookEntity = new BookEntity();
//        Mock
        when(bookRepository.findBookEntityById(5L)).thenReturn(Optional.empty());
//        Perform
        try{
            bookService.getBookById(5L);
        }
        catch(Exception ignored){

        }
//        Assertions
//        Verifications
        verify(bookRepository).findBookEntityById(5L);
        verify(bookRepository,never()).save(bookEntity);
    }
    @Test
    public void getBookByTitleSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Detective");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setAuthorEntity(author1);
        book1.setGenreEntities(Set.of(genre1));
//        Mock
        when(bookRepository.findByTitleContaining("Sherlock")).thenReturn(List.of(book1));
//        Perform
        List<BookEntity>bookResult = bookService.getBookByTitle("Sherlock");
//        Assertions
        assertEquals("Sherlock Holmes",bookResult.get(0).getTitle() );
        assertEquals("A good book for reading",bookResult.get(0).getDescription());
        assertEquals("Arthur Conan Doyle",bookResult.get(0).getAuthorEntity().getName());
        assertEquals(4,bookResult.get(0).getQuantityInStock());
        assertEquals(50.0,bookResult.get(0).getPrice());
        assertEquals(LocalDate.parse("1850-04-05"),bookResult.get(0).getPublishedDay());
//        Verifications
        verify(bookRepository).findByTitleContaining("Sherlock");
    }
    @Test
    public void getBookByTitleUnsuccessful(){
//        Samples
//        Mock
        when(bookRepository.findByTitleContaining("Sherlock")).thenReturn(new ArrayList<BookEntity>());
//        Perform
        List<BookEntity>bookResult = bookService.getBookByTitle("Sherlock");
//        Assertions
        assertEquals(new ArrayList<BookEntity>(),bookResult);
//        Verifications
        verify(bookRepository).findByTitleContaining("Sherlock");
    }
    @Test
    public void saveBookSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Detective");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
//        Mock
//        Perform
        bookService.saveBook(book1,author1.getName(),author1.getCountry(),Set.of(genre1.getName()));
//        Assertions
//        Verifications
        verify(bookRepository).save(book1);
        verify(authorRepository).save(any());
        verify(authorRepository).findByNameIgnoreCase(any());
        verify(authorRepository).save(any());
        verify(genreRepository).findByNameIgnoreCase(any());
    }
    @Test
    public void saveBookUnsuccessful(){
    }
    @Test
    public void updateBookSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Detective");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
//        Mock
        when(bookRepository.findBookEntityById(1L)).thenReturn(Optional.of(book1));
//        Perform
        bookService.updateBook(book1,author1.getName(),author1.getCountry(),Set.of(genre1.getName()),1L);
//        Assertions
//        Verifications
        verify(bookRepository).save(book1);
        verify(bookRepository).findBookEntityById(1L);
        verify(authorRepository).save(any());
        verify(authorRepository).findByNameIgnoreCase(any());
        verify(authorRepository).save(any());
        verify(genreRepository).findByNameIgnoreCase(any());
    }
    @Test
    public void updateBookUnsuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Detective");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
//        Mock
        when(bookRepository.findBookEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            bookService.updateBook(book1,author1.getName(),author1.getCountry(),Set.of(genre1.getName()),1L);
        }
        catch (Exception ignored){
        }
//        Assertions
//        Verifications
        verify(bookRepository).findBookEntityById(1L);
        verify(authorRepository,never()).findByNameIgnoreCase(any());
        verify(genreRepository,never()).findByNameIgnoreCase(any());
    }
    @Test
    public void deletedBookSuccessful(){
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Detective");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
//        Mock
        when(bookRepository.findBookEntityById(1L)).thenReturn(Optional.of(book1));
//        Perform
        bookService.deleteBookById(1L);
//        Assertions
//        Verifications
        verify(bookRepository).findBookEntityById(1L);
        verify(bookRepository).deleteById(1L);
    }
    @Test
    public void deletedBookUnsuccessful(){
//        Samples
//        Mock
        when(bookRepository.findBookEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            bookService.deleteBookById(1L);
        }
        catch(Exception ignored){
        }
//        Assertions
//        Verifications
        verify(bookRepository).findBookEntityById(1L);
        verify(bookRepository,never()).deleteById(1L);
    }


}
