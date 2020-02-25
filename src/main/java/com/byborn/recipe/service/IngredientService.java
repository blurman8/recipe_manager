
package com.byborn.recipe.service;
import com.byborn.recipe.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.byborn.recipe.model.IngredientEntity;
import org.springframework.stereotype.Service;

 
/**
 *
 * @author mana
 */
@Service
public class IngredientService {
    @Autowired
    IngredientRepository repository;
     
    public List<IngredientEntity> getAllIngredients()
    {
        List<IngredientEntity> result = (List<IngredientEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<IngredientEntity>();
        }
    }
     
    public IngredientEntity getIngredientById(Long id) throws RecordNotFoundException 
    {
        Optional<IngredientEntity> ingredient = repository.findById(id);
         
        if(ingredient.isPresent()) {
            return ingredient.get();
        } else {
            throw new RecordNotFoundException("No Ingredient record exist for given id");
        }
    }
     
    public IngredientEntity createOrUpdateIngredient(IngredientEntity entity) 
    {
        if(entity.getId() == null)
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        { 
            Optional<IngredientEntity> myentity = repository.findById(entity.getId());
             
            if(myentity.isPresent()) 
            {
                IngredientEntity newEntity = myentity.get();
                newEntity.setNameingredient(entity.getNameingredient());
                newEntity.setDescription(entity.getDescription());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteIngredientById(Long id) throws RecordNotFoundException 
    {
        Optional<IngredientEntity> myentity = repository.findById(id);
         
        if(myentity.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Ingredient record exist for given id");
        }
    } 
}
