package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<GenreEntity,Long> {
}
