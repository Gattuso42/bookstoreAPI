package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;

import java.util.Set;

public class AuthorServiceImpl implements AuthorService{
    @Override
    public Set<AuthorEntity> getAllAuthor() {
        return null;
    }

    @Override
    public AuthorEntity getAuthorById(Long id) {
        return null;
    }

    @Override
    public void saveAuthor(AuthorEntity authorEntity, Long id) {

    }

    @Override
    public AuthorEntity updateAuthor(AuthorEntity authorEntity, Long id) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    @Override
    public Set<AuthorEntity> getAuthorByName(String name) {
        return null;
    }

    @Override
    public Set<BookEntity> getBooksByAuthor(Long id) {
        return null;
    }
}
