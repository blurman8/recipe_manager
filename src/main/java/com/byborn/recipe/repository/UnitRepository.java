package com.byborn.recipe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.byborn.recipe.model.UnitEntity;
/**
 *
 * @author mana
 */
@Repository
public interface UnitRepository extends CrudRepository<UnitEntity, Long> {
}
