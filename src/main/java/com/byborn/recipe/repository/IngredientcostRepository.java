package com.byborn.recipe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.byborn.recipe.model.IngredientcostEntity;
/**
 *
 * @author mana
 */
@Repository
public interface IngredientcostRepository extends CrudRepository<IngredientcostEntity, Long> {
}
