
package com.byborn.recipe.service;
import com.byborn.recipe.repository.IngredientcostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.byborn.recipe.model.IngredientcostEntity;
import org.springframework.stereotype.Service;

 
/**
 *
 * @author mana
 */
@Service
public class IngredientcostService {
    @Autowired
    IngredientcostRepository repository;
     
    public List<IngredientcostEntity> getAllIngredientcosts()
    {
        List<IngredientcostEntity> result = (List<IngredientcostEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<IngredientcostEntity>();
        }
    }
     
    public IngredientcostEntity getIngredientcostById(Long id) throws RecordNotFoundException 
    {
        Optional<IngredientcostEntity> ingredientcost = repository.findById(id);
         
        if(ingredientcost.isPresent()) {
            return ingredientcost.get();
        } else {
            throw new RecordNotFoundException("No Ingredientcost record exist for given id");
        }
    }
     
    public IngredientcostEntity createOrUpdateIngredientcost(IngredientcostEntity entity) 
    {
        if(entity.getInid() == null)
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        { 
            Optional<IngredientcostEntity> myentity = repository.findById(entity.getInid());
             
            if(myentity.isPresent()) 
            {
                IngredientcostEntity newEntity = myentity.get();
                newEntity.setNameingredient(entity.getNameingredient());
                newEntity.setTotal(entity.getTotal());
                newEntity.setCost(entity.getCost()); 

                
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteIngredientcostById(Long id) throws RecordNotFoundException 
    {
        Optional<IngredientcostEntity> myentity = repository.findById(id);
         
        if(myentity.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Ingredientcost record exist for given id");
        }
    } 
}
