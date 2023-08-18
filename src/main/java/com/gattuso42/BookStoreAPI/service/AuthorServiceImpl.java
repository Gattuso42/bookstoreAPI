package com.gattuso42.BookStoreAPI.service;

import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    AuthorRepository authorRepository;
    @Override
    public List<AuthorEntity> getAllAuthor() {
        return (List<AuthorEntity>)authorRepository.findAll();
    }

    @Override
    public AuthorEntity getAuthorById(Long id) {
        Optional<AuthorEntity>auxData = authorRepository.findAuthorEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Author with this id is not found");
        else return auxData.get();
    }

    @Override
    public void saveAuthor(AuthorEntity authorEntity) {
        authorRepository.save(authorEntity);
    }

    @Override
    public void updateAuthor(AuthorEntity authorEntity, Long id) {
        Optional<AuthorEntity>auxData = authorRepository.findAuthorEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Author with this id is not found");
        else{
            AuthorEntity authorData = auxData.get();
            authorData.setName(authorEntity.getName());
            authorData.setCountry(authorEntity.getCountry());
            authorRepository.save(authorData);
        }
    }

    @Override
    public void deleteAuthorById(Long id) {
        Optional<AuthorEntity>auxData = authorRepository.findAuthorEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Author with this id is not found");
        else authorRepository.deleteById(id);
    }

//    Searching Methods********************

//    Search Author by name
    @Override
    public List<AuthorEntity> getAuthorByName(String name) {
        return authorRepository.findByNameContaining(name);
    }

//    Search Book by Author
    @Override
    public List<BookEntity> getBooksByAuthor(Long id) {
        Optional<AuthorEntity>auxData = authorRepository.findAuthorEntityById(id);
        if(auxData.isEmpty())throw new EntityNotFoundException("The Author with this id is not found");
        else return auxData.get().getBookEntities();
    }
}
