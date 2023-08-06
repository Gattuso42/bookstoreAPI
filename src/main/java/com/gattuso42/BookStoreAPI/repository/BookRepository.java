package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity,Long> {
}
