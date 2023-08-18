package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;

import java.util.List;
import java.util.Set;

public interface AuthorService {

     List<AuthorEntity>getAllAuthor();
     AuthorEntity getAuthorById(Long id);
     void saveAuthor(AuthorEntity authorEntity);
     void updateAuthor(AuthorEntity authorEntity,Long id);
     void deleteAuthorById(Long id);

//    Search Methods*****************

//    Search Author by name
     List<AuthorEntity> getAuthorByName(String name);

//   Search Books by Author
     List<BookEntity> getBooksByAuthor(Long id);
}
