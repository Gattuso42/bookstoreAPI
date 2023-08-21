package com.gattuso42.BookStoreAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.entity.BookEntity;
import com.gattuso42.BookStoreAPI.entity.GenreEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookStoreApiApplicationTests {
//  Integration Test
    @Autowired
    private MockMvc mockMvc;

    @Autowired
   private ObjectMapper objectMapper;

//    Base URLs
    private static final String BASE_BOOK_URL = "/bookstoreapi/book/";
    private static final String BASE_AUTHOR_URL = "/bookstoreapi/author/";
    private static final String BASE_GENRE_URL = "/bookstoreapi/genre/";




//    Get all Entities
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetAllEntitiesSuccessful() throws Exception {
//        Samples
        RequestBuilder getAllAuthorUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"all");
        RequestBuilder getAllGenreUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"all");
        RequestBuilder getAllBookUrl = MockMvcRequestBuilders.get(BASE_BOOK_URL +"all");
//        Perform
        mockMvc.perform(getAllAuthorUrl)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].name").value("AuthorX"))
                .andExpect(jsonPath("$.[0].country").value("England"))
                .andExpect(jsonPath("$.[1].name").value("AuthorY"))
                .andExpect(jsonPath("$.[1].country").value("Italy"))
                .andDo(print());


        mockMvc.perform(getAllGenreUrl)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].name").value("Adventure"))
                .andExpect(jsonPath("$.[1].name").value("Detective"))
                .andExpect(jsonPath("$.[2].name").value("Science"))
                .andDo(print());


        mockMvc.perform(getAllBookUrl)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].title").value("Title1"))
                .andExpect(jsonPath("$.[0].description").value("Description1"))
                .andExpect(jsonPath("$.[0].price").value("50.0"))
                .andExpect(jsonPath("$.[0].quantityInStock").value("5"))
                .andExpect(jsonPath("$.[0].authorEntity.name").value("AuthorX"))
                .andExpect(jsonPath("$.[0].authorEntity.country").value("England"))
                .andExpect(jsonPath("$.[0].genreEntities.[0].name").value("Adventure"))
                .andExpect(jsonPath("$.[1].title").value("Title2"))
                .andExpect(jsonPath("$.[1].description").value("Description2"))
                .andExpect(jsonPath("$.[1].price").value("100.0"))
                .andExpect(jsonPath("$.[1].quantityInStock").value("10"))
                .andExpect(jsonPath("$.[1].authorEntity.name").value("AuthorX"))
                .andExpect(jsonPath("$.[1].authorEntity.country").value("England"))
                .andExpect(jsonPath("$.[1].genreEntities.[0].name").value("Adventure"))
                .andDo(print());
    }

    @Test
    public void shouldGetAllEntitiesEmpty() throws Exception {
//        Samples
        RequestBuilder getAllAuthorsUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"all");
        RequestBuilder getAllGenreUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"all");
        RequestBuilder getAllBooksUrl = MockMvcRequestBuilders.get(BASE_BOOK_URL +"all");
//        Perform
        mockMvc.perform(getAllAuthorsUrl)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());

        mockMvc.perform(getAllGenreUrl)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());

        mockMvc.perform(getAllBooksUrl)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());
    }


//    Get Entities By Id
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetOneEntityByIdSuccessful() throws Exception {
//        Samples
//        Urls
        RequestBuilder getBookByIdUrl = MockMvcRequestBuilders.get(BASE_BOOK_URL +"1");
        RequestBuilder getAuthorByIdUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"1");
        RequestBuilder getGenreByIdUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"1");
//        Perform

        mockMvc.perform(getBookByIdUrl)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title1"))
                .andExpect(jsonPath("$.description").value("Description1"))
                .andExpect(jsonPath("$.price").value("50.0"))
                .andExpect(jsonPath("$.quantityInStock").value("5"))
                .andExpect(jsonPath("$.authorEntity.name").value("AuthorX"))
                .andExpect(jsonPath("$.authorEntity.country").value("England"))
                .andExpect(jsonPath("$.genreEntities.[0].name").value("Adventure"))
                .andDo(print());

        mockMvc.perform(getAuthorByIdUrl)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("AuthorX"))
                .andExpect(jsonPath("$.country").value("England"))
                .andDo(print());


        mockMvc.perform(getGenreByIdUrl)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Adventure"))
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetOneEntityUnsuccessful() throws Exception {
//        Samples
//        Urls
        RequestBuilder getBookByIdUrl = MockMvcRequestBuilders.get(BASE_BOOK_URL +"4");
        RequestBuilder getAuthorByIdUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"error");
        RequestBuilder getGenreByIdUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"4");
//        Perform
        mockMvc.perform(getBookByIdUrl)
                .andExpect(status().isNotFound())
                .andDo(print());

        mockMvc.perform(getAuthorByIdUrl)
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(getGenreByIdUrl)
                .andExpect(status().isNotFound())
                .andDo(print());
    }

//    Get Entities by Name or Title
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetEntitiesByNameOrTitleSuccessful() throws Exception {
//        Samples
//        Urls
        RequestBuilder getBookByTitleUrl = MockMvcRequestBuilders.get(BASE_BOOK_URL +"search/title")
                .queryParam("title","Title2");
        RequestBuilder getAuthorByNameUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"search/name")
                .queryParam("name","AuthorY");
        RequestBuilder getGenreByNameUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"search/name")
                .queryParam("name","Detective");
//        Perform
        mockMvc.perform(getBookByTitleUrl)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].title").value("Title2"))
                .andExpect(jsonPath("$.[0].description").value("Description2"))
                .andExpect(jsonPath("$.[0].price").value("100.0"))
                .andExpect(jsonPath("$.[0].quantityInStock").value("10"))
                .andExpect(jsonPath("$.[0].authorEntity.name").value("AuthorX"))
                .andExpect(jsonPath("$.[0].authorEntity.country").value("England"))
                .andExpect(jsonPath("$.[0].genreEntities.[0].name").value("Adventure"))
                .andDo(print());

        mockMvc.perform(getAuthorByNameUrl)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].name").value("AuthorY"))
                .andExpect(jsonPath("$.[0].country").value("Italy"))
                .andDo(print());

        mockMvc.perform(getGenreByNameUrl)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].name").value("Detective"))
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetEntitiesByNameOrTitleUnsuccessful() throws Exception{
//        Samples
//        URLs
        RequestBuilder getBookByTitleUrl = MockMvcRequestBuilders.get(BASE_BOOK_URL +"search/title")
                .queryParam("title","true");
        RequestBuilder getAuthorByNameUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"search/name")
                .queryParam("name","false");
        RequestBuilder getGenreByNameUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"search/name")
                .queryParam("name","Z");
//        Perform
        mockMvc.perform(getBookByTitleUrl)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());

        mockMvc.perform(getAuthorByNameUrl)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());

        mockMvc.perform(getGenreByNameUrl)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());
    }

//    Get Books by Author or Genre

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetBooksByAuthorOrGenreSuccessful() throws Exception{
//        Samples
//        URLs
        RequestBuilder getBooksByAuthorUrl = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"search-books-by-author/1");
        RequestBuilder getBooksByGenreUrl = MockMvcRequestBuilders.get(BASE_GENRE_URL +"search-books-by-genre/1");
//        Perform
        mockMvc.perform(getBooksByAuthorUrl)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.[0].title").value("Title1"))
                .andExpect(jsonPath("$.[0].description").value("Description1"))
                .andExpect(jsonPath("$.[0].price").value("50.0"))
                .andExpect(jsonPath("$.[0].quantityInStock").value("5"))
                .andExpect(jsonPath("$.[0].publishedDay").value("1850-04-05"))
                .andExpect(jsonPath("$.[1].title").value("Title2"))
                .andExpect(jsonPath("$.[1].description").value("Description2"))
                .andExpect(jsonPath("$.[1].price").value("100.0"))
                .andExpect(jsonPath("$.[1].quantityInStock").value("10"))
                .andExpect(jsonPath("$.[1].publishedDay").value("1850-04-05"))
                .andDo(print());

        
        mockMvc.perform(getBooksByGenreUrl)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.[0].title").value("Title1"))
                .andExpect(jsonPath("$.[0].description").value("Description1"))
                .andExpect(jsonPath("$.[0].price").value("50.0"))
                .andExpect(jsonPath("$.[0].quantityInStock").value("5"))
                .andExpect(jsonPath("$.[0].publishedDay").value("1850-04-05"))
                .andExpect(jsonPath("$.[1].title").value("Title2"))
                .andExpect(jsonPath("$.[1].description").value("Description2"))
                .andExpect(jsonPath("$.[1].price").value("100.0"))
                .andExpect(jsonPath("$.[1].quantityInStock").value("10"))
                .andExpect(jsonPath("$.[1].publishedDay").value("1850-04-05"))
                .andDo(print());

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetBooksByAuthorOrGenreUnsuccessful() throws Exception{
//        Samples
//        URLs
        RequestBuilder getBooksByAuthorUrl1 = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"search-books-by-author/40");
        RequestBuilder getBooksByGenreUrl1 = MockMvcRequestBuilders.get(BASE_GENRE_URL +"search-books-by-genre/40");
        RequestBuilder getBooksByAuthorUrl2 = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"search-books-by-author/true");
        RequestBuilder getBooksByGenreUrl2 = MockMvcRequestBuilders.get(BASE_GENRE_URL +"search-books-by-genre/true");
        RequestBuilder getBooksByAuthorUrl3 = MockMvcRequestBuilders.get(BASE_AUTHOR_URL +"search-books-by-author/3");
        RequestBuilder getBooksByGenreUrl3 = MockMvcRequestBuilders.get(BASE_GENRE_URL +"search-books-by-genre/3");
//        Perform
        mockMvc.perform(getBooksByAuthorUrl1)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(getBooksByAuthorUrl2)
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(getBooksByAuthorUrl3)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());

        mockMvc.perform(getBooksByGenreUrl1)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(getBooksByGenreUrl2)
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(getBooksByGenreUrl3)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(print());

    }

//    Save Entities
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldSaveEntitiesSuccessful() throws Exception {
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        BookEntity book1 = new BookEntity();
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for read");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("0000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);

        GenreEntity genre1 = new GenreEntity();
        genre1.setName("Fantasy");


//        Urls
        RequestBuilder postBook1 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1))
                .queryParam("authorName","AuthorX")
                .queryParam("authorCountry","England")
                .queryParam("genreName","Detective");
        RequestBuilder postBook2 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1))
                .queryParam("authorName","AuthorW")
                .queryParam("authorCountry","England")
                .queryParam("genreName","Fiction");
        RequestBuilder postAuthor = MockMvcRequestBuilders.post("/bookstoreapi/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author1));
        RequestBuilder postGenre = MockMvcRequestBuilders.post("/bookstoreapi/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre1));
//        Perform
        mockMvc.perform(postBook1)
                .andExpect(status().isCreated())
                .andDo(print());
        mockMvc.perform(postBook2)
                .andExpect(status().isCreated())
                .andDo(print());
        mockMvc.perform(postAuthor)
                .andExpect(status().isCreated())
                .andDo(print());
        mockMvc.perform(postGenre)
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldSaveEntitiesUnsuccessful() throws Exception{
//        Samples
        AuthorEntity author1 = new AuthorEntity();//Not Blank
        author1.setName("");
        author1.setCountry("");

        AuthorEntity author2 = new AuthorEntity();//Only letters allowed
        author2.setName("Arthur Conan Doyle%@");
        author2.setCountry("England@@");

        AuthorEntity author3 = new AuthorEntity();//Size
        author3.setName("Fffffffffffffffffffffffffffffffffffffffffffff");
        author3.setCountry("Ffffffffffffffffffffffffffffffffffffffffff");


        BookEntity book1 = new BookEntity();// Not Blank error
        book1.setTitle("");
        book1.setDescription("");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("0000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);

        BookEntity book2 = new BookEntity();// Only letters allowed
        book2.setTitle("Sherlock Holmes%%%%");
        book2.setDescription("A good book for%%%%% read");
        book2.setPublishedDay(LocalDate.parse("1850-04-05"));
        book2.setIsbn("0000000000000");
        book2.setPrice(50.0);
        book2.setQuantityInStock(4);

        BookEntity book3 = new BookEntity();//Only Past date allowed
        book3.setTitle("Sherlock Holmes");
        book3.setDescription("A good book for read");
        book3.setPublishedDay(LocalDate.parse("2024-04-05"));
        book3.setIsbn("0000000000000");
        book3.setPrice(50.0);
        book3.setQuantityInStock(4);

        BookEntity book4 = new BookEntity();// Isbn format incorrect
        book4.setTitle("Sherlock Holmes");
        book4.setDescription("A good book for read");
        book4.setPublishedDay(LocalDate.parse("1850-04-05"));
        book4.setIsbn("0");
        book4.setPrice(50.0);
        book4.setQuantityInStock(4);

        BookEntity book5 = new BookEntity();// Stock digit limit
        book5.setTitle("Sherlock Holmes");
        book5.setDescription("A good book for read");
        book5.setPublishedDay(LocalDate.parse("1850-04-05"));
        book5.setIsbn("0000000000000");
        book5.setPrice(50.0);
        book5.setQuantityInStock(400000000);

        BookEntity book6 = new BookEntity();// Validation params between entities
        book6.setTitle("Sherlock Holmes");
        book6.setDescription("A good book for read");
        book6.setPublishedDay(LocalDate.parse("1850-04-05"));
        book6.setIsbn("0000000000000");
        book6.setPrice(50.0);
        book6.setQuantityInStock(4);



        GenreEntity genre1 = new GenreEntity();// Not blank
        genre1.setName("");

        GenreEntity genre2 = new GenreEntity();//Only letter allowed
        genre2.setName("Fantasy@$1");

        GenreEntity genre3 = new GenreEntity();//Size
        genre3.setName("Fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
//        Urls

//        books
        RequestBuilder postBook1 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1))
                .queryParam("authorName","Arthur Conan Doyle")
                .queryParam("authorCountry","England")
                .queryParam("genreName","Fiction");
        RequestBuilder postBook2 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book2))
                .queryParam("authorName","Jules Verne")
                .queryParam("authorCountry","France")
                .queryParam("genreName","Fiction");
        RequestBuilder postBook3 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book3))
                .queryParam("authorName","AuthorN")
                .queryParam("authorCountry","Italy")
                .queryParam("genreName","Detective");
        RequestBuilder postBook4 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book4))
                .queryParam("authorName","AuthorN")
                .queryParam("authorCountry","Italy")
                .queryParam("genreName","Detective");
        RequestBuilder postBook5 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book5))
                .queryParam("authorName","AuthorN")
                .queryParam("authorCountry","Italy")
                .queryParam("genreName","Detective");
        RequestBuilder postBook6 = MockMvcRequestBuilders.post("/bookstoreapi/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book6))
                .queryParam("authorName","")
                .queryParam("authorCountry","")
                .queryParam("genreName","");
//        authors
        RequestBuilder postAuthor1 = MockMvcRequestBuilders.post("/bookstoreapi/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author1));
        RequestBuilder postAuthor2 = MockMvcRequestBuilders.post("/bookstoreapi/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author2));
        RequestBuilder postAuthor3 = MockMvcRequestBuilders.post("/bookstoreapi/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author3));
//        genre
        RequestBuilder postGenre1 = MockMvcRequestBuilders.post("/bookstoreapi/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre1));
        RequestBuilder postGenre2 = MockMvcRequestBuilders.post("/bookstoreapi/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre2));
        RequestBuilder postGenre3 = MockMvcRequestBuilders.post("/bookstoreapi/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre3));
//        Perform

        //books perform
        mockMvc.perform(postBook1)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isMap())
                .andDo(print());
        mockMvc.perform(postBook2)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postBook3)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postBook4)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postBook5)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postBook6)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        //author perform
        mockMvc.perform(postAuthor1)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postAuthor2)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postAuthor3)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        //genre perform
        mockMvc.perform(postGenre1)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postGenre2)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(postGenre3)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


//  Upgrade Entities
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void UpgradeEntitiesSuccessful() throws Exception {
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for read");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("0000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);

        BookEntity book2 = new BookEntity();
        book2.setId(2L);
        book2.setTitle("Title4");
        book2.setDescription("A good book for read");
        book2.setPublishedDay(LocalDate.parse("1850-04-05"));
        book2.setIsbn("0000000000000");
        book2.setPrice(50.0);
        book2.setQuantityInStock(4);

        GenreEntity genre1 = new GenreEntity();
        genre1.setName("Fiction");
        genre1.setId(1L);
//        Urls
        RequestBuilder putBook1 = MockMvcRequestBuilders.put(BASE_BOOK_URL+"1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1))
                .queryParam("authorName","AuthorX")
                .queryParam("authorCountry","England")
                .queryParam("genreName","NewGenre");
        RequestBuilder putBook2 = MockMvcRequestBuilders.put(BASE_BOOK_URL+"2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book2))
                .queryParam("authorName","AuthorNew")
                .queryParam("authorCountry","England")
                .queryParam("genreName","Science");
        RequestBuilder putAuthor = MockMvcRequestBuilders.put(BASE_AUTHOR_URL+"1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author1));
        RequestBuilder putGenre = MockMvcRequestBuilders.put(BASE_GENRE_URL+"1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre1));
//        Perform
        mockMvc.perform(putBook1)
                .andExpect(status().isAccepted())
                .andDo(print());
        mockMvc.perform(putBook2)
                .andExpect(status().isAccepted())
                .andDo(print());
        mockMvc.perform(putAuthor)
                .andExpect(status().isAccepted())
                .andDo(print());
        mockMvc.perform(putGenre)
                .andExpect(status().isAccepted())
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void UpgradeEntitiesUnsuccessful() throws Exception {
//        Samples
        AuthorEntity author1 = new AuthorEntity();
        author1.setId(1L);
        author1.setName("Arthur Conan Doyle");
        author1.setCountry("England");

        BookEntity book1 = new BookEntity();
        book1.setId(1L);
        book1.setTitle("Sherlock Holmes");
        book1.setDescription("A good book for read");
        book1.setPublishedDay(LocalDate.parse("1850-04-05"));
        book1.setIsbn("0000000000000");
        book1.setPrice(50.0);
        book1.setQuantityInStock(4);

        BookEntity book2 = new BookEntity();
        book2.setId(2L);
        book2.setTitle("Title4");
        book2.setDescription("A good book for read");
        book2.setPublishedDay(LocalDate.parse("1850-04-05"));
        book2.setIsbn("0000000000000");
        book2.setPrice(50.0);
        book2.setQuantityInStock(4);

        GenreEntity genre1 = new GenreEntity();
        genre1.setName("Fiction");
        genre1.setId(1L);
//        Urls
        RequestBuilder putBook1 = MockMvcRequestBuilders.put(BASE_BOOK_URL+"10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1))
                .queryParam("authorName","AuthorX")
                .queryParam("authorCountry","England")
                .queryParam("genreName","NewGenre");
        RequestBuilder putBook2 = MockMvcRequestBuilders.put(BASE_BOOK_URL+"10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book2))
                .queryParam("authorName","AuthorNew")
                .queryParam("authorCountry","England")
                .queryParam("genreName","Science");
        RequestBuilder putAuthor = MockMvcRequestBuilders.put(BASE_AUTHOR_URL+"10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author1));
        RequestBuilder putGenre = MockMvcRequestBuilders.put(BASE_GENRE_URL+"10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genre1));
//        Perform
        mockMvc.perform(putBook1)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(putBook2)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(putAuthor)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(putGenre)
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void DeletedEntitiesByIdSuccessful() throws Exception {
//        Samples
//        URls
        RequestBuilder deletedBookById = MockMvcRequestBuilders.delete(BASE_BOOK_URL+"1");
        RequestBuilder deletedAuthorById = MockMvcRequestBuilders.delete(BASE_AUTHOR_URL+"1");
        RequestBuilder deletedGenreById = MockMvcRequestBuilders.delete(BASE_GENRE_URL+"1");
//        Perform
        mockMvc.perform(deletedBookById)
                .andExpect(status().isAccepted())
                .andDo(print());
        mockMvc.perform(deletedAuthorById)
                .andExpect(status().isAccepted())
                .andDo(print());
        mockMvc.perform(deletedGenreById)
                .andExpect(status().isAccepted())
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void DeletedEntitiesByIdUnsuccessful() throws Exception {
//        Samples
//        URls
        RequestBuilder deletedBookById = MockMvcRequestBuilders.delete(BASE_BOOK_URL+"10");
        RequestBuilder deletedAuthorById = MockMvcRequestBuilders.delete(BASE_AUTHOR_URL+"10");
        RequestBuilder deletedGenreById = MockMvcRequestBuilders.delete(BASE_GENRE_URL+"10");
//        Perform
        mockMvc.perform(deletedBookById)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(deletedAuthorById)
                .andExpect(status().isNotFound())
                .andDo(print());
        mockMvc.perform(deletedGenreById)
                .andExpect(status().isNotFound())
                .andDo(print());
    }



}
