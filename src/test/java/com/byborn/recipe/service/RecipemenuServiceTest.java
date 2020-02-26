package com.byborn.recipe.service;

import com.byborn.recipe.model.RecipemenuEntity;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test Recipemenu Service")
public class RecipemenuServiceTest {

    @Autowired
    RecipemenuService recipemenuService;

    @Test
    void FindAllRecipemenu() {
        
        assertEquals(10, recipemenuService.getAllRecipemenus().size());
        assertEquals(7, recipemenuService.getAllRecipemenus().get(6).getId());
        assertEquals(4 , recipemenuService.getAllRecipemenus().get(3).getId());
        assertEquals(6 , recipemenuService.getRecipemenuByRid(1L).size());
        assertEquals(10 , recipemenuService.getRecipemenuByRid(1L).get(5).getId());
        
    }

    @Test
    void FindRecipemenu() {
        assertEquals(1, recipemenuService.getRecipemenuById(3L).getRid());
    }
    
    @Test
    void TestAdd_Remove() {
        assertEquals(10, recipemenuService.getAllRecipemenus().size());
        RecipemenuEntity recipemenu1 = new RecipemenuEntity(12L, 1L, 5L, 60.0, 1L);
        RecipemenuEntity recipemenu2 = new RecipemenuEntity(13L, 1L, 6L, 80.0, 1L);
        recipemenuService.createOrUpdateRecipemenu(recipemenu1);
        recipemenuService.createOrUpdateRecipemenu(recipemenu2);
        assertEquals(12, recipemenuService.getAllRecipemenus().size());
        recipemenuService.deleteRecipemenuById(12L);
        recipemenuService.deleteRecipemenuById(13L);
        assertEquals(10, recipemenuService.getAllRecipemenus().size());
        assertEquals(4 , recipemenuService.getAllRecipemenus().get(3).getId());
        assertThrows(RecordNotFoundException.class,() -> recipemenuService.getRecipemenuById(11L));
    }
}
