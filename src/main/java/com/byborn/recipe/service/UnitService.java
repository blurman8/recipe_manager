
package com.byborn.recipe.service;
import com.byborn.recipe.repository.UnitRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import com.byborn.recipe.model.UnitEntity;
import org.springframework.stereotype.Service;

 
/**
 *
 * @author mana
 */
@Service
public class UnitService {
    @Autowired
    UnitRepository repository;
     
    public List<UnitEntity> getAllUnits()
    {
        List<UnitEntity> result = (List<UnitEntity>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<UnitEntity>();
        }
    }
     
    public UnitEntity getUnitById(Long id) throws RecordNotFoundException 
    {
        Optional<UnitEntity> unit = repository.findById(id);
         
        if(unit.isPresent()) {
            return unit.get();
        } else {
            throw new RecordNotFoundException("No Unit record exist for given id");
        }
    }
     
    public UnitEntity createOrUpdateUnit(UnitEntity entity) 
    {
        if(entity.getId() == null)
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        { 
            Optional<UnitEntity> myentity = repository.findById(entity.getId());
             
            if(myentity.isPresent()) 
            {
                UnitEntity newEntity = myentity.get();
                newEntity.setNameunit(entity.getNameunit());
                newEntity.setTotal1(entity.getTotal1());
                newEntity.setUID2(entity.getUID2());
                newEntity.setTotal2(entity.getTotal2());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteUnitById(Long id) throws RecordNotFoundException 
    {
        Optional<UnitEntity> myentity = repository.findById(id);
         
        if(myentity.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Unit record exist for given id");
        }
    } 
}
