package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.repository.AuthorRepository;
import com.gattuso42.BookStoreAPI.repository.BookRepository;
import com.gattuso42.BookStoreAPI.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

   private BookRepository bookRepository;
   private AuthorRepository authorRepository;
   private GenreRepository genreRepository;

    @Override
    public List<BookEntity> getAllBooks() {
        return (List<BookEntity>) bookRepository.findAll();
    }

    @Override
    public BookEntity getBookById(Long id) {
        Optional<BookEntity> auxData = bookRepository.findBookEntityById(id);
        if(auxData.isEmpty()) throw new EntityNotFoundException("The Book with this id is not found");
        else return auxData.get();
    }

    @Override
    public void saveBook(BookEntity bookEntity,String authorName,String country,Set<String> genreName) {
        for(String aux:genreName){
            Optional<GenreEntity>genreAuxData = genreRepository.findByNameIgnoreCase(aux);
            if(genreAuxData.isEmpty()){
                GenreEntity newGenre = new GenreEntity();
                newGenre.setName(aux);
                genreRepository.save(newGenre);
                bookEntity.getGenreEntities().add(newGenre);
            }
            else{
                bookEntity.getGenreEntities().add(genreAuxData.get());
            }
        }

        List<AuthorEntity>listAuthorsData = authorRepository.findByNameIgnoreCase(authorName);
        if(listAuthorsData.isEmpty()){
            AuthorEntity newAuthor = new AuthorEntity();
            newAuthor.setName(authorName);
            newAuthor.setCountry(country);
            authorRepository.save(newAuthor);
            bookEntity.setAuthorEntity(newAuthor);
        }
        else{
            bookEntity.setAuthorEntity(listAuthorsData.get(0));
        }
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBook(BookEntity bookEntity,String authorName,String country,Set<String> genreName, Long id) {
        Optional<BookEntity> auxData = bookRepository.findBookEntityById(id);
//        Process similar to save
        if(auxData.isEmpty()) throw new EntityNotFoundException("The Book with this id is not found");
        else {
//          Verifying if the Genres
            for(String aux:genreName){
                Optional<GenreEntity>genreAuxData = genreRepository.findByNameIgnoreCase(aux);
                if(genreAuxData.isEmpty()){
                    GenreEntity newGenre = new GenreEntity();
                    newGenre.setName(aux);
                    genreRepository.save(newGenre);
                    bookEntity.getGenreEntities().add(newGenre);
                }
                else{
                    bookEntity.getGenreEntities().add(genreAuxData.get());
                }
            }
//            Verifying the Authors
            List<AuthorEntity>listAuthorsData = authorRepository.findByNameIgnoreCase(authorName);
            if(listAuthorsData.isEmpty()){
                AuthorEntity newAuthor = new AuthorEntity();
                newAuthor.setName(authorName);
                newAuthor.setCountry(country);
                authorRepository.save(newAuthor);
                bookEntity.setAuthorEntity(newAuthor);
            }
            else{
                bookEntity.setAuthorEntity(listAuthorsData.get(0));
            }
//            Update Process
            auxData.get().setTitle(bookEntity.getTitle());
            auxData.get().setDescription(bookEntity.getDescription());
            auxData.get().setIsbn(bookEntity.getIsbn());
            auxData.get().setPrice(bookEntity.getPrice());
            auxData.get().setQuantityInStock(bookEntity.getQuantityInStock());
            auxData.get().setAuthorEntity(bookEntity.getAuthorEntity());
            auxData.get().setGenreEntities(bookEntity.getGenreEntities());

            bookRepository.save(auxData.get());
        }

    }

    @Override
    public void deleteBookById(Long id) {
        Optional<BookEntity> auxData = bookRepository.findBookEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Book with this id is not found");
        else bookRepository.deleteById(id);
    }


//  Searching Methods*****************

//  Search Book by title
    @Override
    public List<BookEntity> getBookByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

//    Aux Methods


}
