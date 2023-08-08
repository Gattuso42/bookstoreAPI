package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends CrudRepository<BookEntity,Long> {
    Optional<BookEntity>findBookEntityById(Long id);

    List<BookEntity> findByTitleContaining(String title);
}
