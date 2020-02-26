package com.byborn.recipe.service;



import com.byborn.recipe.model.UnitEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
 
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test Unit Service")
public class UnitServiceTest {

    @Autowired
    UnitService unitService;

    
    @Test
    void FindAllUnit() {
        assertEquals(5, unitService.getAllUnits().size());
        assertEquals("grams", unitService.getAllUnits().get(0).getNameunit());
        assertEquals("Cups" , unitService.getAllUnits().get(3).getNameunit());
    }

    @Test
    void FindUnit() {
        assertEquals("Cups", unitService.getUnitById((long) 4).getNameunit());
    }

    @Test
    void TestAdd_Remove() {
        assertEquals(5, unitService.getAllUnits().size());
        UnitEntity unit1 = new UnitEntity(7L, "grams1", 1.0, 3L, 0.035274 );
        UnitEntity unit2 = new UnitEntity(8L, "grams2", 1.0, 3L, 0.035274);
        unitService.createOrUpdateUnit(unit1);
        unitService.createOrUpdateUnit(unit2);
        assertEquals(7, unitService.getAllUnits().size());
        unitService.deleteUnitById((long) 7);
        unitService.deleteUnitById((long) 8);
        assertEquals(5, unitService.getAllUnits().size());
        assertEquals("Cups", unitService.getUnitById((long) 4).getNameunit());
        assertThrows(RecordNotFoundException.class,() -> unitService.getUnitById((long)6));
    }

}
