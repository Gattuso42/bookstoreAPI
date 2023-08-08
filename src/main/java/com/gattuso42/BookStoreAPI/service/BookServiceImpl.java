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

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    GenreRepository genreRepository;

    @Override
    public Set<BookEntity> getAllBooks() {
        return (Set<BookEntity>) bookRepository.findAll();
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
            authorRepository.save(newAuthor);
            bookEntity.setAuthorEntity(newAuthor);
        }
        else{
            bookEntity.setAuthorEntity(listAuthorsData.get(0));
        }
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBook(BookEntity bookEntity, Long id) {
        BookEntity upgradedBook = new BookEntity();
        Optional<BookEntity> auxData = bookRepository.findBookEntityById(id);
        if(auxData.isEmpty()) throw new EntityNotFoundException("The Book with this id is not found");
        else {
            auxData.get().setTitle(bookEntity.getTitle());
            auxData.get().setDescription(bookEntity.getDescription());
            auxData.get().setISBN(bookEntity.getISBN());
            auxData.get().setPrice(bookEntity.getPrice());
            auxData.get().setQuantityInStock(bookEntity.getQuantityInStock());
//            Different Process
            auxData.get().setAuthorEntity(bookEntity.getAuthorEntity());
            auxData.get().setGenreEntities(bookEntity.getGenreEntities());
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
