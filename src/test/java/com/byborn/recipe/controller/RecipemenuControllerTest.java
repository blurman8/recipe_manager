package com.byborn.recipe.controller;

import com.byborn.recipe.model.RecipemenuEntity;
import com.byborn.recipe.service.RecipemenuService;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class RecipemenuControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RecipemenuService recipemenuService;

    @Autowired
    WebApplicationContext wac; // cached
    
    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
    }

    @Test
    public void RecipemenuAdd() throws Exception {

        mockMvc.perform(get("/recipemenu/add/1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("value=\"\"")));
        List<RecipemenuEntity> recipemenu_all = recipemenuService.getAllRecipemenus();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/recipemenu/createRecipemenu")
            .param("id", "")
            .param("rid", "1")
            .param("inid", "5")
            .param("total", "60.0")
            .param("uid", "1");
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/recipe/edit/1"));

        recipemenu_all = recipemenuService.getAllRecipemenus();
        recipemenuService.deleteRecipemenuById(recipemenu_all.get(recipemenu_all.size() -1).getId() );

    }
    @Test
    public void RecipemenuEdit() throws Exception {

        mockMvc.perform(get("/recipemenu/edit/2")).andDo(print()).andExpect(content().string(containsString("3.0")));

         
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/recipemenu/createRecipemenu")
            //.contentType("application/x-www-form-urlencoded")  ( 2, 1, 2,  3, 1),
            .param("id", "2")
            .param("rid", "1")
            .param("inid", "2")
            .param("total", "3.5")
            .param("uid", "1");
 
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/recipe/edit/1"));

        mockMvc.perform(get("/recipemenu/edit/2")).andDo(print()).andExpect(content().string(containsString("3.5")));
        
    }
}
