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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookStoreApiApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void testing() throws Exception {
        RequestBuilder getBuilder1 = MockMvcRequestBuilders.get("/bookstoreapi/author/all");
        mockMvc.perform(getBuilder1)
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        RequestBuilder getBuilder2 = MockMvcRequestBuilders.get("/bookstoreapi/genre/all");
        mockMvc.perform(getBuilder2)
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        RequestBuilder getBuilder3 = MockMvcRequestBuilders.get("/bookstoreapi/book/all");
        mockMvc.perform(getBuilder3)
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "/sql-scripts.sql")
    public void testing1() throws Exception {
        RequestBuilder getBuilder1 = MockMvcRequestBuilders.get("/bookstoreapi/author/all");
        mockMvc.perform(getBuilder1)
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}
