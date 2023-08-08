package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface GenreRepository extends CrudRepository<GenreEntity,Long> {
    Optional<GenreEntity>findGenreEntityById(Long id);

    Set<GenreEntity>findByNameContaining(String name);

    Optional<GenreEntity>findByNameIgnoreCase(String genreName);
}
