package com.byborn.recipe.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
import com.byborn.recipe.model.RecipemenuEntity;
import java.util.List;
import java.util.Optional; 
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
/**
 *
 * @author mana
 */
@Repository
public interface RecipemenuRepository extends CrudRepository<RecipemenuEntity, Long> {
    List<RecipemenuEntity> findByRid(Long RID); 
}
