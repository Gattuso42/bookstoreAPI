package com.gattuso42.BookStoreAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_pk_id")
    private Long id;
    @Column(name = "genre_name")
    private String name;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "genre_fk_id" ,referencedColumnName = "genre_pk_id"),
            inverseJoinColumns = @JoinColumn(name = "book_fk_id",referencedColumnName = "book_pk_id" )
    )
    Set<BookEntity> bookEntities;
}
