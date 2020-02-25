package com.byborn.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byborn.recipe.model.IngredientcostEntity;
import com.byborn.recipe.service.IngredientcostService;
import com.byborn.recipe.service.RecordNotFoundException;

/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/ingredientcost")
public class IngredientcostController {

    @Autowired
    IngredientcostService service;

    @RequestMapping()
    public String getAllIngredientcosts(Model model) {
        try {
            List<IngredientcostEntity> list = service.getAllIngredientcosts();
            model.addAttribute("ingredientcosts", list);
            return "list-ingredientcosts";
        } catch (Exception e) {
            return "redirect:/ingredientcost";
        }
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editIngredientcostById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        try {
            if (id.isPresent()) {
                IngredientcostEntity entity = service.getIngredientcostById(id.get());
                model.addAttribute("ingredientcost", entity);
            } else {
                model.addAttribute("ingredientcost", new IngredientcostEntity());
            }
            return "edit-ingredientcost";
        } catch (Exception e) {
            return "redirect:/ingredientcost";
        }
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteIngredientcostById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        try {
            service.deleteIngredientcostById(id);
            return "redirect:/ingredientcost";
        } catch (Exception e) {
            return "redirect:/ingredientcost";
        }
    }

    @RequestMapping(path = "/createIngredientcost", method = RequestMethod.POST)
    public String createOrUpdateIngredientcost(IngredientcostEntity ingredientcost) {
        try {
            service.createOrUpdateIngredientcost(ingredientcost);
            return "redirect:/ingredientcost";
        } catch (Exception e) {
            return "redirect:/ingredientcost";
        }
    }

}
