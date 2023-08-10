package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface BookRepository extends CrudRepository<BookEntity,Long> {
    Optional<BookEntity>findBookEntityById(Long id);
    List<BookEntity> findByTitleContaining(String title);
}
