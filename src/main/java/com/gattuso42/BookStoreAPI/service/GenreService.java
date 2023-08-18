package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;

import java.util.List;
import java.util.Set;

public interface GenreService {


     List<GenreEntity> getAllGenres();
     GenreEntity getGenreById(Long id);
     void saveGenre(GenreEntity genreEntity);
     void updateGenre(GenreEntity genreEntity,Long id);
     void deleteGenreById(Long id);

//    Searching Methods*******************

//    Search Genre by name
     List<GenreEntity>getGenreByName(String name);

//    Search Books by
     List<BookEntity>getBooksByGenre(Long id);

}
