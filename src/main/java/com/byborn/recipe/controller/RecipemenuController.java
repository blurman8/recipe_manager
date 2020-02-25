package com.byborn.recipe.controller;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byborn.recipe.model.RecipemenuEntity;
import com.byborn.recipe.model.RecipeEntity;
import com.byborn.recipe.model.IngredientEntity;
import com.byborn.recipe.model.UnitEntity;
import com.byborn.recipe.service.RecipemenuService;
import com.byborn.recipe.service.RecipeService;
import com.byborn.recipe.service.IngredientService;
import com.byborn.recipe.service.UnitService;
import com.byborn.recipe.service.RecordNotFoundException;



/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/recipemenu")
public class RecipemenuController {
    
   @Autowired
   RecipemenuService service;

   @Autowired
   RecipeService r_service;

   @Autowired
   IngredientService in_service;

   @Autowired
   UnitService u_service;


    
    @RequestMapping()
    public String getAllRecipemenus(Model model) 
    {
        try {
            List<RecipemenuEntity> list = service.getAllRecipemenus();
            model.addAttribute("recipemenus", list);
            List<RecipeEntity> r_list = r_service.getAllRecipes();
            List<IngredientEntity> in_list = in_service.getAllIngredients();
            List<UnitEntity> u_list = u_service.getAllUnits();
            model.addAttribute("recipes", r_list);
            model.addAttribute("ingredients", in_list);
            model.addAttribute("units", u_list);
            return "list-recipemenus";
        } catch (Exception e) {
           return "redirect:/recipemenu";
        }
    }
 
    @RequestMapping(path = {"/edit/{id}"})
    public String editRecipemenuById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        try {
             
            RecipemenuEntity entity = service.getRecipemenuById(id);
            model.addAttribute("recipemenu", entity);

            RecipeEntity recipe = r_service.getRecipeById(entity.getRid());
            List<IngredientEntity> in_list = in_service.getAllIngredients();
            List<UnitEntity> u_list = u_service.getAllUnits();
            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredients", in_list);
            model.addAttribute("units", u_list);
            return "add-edit-recipemenu";
        } catch (Exception e) {
           return "redirect:/recipemenu";
        }
    }
    @RequestMapping(path = { "/add/{rid}"})
    public String addRecipemenuByRid(Model model, @PathVariable("rid") Long rid) 
                            throws RecordNotFoundException 
    {
        try {
            RecipemenuEntity newRecipemenu = new RecipemenuEntity();
            newRecipemenu.setRid(rid);
            model.addAttribute("recipemenu", newRecipemenu);
            
            RecipeEntity recipe = r_service.getRecipeById(rid);
            List<IngredientEntity> in_list = in_service.getAllIngredients();
            List<UnitEntity> u_list = u_service.getAllUnits();
            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredients", in_list);
            model.addAttribute("units", u_list);
            model.addAttribute("rid", rid);
            
            return "add-recipemenu";
        } catch (Exception e) {
           return "redirect:/recipemenu";
        }
    } 
    
    @RequestMapping(path = "/delete/{id}")
    public String deleteRecipemenuById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        try {
            service.deleteRecipemenuById(id);
            return "redirect:/recipemenu";
        } catch (Exception e) {
           return "redirect:/recipemenu";
        }
    }
 
    @RequestMapping(path = "/createRecipemenu", method = RequestMethod.POST)
    public String createOrUpdateRecipemenu(RecipemenuEntity recipemenu) 
    {
        try { 
            service.createOrUpdateRecipemenu(recipemenu);
            Long rid = recipemenu.getRid();
            return "redirect:/recipe/edit/" + rid;
        } catch (Exception e) {
            return "redirect:/recipe/";
        }
    }
    
}
