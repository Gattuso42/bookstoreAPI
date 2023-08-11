package com.gattuso42.BookStoreAPI.entity;

import com.gattuso42.BookStoreAPI.validation.Isbn;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;
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
    @NotNull(message = "Title must not be null")
    @NotBlank(message = "Title must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "Only numbers and letters are allowed")
    @Size(max = 50,message = "Title must not bigger than 50 characters" )
    @Column(name = "title")
    private String title;

    @NotNull(message = "Description must not be null")
    @NotBlank(message = "Description must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$",message = "Only numbers and letters are allowed")
    @Size(max = 50,message = "The description must not bigger than 150 characters" )
    @Column(name = "description")
    private String Description;

    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$",message = "")
    @NotNull(message = "Price must not be null ")
    @NotBlank(message = "Price must not be blank")
    @Digits(integer = 9,fraction =2,message = "Price must have ten integer digits and two fraction digits")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "ISBN must not be null ")
    @NotBlank(message = "ISBN must not be blank")
    @Isbn
    @Digits(integer = 13 ,fraction = 0,message = "Price must have only integer digits")
    @Column(name = "isbn")
    private String isbn;

    @NotBlank(message = "Quantity must not be blank")
    @Digits(integer = 10 ,fraction = 0,message = "Quantity must have up to 10 integer digits")
    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @PastOrPresent(message = "Published day must not be in future")
    @NotBlank(message = "Published day must not be blank")
    @Column(name = "published_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDay;
    @NotNull(message = "Author must not be null")
    @NotBlank(message = "Author must not be blank")
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_fk_id",referencedColumnName = "author_pk_id")
    private AuthorEntity authorEntity;

//    @JsonIgnore
    @NotNull(message = "Genre must not be null")
    @NotBlank(message = "Genre must not be blank")
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_fk_id",referencedColumnName = "book_pk_id" ),
            inverseJoinColumns = @JoinColumn(name = "genre_fk_id" ,referencedColumnName = "genre_pk_id")
    )
    Set<GenreEntity> genreEntities = new HashSet<>();
}
