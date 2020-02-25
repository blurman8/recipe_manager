package com.byborn.recipe.controller;

import com.byborn.recipe.model.IngredientEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byborn.recipe.model.RecipeEntity;
import com.byborn.recipe.model.RecipemenuEntity;
import com.byborn.recipe.model.RecipemenuView;
import com.byborn.recipe.model.UnitEntity;
import com.byborn.recipe.service.IngredientService;
import com.byborn.recipe.service.RecipeService;
import com.byborn.recipe.service.RecipemenuService;
import com.byborn.recipe.service.RecordNotFoundException;
import com.byborn.recipe.service.UnitService;
import java.util.ArrayList;

/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService service;

    @Autowired
    RecipemenuService rm_service;

    @Autowired
    IngredientService in_service;

    @Autowired
    UnitService ut_service;

    
    
    @RequestMapping()
    public String getAllRecipes(Model model) {
        try {
            List<RecipeEntity> list = service.getAllRecipes();
            model.addAttribute("recipes", list);
            return "list-recipes";
        } catch (Exception e) {
            return "redirect:/recipe";
        }
    }

    @RequestMapping(path = {"/add"})
    public String editRecipeById(Model model)
            throws RecordNotFoundException {
        try {
            model.addAttribute("recipe", new RecipeEntity());
            return "add-edit-recipe";
        } catch (Exception e) {
            return "redirect:/recipe";
        }
    }

    @RequestMapping(path = {"/edit/{id}"})
    public String edit2RecipeById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        try {
            if (id.isPresent()) {
                RecipeEntity entity = service.getRecipeById(id.get());
                model.addAttribute("recipe", entity);
                List<IngredientEntity> in_entity = in_service.getAllIngredients();
                List<UnitEntity> ut_entity = ut_service.getAllUnits();
                model.addAttribute("ingredients", in_entity);
                model.addAttribute("units", ut_entity);
                List<RecipemenuEntity> list = rm_service.getRecipemenuByRid(entity.getId());
                model.addAttribute("recipemenus", list);
                
                List<RecipemenuView> list2 = new ArrayList<>();
                
                
                 
		for(RecipemenuEntity re : list){
                       
			RecipemenuView dummy = new RecipemenuView();
                        dummy.setId( re.getId() );
                        dummy.setInid( re.getInid() );
                        dummy.setRid( re.getRid() );
                        dummy.setTotal(re.getTotal() );
                        dummy.setUid( re.getUid() );
                        dummy.setNameingredient(re.getIngredient().getNameingredient());
                        dummy.setNamerecipe(re.getRecipe().getNamerecipe());
                        dummy.setNameunit(re.getUnit().getNameunit());
                        list2.add(dummy);
		}
                model.addAttribute("recipemenus", list2);
            }
            return "add-edit-recipe2";
        } catch (Exception e) {
            return "redirect:/recipe";
        }
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteRecipeById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        try {
            service.deleteRecipeById(id);
            return "redirect:/recipe";
        } catch (Exception e) {
            return "redirect:/recipe";
        }
    }

    @RequestMapping(path = "/createRecipe", method = RequestMethod.POST)
    public String createOrUpdateRecipe(RecipeEntity recipe) {
        try {
            service.createOrUpdateRecipe(recipe);
            Long rid = recipe.getId();
            return "redirect:/recipe/edit/" + rid ;
        } catch (Exception e) {
            return "redirect:/recipe/";
        }
    }

}
