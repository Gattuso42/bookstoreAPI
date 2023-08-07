package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;

import java.util.Set;

public interface AuthorService {

     Set<AuthorEntity>getAllAuthor();
     AuthorEntity getAuthorById(Long id);
     void saveAuthor(AuthorEntity authorEntity,Long id);
     AuthorEntity updateAuthor(AuthorEntity authorEntity,Long id);
     void deleteAuthorById(Long id);

//    Search Methods*****************

//    Search Author by name
     Set<AuthorEntity> getAuthorByName(String name);

//   Search Books by Author
     Set<BookEntity> getBooksByAuthor(Long id);
}
