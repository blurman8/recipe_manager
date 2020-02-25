package com.byborn.recipe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.byborn.recipe.model.RecipeEntity;
/**
 *
 * @author mana
 */
@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    
}
