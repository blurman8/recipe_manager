
package com.byborn.recipe.service;
import com.byborn.recipe.repository.PredictcostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.byborn.recipe.model.PredictcostEntity;
import org.springframework.stereotype.Service;

 
/**
 *
 * @author mana
 */
@Service
public class PredictcostService {
    @Autowired
    PredictcostRepository repository;
     
    public List<PredictcostEntity> getAllPredictcosts()
    {
        List<PredictcostEntity> result = (List<PredictcostEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<PredictcostEntity>();
        }
    }
     
    public PredictcostEntity getPredictcostById(Long id) throws RecordNotFoundException 
    {
        Optional<PredictcostEntity> predictcost = repository.findById(id);
         
        if(predictcost.isPresent()) {
            return predictcost.get();
        } else {
            throw new RecordNotFoundException("No Predictcost record exist for given id");
        }
    }
     
    public PredictcostEntity createOrUpdatePredictcost(PredictcostEntity entity) 
    {
        if(entity.getId() == null)
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        { 
            Optional<PredictcostEntity> myentity = repository.findById(entity.getId());
             
            if(myentity.isPresent()) 
            {
                PredictcostEntity newEntity = myentity.get();
                newEntity.setRid(entity.getRid());
                newEntity.setTotal(entity.getTotal()); 
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deletePredictcostById(Long id) throws RecordNotFoundException 
    {
        Optional<PredictcostEntity> myentity = repository.findById(id);
         
        if(myentity.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Predictcost record exist for given id");
        }
    } 
}
