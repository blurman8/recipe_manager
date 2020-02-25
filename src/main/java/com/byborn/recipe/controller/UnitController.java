package com.byborn.recipe.controller;
import com.byborn.recipe.model.RecipemenuEntity;
import com.byborn.recipe.model.RecipemenuView;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byborn.recipe.model.UnitEntity;
import com.byborn.recipe.model.UnitView;
import com.byborn.recipe.service.UnitService;
import com.byborn.recipe.service.RecordNotFoundException;
import java.util.ArrayList;
/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/unit")
public class UnitController {
    
   @Autowired
   UnitService service;

    
    
    @RequestMapping()
    public String getAllUnits(Model model) 
    {
        try {
            List<UnitEntity> list = service.getAllUnits();
            model.addAttribute("units", list);
            List<UnitView> list2 = new ArrayList<>();
            
                for(UnitEntity ue : list){
                    UnitView dummy = new UnitView();
                    dummy.setId( ue.getId() );
                    dummy.setNameunit( ue.getNameunit() );
                    dummy.setTotal1( ue.getTotal1() );
                    dummy.setTotal2(ue.getTotal2() );
                    dummy.setUID2( ue.getUID2() );
                    UnitEntity u2 = service.getUnitById(ue.getUID2());
                    dummy.setNameunit2( u2.getNameunit() );
                    list2.add(dummy);
		}
                model.addAttribute("v_units", list2);
            return "list-units";
        } catch (Exception e) {
           return "redirect:/unit";
        }
    }
 
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUnitById(Model model, @PathVariable("id") Optional<Long> id) 
                            throws RecordNotFoundException 
    {
        try {
            if (id.isPresent()) {
                UnitEntity entity = service.getUnitById(id.get());
                model.addAttribute("unit", entity);
                List<UnitEntity> units = service.getAllUnits();
                model.addAttribute("units", units);
            } else {
                model.addAttribute("unit", new UnitEntity());
                List<UnitEntity> units = service.getAllUnits();
                model.addAttribute("units", units);
            }
            return "add-edit-unit";
        } catch (Exception e) {
           return "redirect:/unit";
        }
    }
     
    @RequestMapping(path = "/delete/{id}")
    public String deleteUnitById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        try {
            service.deleteUnitById(id);
            return "redirect:/unit";
        } catch (Exception e) {
           return "redirect:/unit";
        }
    }
 
    @RequestMapping(path = "/createUnit", method = RequestMethod.POST)
    public String createOrUpdateUnit(UnitEntity unit) 
    {
        try {
            service.createOrUpdateUnit(unit);
            return "redirect:/unit";
        } catch (Exception e) {
           return "redirect:/unit";
        }
    }
    
}
