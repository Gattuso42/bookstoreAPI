package com.gattuso42.BookStoreAPI.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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


    @NotBlank(message = "Author name must not be blank")
    @Size(max = 35,message = "Name must be up to 35 characters ")
//  @Pattern(regexp = "^[a-zA-Z]*$",message = "Only letters are allowed")
    @Column(name = "author_name")
    private String name;


    @NotBlank(message = "Country must not be blank")
    @Size(max = 35,message = "Country must be up to 35 characters ")
    @Pattern(regexp = "^[a-zA-Z]*$",message = "Only letters are allowed")
    @Column(name = "country")
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "authorEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    Set<BookEntity> bookEntities;
}
