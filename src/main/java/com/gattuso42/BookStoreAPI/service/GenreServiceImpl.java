package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;

import java.util.Set;

public class GenreServiceImpl implements GenreService {
    @Override
    public Set<GenreEntity> getAllGenres() {
        return null;
    }

    @Override
    public GenreEntity getGenreById(Long id) {
        return null;
    }

    @Override
    public void saveGenre(GenreEntity genreEntity) {

    }

    @Override
    public GenreEntity updateGenre(GenreEntity genreEntity, Long id) {
        return null;
    }

    @Override
    public void deleteGenreById(Long id) {

    }

    @Override
    public Set<GenreEntity> getGenreByName(String name) {
        return null;
    }

    @Override
    public Set<BookEntity> getBooksByGenre(Long id) {
        return null;
    }
}
