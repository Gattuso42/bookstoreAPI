package com.gattuso42.BookStoreAPI.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_pk_id")
    private Long id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "authorEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<BookEntity> bookEntities;
}
