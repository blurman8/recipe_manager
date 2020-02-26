package com.byborn.recipe.service;

import com.byborn.recipe.model.IngredientEntity;
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
public class IngredientControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    WebApplicationContext wac; // cached
    
    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
    }

    @Test
    public void IngredientAdd() throws Exception {

        mockMvc.perform(get("/ingredient")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Dressing")));
        mockMvc.perform(get("/ingredient/edit")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("value=\"\"")));

        //System.out.println("org result : " +ingredientService.getAllIngredients().size());
 
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ingredient/createIngredient")
            //.contentType("application/x-www-form-urlencoded")
            .param("id", "")
            .param("Nameingredient", "NewIngredient")
            .param("Description", "NewIngredient");
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/ingredient"));

        List<IngredientEntity> ingredient_all = ingredientService.getAllIngredients();
        ingredientService.deleteIngredientById(ingredient_all.get(ingredient_all.size() -1).getId() );

    }
    @Test
    public void IngredientEdit() throws Exception {


        mockMvc.perform(get("/ingredient/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Mayones")));
        mockMvc.perform(get("/ingredient/edit/2")).andDo(print()).andExpect(content().string(containsString("Pepper")));

         
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ingredient/createUnit")
            //.contentType("application/x-www-form-urlencoded")
            .param("id", "2")
            .param("Nameingredient", "NewIngredient")
            .param("Description", "NewIngredient");
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().string(containsString("NewIngredient")));

        mockMvc.perform(get("/ingredient/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("NewIngredient")));

    }
}
