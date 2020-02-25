package com.byborn.recipe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.byborn.recipe.model.PredictcostEntity;
/**
 *
 * @author mana
 */
@Repository
public interface PredictcostRepository extends CrudRepository<PredictcostEntity, Long> {
}
