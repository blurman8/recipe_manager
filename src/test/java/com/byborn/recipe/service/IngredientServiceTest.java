package com.byborn.recipe.service;

import com.byborn.recipe.model.IngredientEntity;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test Ingredient Service")
public class IngredientServiceTest {

    @Autowired
    IngredientService ingredientService;

    @Test
    void FindAllIngredient() {
        assertEquals(10, ingredientService.getAllIngredients().size());
        assertEquals("Pepper", ingredientService.getAllIngredients().get(1).getNameingredient());
        assertEquals("Cheese" , ingredientService.getAllIngredients().get(3).getNameingredient());
    }

    @Test
    void FindIngredient() {
        assertEquals("Cheese", ingredientService.getIngredientById((long) 4).getNameingredient());
    }
/*
    @Test
    void TestAdd_Remove() {
        assertEquals(10, ingredientService.getAllIngredients().size());
        IngredientEntity ingredient1 = new IngredientEntity((long) 12, "grams1", "gram1");
        IngredientEntity ingredient2 = new IngredientEntity((long) 13, "grams2", "gram2");
        ingredientService.createOrUpdateIngredient(ingredient1);
        ingredientService.createOrUpdateIngredient(ingredient2);
        assertEquals(12, ingredientService.getAllIngredients().size());
        ingredientService.deleteIngredientById((long) 12);
        ingredientService.deleteIngredientById((long) 13);
        assertEquals(10, ingredientService.getAllIngredients().size());
        assertEquals("Cheese", ingredientService.getIngredientById((long) 4).getNameingredient());
        assertThrows(RecordNotFoundException.class,() -> ingredientService.getIngredientById((long)11));
    }
*/
}
