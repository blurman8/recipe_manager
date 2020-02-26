package com.byborn.recipe.service;

import com.byborn.recipe.model.RecipeEntity;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class RecipeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    WebApplicationContext wac; // cached
    
    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
    }

    @Test
    public void RecipeAdd() throws Exception {
        mockMvc.perform(get("/recipe")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("sandwich")));
        mockMvc.perform(get("/recipe/add")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("value=\"\"")));

        //System.out.println("org result : " +recipeService.getAllRecipes().size());
 
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/recipe/createRecipe")
            //.contentType("application/x-www-form-urlencoded")
            .param("id", "")
            .param("Namerecipe", "NewRecipe")
            .param("Description", "NewRecipe");
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/recipe/edit/3"));

        List<RecipeEntity> recipe_all = recipeService.getAllRecipes();
        recipeService.deleteRecipeById(recipe_all.get(recipe_all.size() -1).getId() );
            assertEquals(2, recipeService.getAllRecipes().size());

    }
    @Test
    public void RecipeEdit() throws Exception {
    
        
        mockMvc.perform(get("/recipe/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("sandwich")));
        mockMvc.perform(get("/recipe/edit/2")).andDo(print()).andExpect(content().string(containsString("egg salad")));
        mockMvc.perform(get("/recipe/edit/2")).andDo(print()).andExpect(content().string(containsString("Tomato")));

         
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/recipe/createRecipe")
            //.contentType("application/x-www-form-urlencoded")
            .param("id", "2")
            .param("Namerecipe", "NewRecipe")
            .param("Description", "Description");
 
        mockMvc.perform(builder)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(redirectedUrl("/recipe/edit/2"));

        mockMvc.perform(get("/recipe/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("NewRecipe")));
        assertEquals(2, recipeService.getAllRecipes().size());
        
    }
}
