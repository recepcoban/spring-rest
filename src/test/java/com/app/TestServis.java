/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;

import com.app.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author rohat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestServis extends ApplicationTests {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    Book test = new Book();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        test.setBookname("testbook");
        test.setAuthor("testauthor");
    }

    @Test
    public void get_book() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void insert_book() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/insert"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                 content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.bookname").value(test.getBookname()))
                .andExpect(jsonPath("$.author").value(test.getAuthor()));
                

    }

    @Test
    public void update_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/update/"+test.getBookname()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                 content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.bookname").value("test"))
                .andExpect(jsonPath("$.author").value("test"));

    }

    @Test
    public void delete_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/delete/" + test.getBookname()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
