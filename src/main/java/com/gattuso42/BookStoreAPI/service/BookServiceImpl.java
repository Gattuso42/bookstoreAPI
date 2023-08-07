package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;

import java.util.Set;

public class BookServiceImpl implements BookService{
    @Override
    public Set<BookEntity> getAllBooks() {
        return null;
    }

    @Override
    public BookEntity getBookById(Long id) {
        return null;
    }

    @Override
    public void saveBook(BookEntity bookEntity) {

    }

    @Override
    public BookEntity updateBook(BookEntity bookEntity, Long id) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }

    @Override
    public Set<BookEntity> getBookByTitle(String title) {
        return null;
    }
}
