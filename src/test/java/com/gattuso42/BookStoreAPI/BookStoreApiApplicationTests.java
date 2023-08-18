package com.gattuso42.BookStoreAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gattuso42.BookStoreAPI.entity.AuthorEntity;
import com.gattuso42.BookStoreAPI.repository.AuthorRepository;
import org.junit.Before;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static net.bytebuddy.matcher.ElementMatchers.isArray;
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
    private static final String BASE_URL_BOOK = "/bookstoreapi/book/";
    private static final String BASE_URL_AUTHOR = "/bookstoreapi/author/";
    private static final String BASE_URL_GENRE = "/bookstoreapi/genre/";




//    Get all Entities
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void shouldGetAllEntitiesSuccessful() throws Exception {
//        Samples
        RequestBuilder getAllAuthorUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"all");
        RequestBuilder getAllGenreUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"all");
        RequestBuilder getAllBookUrl = MockMvcRequestBuilders.get(BASE_URL_BOOK+"all");
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
        RequestBuilder getAllAuthorsUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"all");
        RequestBuilder getAllGenreUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"all");
        RequestBuilder getAllBooksUrl = MockMvcRequestBuilders.get(BASE_URL_BOOK+"all");
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
        RequestBuilder getBookByIdUrl = MockMvcRequestBuilders.get(BASE_URL_BOOK+"1");
        RequestBuilder getAuthorByIdUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"1");
        RequestBuilder getGenreByIdUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"1");
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
        RequestBuilder getBookByIdUrl = MockMvcRequestBuilders.get(BASE_URL_BOOK+"4");
        RequestBuilder getAuthorByIdUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"error");
        RequestBuilder getGenreByIdUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"4");
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
        RequestBuilder getBookByTitleUrl = MockMvcRequestBuilders.get(BASE_URL_BOOK+"search/title")
                .queryParam("title","Title2");
        RequestBuilder getAuthorByNameUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"search/name")
                .queryParam("name","AuthorY");
        RequestBuilder getGenreByNameUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"search/name")
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
        RequestBuilder getBookByTitleUrl = MockMvcRequestBuilders.get(BASE_URL_BOOK+"search/title")
                .queryParam("title","true");
        RequestBuilder getAuthorByNameUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"search/name")
                .queryParam("name","false");
        RequestBuilder getGenreByNameUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"search/name")
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
        RequestBuilder getBooksByAuthorUrl = MockMvcRequestBuilders.get(BASE_URL_AUTHOR+"search-books-by-author/1");
        RequestBuilder getBooksByGenreUrl = MockMvcRequestBuilders.get(BASE_URL_GENRE+"search-books-by-genre/1");
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

}
