package com.gattuso42.BookStoreAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_pk_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String Description;
    @Column(name = "price")
    private Double price;
    @Column(name = "isbn")
    private String ISBN;
    @Column(name = "quantity_in_stock")
    private int quantityInStock;
    @Column(name = "published_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDay;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_fk_id",referencedColumnName = "author_pk_id")
    private AuthorEntity authorEntity;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_fk_id",referencedColumnName = "book_pk_id" ),
            inverseJoinColumns = @JoinColumn(name = "genre_fk_id" ,referencedColumnName = "genre_pk_id")
    )
    Set<GenreEntity> genreEntities = new HashSet<>();
}
