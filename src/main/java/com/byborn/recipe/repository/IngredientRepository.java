package com.byborn.recipe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.byborn.recipe.model.IngredientEntity;
/**
 *
 * @author mana
 */
@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, Long> {
}
