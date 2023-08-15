package com.gattuso42.BookStoreAPI.service;


import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceTest {
    @Mock
    GenreRepository genreRepository;
    @InjectMocks
    GenreServiceImpl genreService;

//Samples
//    GenreEntity genre1 = new GenreEntity();
//    genre1.setId(1L);
//    genre1.setName("Adventure");




    @Test
    public void getAllGenresSuccessful() {
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setName("Detective");
//        Mock
        when(genreRepository.findAll()).thenReturn(List.of(genre1,genre2));
//        Perform
        List<GenreEntity>genreResult = genreService.getAllGenres();
//        Assertions
        assertEquals("Adventure",genreResult.get(0).getName());
        assertEquals(1L,genreResult.get(0).getId());
        assertEquals("Detective",genreResult.get(1).getName());
        assertEquals(2L,genreResult.get(1).getId());
//        Verifications
        verify(genreRepository).findAll();
    }
    @Test
    public void getAllGenresUnsuccessful(){
//        Samples
//        Mock
        when(genreRepository.findAll()).thenReturn(new ArrayList<GenreEntity>());
//        Perform
        List<GenreEntity>genreResult = genreService.getAllGenres();
//        Assertions
        assertEquals(new ArrayList<GenreEntity>(),genreResult);
//        Verifications
        verify(genreRepository).findAll();
    }
    @Test
    public void getGenreByIdSuccessful(){
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.of(genre1));
//        Perform
        GenreEntity genreResult = genreService.getGenreById(1L);
//        Assertions
        assertEquals("Adventure",genreResult.getName());
        assertEquals(1L,genreResult.getId());
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
    }
    @Test
    public void getGenreByIdUnsuccessful(){
//        Samples
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            GenreEntity genreResult = genreService.getGenreById(1L);
        }
        catch(EntityNotFoundException ignored){

        }
//        Assertions
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
    }
    @Test
    public void getGenreByNameSuccessful(){
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");
//        Mock
        when(genreRepository.findByNameContaining("Adventure")).thenReturn(Set.of(genre1));
//        Perform
        List<GenreEntity>genreResult = new ArrayList<>(genreService.getGenreByName("Adventure"));
//        Assertions
        assertEquals("Adventure",genreResult.get(0).getName());
        assertEquals(1L,genreResult.get(0).getId());
//        Verifications
        verify(genreRepository).findByNameContaining("Adventure");
    }
    @Test
    public void getGenreByNameUnsuccessful(){
//        Samples
//        Mock
        when(genreRepository.findByNameContaining("Adventure")).thenReturn(new HashSet<GenreEntity>());
//        Perform
        Set<GenreEntity> genreResult = genreService.getGenreByName("Adventure");
//        Assertions
        assertEquals(new HashSet<GenreEntity>(),genreResult);
//        Verifications
        verify(genreRepository).findByNameContaining("Adventure");
    }
    @Test
    public void getBooksByGenreSuccessful(){
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for reading");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);
        book1.setGenreEntities(Set.of(genre1));

        genre1.setBookEntities(Set.of(book1));
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.of(genre1));
//        Perform
        List<BookEntity>bookEntities = new ArrayList<>(genreService.getBooksByGenre(1L));
//        Assertions
        assertEquals("Sherlock Holmes",bookEntities.get(0).getTitle());
        assertEquals(1L,bookEntities.get(0).getId());
        assertEquals("A good book for reading",bookEntities.get(0).getDescription());
        assertEquals(50.0,bookEntities.get(0).getPrice());
        assertEquals("Adventure",new ArrayList<>(bookEntities.get(0).getGenreEntities()).get(0).getName());

//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
    }
    @Test
    public void getBooksByGenreUnsuccessful(){
//        Samples
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            genreService.getBooksByGenre(1L);
        }
        catch(EntityNotFoundException ignored){
        }
//        Assertions
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
    }
    @Test
    public void saveGenreSuccessful(){
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");
//        Mock
//        Perform
        genreService.saveGenre(genre1);
//        Assertions
//        Verifications
        verify(genreRepository).save(genre1);
    }
    @Test
    public void updateGenreSuccessful(){
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.of(genre1));
//        Perform
        genreService.updateGenre(genre1,1L);
//        Assertions
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
        verify(genreRepository).save(genre1);
    }
    @Test
    public void updateGenreUnsuccessful(){
//        Samples
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.empty());
//        Perform
        try{
            genreService.updateGenre(new GenreEntity(),1L);
        }
        catch (EntityNotFoundException ignored){
        }
//        Assertions
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
        verify(genreRepository,never()).save(any());
    }
    @Test
    public void deletedByIdSuccessful(){
//        Samples
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("Adventure");
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.of(genre1));
//        Perform
        genreService.deleteGenreById(1L);
//        Assertions
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
        verify(genreRepository).deleteById(1L);
    }
    @Test
    public void deletedByIdUnsuccessful(){
//        Samples
//        Mock
        when(genreRepository.findGenreEntityById(1L)).thenReturn(Optional.empty());
//        Perform
       try{
           genreService.deleteGenreById(1L);
       }
       catch(EntityNotFoundException ignored){
       }
//        Assertions
//        Verifications
        verify(genreRepository).findGenreEntityById(1L);
        verify(genreRepository,never()).deleteById(1L);
    }

}
