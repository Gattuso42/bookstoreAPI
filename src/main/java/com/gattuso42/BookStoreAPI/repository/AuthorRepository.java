package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
}
