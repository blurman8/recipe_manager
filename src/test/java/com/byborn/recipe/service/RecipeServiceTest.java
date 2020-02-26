package com.byborn.recipe.service;

import com.byborn.recipe.model.RecipeEntity;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test Recipe Service")
public class RecipeServiceTest {

    @Autowired
    RecipeService recipeService;

    @Test
    void FindAllRecipe() {
        assertEquals(2, recipeService.getAllRecipes().size());
    }

    @Test
    void FindRecipe() {
        assertEquals("sandwich", recipeService.getRecipeById(1L).getNamerecipe());
    }

    @Test
    void TestAdd_Remove() {
        assertEquals(2, recipeService.getAllRecipes().size());
        RecipeEntity recipe1 = new RecipeEntity(4L, "menu4", "menu4");
        RecipeEntity recipe2 = new RecipeEntity(5L, "menu5", "menu5");
        recipeService.createOrUpdateRecipe(recipe1);
        recipeService.createOrUpdateRecipe(recipe2);
        assertEquals(4, recipeService.getAllRecipes().size());
        recipeService.deleteRecipeById(4L);
        recipeService.deleteRecipeById(5L);
        assertEquals(2, recipeService.getAllRecipes().size());
        assertEquals("sandwich", recipeService.getAllRecipes().get(0).getNamerecipe());
        assertThrows(RecordNotFoundException.class,() -> recipeService.getRecipeById((long)11));
    }

}
