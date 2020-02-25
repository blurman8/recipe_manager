package com.byborn.recipe.controller;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byborn.recipe.model.IngredientEntity;
import com.byborn.recipe.service.IngredientService;
import com.byborn.recipe.service.RecordNotFoundException;
/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    
   @Autowired
   IngredientService service;

    
    
    @RequestMapping()
    public String getAllIngredients(Model model) 
    {
        try {
            List<IngredientEntity> list = service.getAllIngredients();
            model.addAttribute("ingredients", list);
            return "list-ingredients";
        } catch (Exception e) {
           return "redirect:/ingredient";
        }
    }
 
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editIngredientById(Model model, @PathVariable("id") Optional<Long> id) 
                            throws RecordNotFoundException 
    {
        try {
            if (id.isPresent()) {
                IngredientEntity entity = service.getIngredientById(id.get());
                model.addAttribute("ingredient", entity);
            } else {
                model.addAttribute("ingredient", new IngredientEntity());
            }
            return "add-edit-ingredient";
        } catch (Exception e) {
           return "redirect:/ingredient";
        }
    }
     
    @RequestMapping(path = "/delete/{id}")
    public String deleteIngredientById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        try {
            service.deleteIngredientById(id);
            return "redirect:/ingredient";
        } catch (Exception e) {
           return "redirect:/ingredient";
        }
    }
 
    @RequestMapping(path = "/createIngredient", method = RequestMethod.POST)
    public String createOrUpdateIngredient(IngredientEntity ingredient) 
    {
        try {
            service.createOrUpdateIngredient(ingredient);
            return "redirect:/ingredient";
        } catch (Exception e) {
           return "redirect:/ingredient";
        }
    }
    
}
