package com.gattuso42.BookStoreAPI.repository;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
    Optional<AuthorEntity>findAuthorEntityById(Long id);

    List<AuthorEntity>findByNameContaining(String name);

    List<AuthorEntity>findByNameIgnoreCase(String name);
}
