package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService {

     List<BookEntity> getAllBooks();
     BookEntity getBookById(Long id);
     void saveBook(BookEntity bookEntity,String authorName,String country,List<String> genreName);
     void updateBook(BookEntity bookEntity,String authorName,String country,List<String> genreName,Long id);
     void deleteBookById(Long id);

//    Searching Methods*****************

//    Search Book by title
     List<BookEntity> getBookByTitle(String title);


}
