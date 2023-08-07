package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;

import java.util.Optional;
import java.util.Set;

public interface BookService {

     Set<BookEntity> getAllBooks();
     BookEntity getBookById(Long id);
     void saveBook(BookEntity bookEntity);
     BookEntity updateBook(BookEntity bookEntity,Long id);
     void deleteBookById(Long id);

//    Searching Methods*****************

//    Search Book by title
     Set<BookEntity> getBookByTitle(String title);


}
