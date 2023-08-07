package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;

import java.util.Set;

public interface GenreService {


     Set<GenreEntity> getAllGenres();
     GenreEntity getGenreById(Long id);
     void saveGenre(GenreEntity genreEntity);
     GenreEntity updateGenre(GenreEntity genreEntity,Long id);
     void deleteGenreById(Long id);

//    Searching Methods*******************

//    Search Genre by name
     Set<GenreEntity>getGenreByName(String name);

//    Search Books by
     Set<BookEntity>getBooksByGenre(Long id);

}
