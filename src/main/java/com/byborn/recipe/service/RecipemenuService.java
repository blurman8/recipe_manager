
package com.byborn.recipe.service;
import com.byborn.recipe.repository.RecipemenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.byborn.recipe.model.RecipemenuEntity;
import org.springframework.stereotype.Service;

 
/**
 *
 * @author mana
 */
@Service
public class RecipemenuService {
    @Autowired
    RecipemenuRepository repository;
     
    public List<RecipemenuEntity> getAllRecipemenus()
    {
        List<RecipemenuEntity> result = (List<RecipemenuEntity>) repository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<RecipemenuEntity>();
        }
    }
     
    public RecipemenuEntity getRecipemenuById(Long id) throws RecordNotFoundException 
    {
        Optional<RecipemenuEntity> recipemenu = repository.findById(id);
         
        if(recipemenu.isPresent()) {
            return recipemenu.get();
        } else {
            throw new RecordNotFoundException("No Recipemenu record exist for given id");
        }
    }
     
    
    public List<RecipemenuEntity> getRecipemenuByRid(Long rid) throws RecordNotFoundException 
    {
        List<RecipemenuEntity> recipemenu = (List<RecipemenuEntity>) repository.findByRid(rid);
         
        if( recipemenu.size() > 0) {
            return recipemenu;
        } else {
            return new ArrayList<RecipemenuEntity>();
        }
    }
    
    
        
    public RecipemenuEntity createOrUpdateRecipemenu(RecipemenuEntity entity) 
    {
        if(entity.getId() == null)
        { 
            RecipemenuEntity newEntity = new RecipemenuEntity();
            newEntity.setRid(entity.getRid());
            newEntity.setInid(entity.getInid());
            newEntity.setTotal(entity.getTotal());
            newEntity.setUid(entity.getUid());
            try {
            entity = repository.save(newEntity);
            } catch (Exception e) {
                System.out.println(e);
            }
            return entity;
        } 
        else
        { 
            Optional<RecipemenuEntity> myentity = repository.findById(entity.getId());
             
            if(myentity.isPresent()) 
            {
                RecipemenuEntity newEntity = myentity.get();
                newEntity.setRid(entity.getRid());
                newEntity.setInid(entity.getInid());
                newEntity.setTotal(entity.getTotal());
                newEntity.setUid(entity.getUid());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteRecipemenuById(Long id) throws RecordNotFoundException 
    {
        Optional<RecipemenuEntity> myentity = repository.findById(id);
         
        if(myentity.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Recipemenu record exist for given id");
        }
    } 
}
