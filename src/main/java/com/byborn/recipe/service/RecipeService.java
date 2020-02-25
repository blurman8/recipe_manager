
package com.byborn.recipe.service;
import com.byborn.recipe.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.byborn.recipe.model.RecipeEntity;
import org.springframework.stereotype.Service;

 
/**
 *
 * @author mana
 */
@Service
public class RecipeService {
    @Autowired
    RecipeRepository repository;
     
    public List<RecipeEntity> getAllRecipes()
    {
        List<RecipeEntity> result = (List<RecipeEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<RecipeEntity>();
        }
    }
     
    public RecipeEntity getRecipeById(Long id) throws RecordNotFoundException 
    {
        Optional<RecipeEntity> ingredient = repository.findById(id);
         
        if(ingredient.isPresent()) {
            return ingredient.get();
        } else {
            throw new RecordNotFoundException("No Recipe record exist for given id");
        }
    }
     
    public RecipeEntity createOrUpdateRecipe(RecipeEntity entity) 
    {
        if(entity.getId() == null)
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        { 
            Optional<RecipeEntity> myentity = repository.findById(entity.getId());
             
            if(myentity.isPresent()) 
            {
                RecipeEntity newEntity = myentity.get();
                newEntity.setNamerecipe(entity.getNamerecipe());
                newEntity.setDescription(entity.getDescription());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteRecipeById(Long id) throws RecordNotFoundException 
    {
        Optional<RecipeEntity> myentity = repository.findById(id);
         
        if(myentity.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Recipe record exist for given id");
        }
    } 
}
