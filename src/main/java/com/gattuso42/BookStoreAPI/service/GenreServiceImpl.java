package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import com.gattuso42.BookStoreAPI.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    GenreRepository genreRepository;
    @Override
    public List<GenreEntity> getAllGenres() {
        return (List<GenreEntity>)genreRepository.findAll();
    }

    @Override
    public GenreEntity getGenreById(Long id) {
        Optional<GenreEntity> auxData = genreRepository.findGenreEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Genre with this id is not found");
        else return auxData.get();
    }

    @Override
    public void saveGenre(GenreEntity genreEntity) {
        genreRepository.save(genreEntity);
    }

    @Override
    public void updateGenre(GenreEntity genreEntity, Long id) {
        Optional<GenreEntity>auxData = genreRepository.findGenreEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Genre with this id is not found");
        else {
            GenreEntity genreData = auxData.get();
            genreData.setName(genreEntity.getName());
            genreRepository.save(genreData);
        }
    }

    @Override
    public void deleteGenreById(Long id) {
        Optional<GenreEntity>auxData = genreRepository.findGenreEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Genre with this id not found");
        else genreRepository.deleteById(id);

    }

//   Searching Methods*******************

//    Search Genre by name
    @Override
    public List<GenreEntity> getGenreByName(String name) {
        return genreRepository.findByNameContaining(name);
    }

//    Search Books by Genre
    @Override
    public List<BookEntity> getBooksByGenre(Long id) {
        Optional<GenreEntity>auxData = genreRepository.findGenreEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Genre with this id is not found");
        else return auxData.get().getBookEntities();

    }
}
