package com.byborn.recipe.controller;
import com.byborn.recipe.model.IngredientEntity;
import com.byborn.recipe.model.IngredientcostEntity;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.byborn.recipe.model.PredictcostEntity;
import com.byborn.recipe.model.PredictcostView;
import com.byborn.recipe.service.PredictcostService;
import com.byborn.recipe.service.RecordNotFoundException;
import com.byborn.recipe.model.RecipeEntity;
import com.byborn.recipe.model.RecipemenuEntity;
import com.byborn.recipe.model.UnitEntity;
import com.byborn.recipe.service.IngredientService;
import com.byborn.recipe.service.IngredientcostService;
import com.byborn.recipe.service.RecipeService;
import com.byborn.recipe.service.RecipeService;
import com.byborn.recipe.service.RecipemenuService;
import com.byborn.recipe.service.UnitService;
import java.util.ArrayList;

/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/predictcost")
public class PredictcostController {
    
    @Autowired
    PredictcostService service;

    @Autowired
    RecipeService r_service;

    @Autowired
    RecipemenuService rm_service;

    @Autowired
    UnitService u_service;
    
    @Autowired
    IngredientService in_service;

    @Autowired
    IngredientcostService ic_service;

    
    @RequestMapping()
    public String getAllPredictcosts(Model model) 
    {
        
        try {
            List<PredictcostEntity> list = service.getAllPredictcosts();


            List<PredictcostView> list2 = new ArrayList<>();
            for(PredictcostEntity pe : list){
                    PredictcostView dummy = new PredictcostView();
                    dummy.setId( pe.getId() );
                    dummy.setRid( pe.getRid() );
                    dummy.setTotal( pe.getTotal() );
                    dummy.setNamerecipe(pe.getRecipe().getNamerecipe() );
                    list2.add(dummy);
            }
            model.addAttribute("predictcosts", list2);
            List<IngredientcostEntity> list3 =  ic_service.getAllIngredientcosts();
            model.addAttribute("ingredientcosts", list3);
            
            
            
            return "list-predictcosts";
        } catch (Exception e) {
           return "redirect:/predictcost/";
        }
        
    }
 
    
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editPredictcostById(Model model, @PathVariable("id") Optional<Long> id) 
                            throws RecordNotFoundException 
    {
        List<RecipeEntity> r_list = r_service.getAllRecipes();
        model.addAttribute("recipes", r_list);
        
        try {
            if (id.isPresent()) {
                PredictcostEntity entity = service.getPredictcostById(id.get());
                model.addAttribute("predictcost", entity);
            } else {
                model.addAttribute("predictcost", new PredictcostEntity());
            }
            return "add-edit-predictcost";
        } catch (Exception e) {
           return "redirect:/predictcost";
        }
    }
    
    @RequestMapping(path = {"/start"})
    public String Calculation(Model model ) 
                            throws RecordNotFoundException 
    {
        List<PredictcostEntity> predictcosts = service.getAllPredictcosts();
        List<IngredientEntity> ingredients = in_service.getAllIngredients();
        List<IngredientcostEntity> costlist = new ArrayList<>();
        
        List<IngredientcostEntity> list = ic_service.getAllIngredientcosts();
        for( IngredientcostEntity ice : list ) {
            ic_service.deleteIngredientcostById(ice.getInid());
        }
        List<UnitEntity> units = u_service.getAllUnits();
        
        for(IngredientEntity ie : ingredients){
            IngredientcostEntity dummy = new IngredientcostEntity();
            dummy.setInid(ie.getId());
            dummy.setNameingredient(ie.getNameingredient());
            dummy.setTotal((Double)0.0);
            dummy.setTotal2((Double)0.0);
            dummy.setAllcost((Double)0.0);
            dummy.setCost((Double)0.0);
            dummy.setUid(Long.parseLong("0"));
            dummy.setIuid(Long.parseLong("0"));
            dummy.setCountitem((Double)0.0);
            dummy.setNameunit(" ");
            costlist.add(dummy);
        }
        
        for(PredictcostEntity pe : predictcosts) {
            List<RecipemenuEntity> recipemenus = rm_service.getRecipemenuByRid(pe.getRid());
            for(RecipemenuEntity rme : recipemenus ) {
                for(IngredientcostEntity ice : costlist ) {

                    if ( ice.getInid() == rme.getInid()){
                        ice.setTotal( ice.getTotal() + rme.getTotal() * pe.getTotal() );
                        ice.setUid((Long)rme.getUid());
                        ice.setIuid((Long)rme.getUid());
                        for(UnitEntity u : units) {
                            if ( u.getId() == rme.getUid() ) {
                                ice.setNameunit(u.getNameunit());
                            }
                        }
                        ic_service.createOrUpdateIngredientcost(ice);
                    }
                }
            }
        }
        
        list = ic_service.getAllIngredientcosts();
        for(IngredientcostEntity ice : list) {
            if ( ice.getTotal() == 0.0  ) {
                ic_service.deleteIngredientcostById(ice.getInid());
            }
        }
        
        model.addAttribute("ingredientcosts", costlist);

        
        List<PredictcostEntity> list3 = service.getAllPredictcosts();
        List<RecipeEntity> r_list = r_service.getAllRecipes();
        model.addAttribute("recipes", r_list);

        List<PredictcostView> list4 = new ArrayList<>();
        for (PredictcostEntity pe : list3) {
            PredictcostView dummy = new PredictcostView();
            dummy.setId(pe.getId());
            dummy.setRid(pe.getRid());
            dummy.setTotal(pe.getTotal());
            dummy.setNamerecipe(pe.getRecipe().getNamerecipe());
            list4.add(dummy);
        }
        model.addAttribute("predictcosts", list4);
        return "list-predictcosts";
    }
    
    @RequestMapping(path = {"/edit/cost/{id}"})
    public String editIngredientcostById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        try {
            IngredientcostEntity entity = ic_service.getIngredientcostById(id);
            model.addAttribute("ingredientcost", entity);
            List<UnitEntity> units = u_service.getAllUnits();
            model.addAttribute("units", units);
            return "add-edit-ingredientcost";
        } catch (Exception e) {
           return "redirect:/predictcost/";
        }
    }
 
    
    @RequestMapping(path = "/createIngredientcost", method = RequestMethod.POST)
    public String createOrUpdateIngredientcost(IngredientcostEntity ingredientcost) 
    {
        try {
            IngredientcostEntity entity = ic_service.getIngredientcostById(ingredientcost.getInid());
            entity.setUid(ingredientcost.getUid());
            entity.setCost(ingredientcost.getCost());
            entity.setTotal2(ingredientcost.getTotal2());

            List<UnitEntity> units = u_service.getAllUnits();
            for(UnitEntity u : units) {
                if ( u.getId() == entity.getUid() ) {
                    entity.setNameunit(u.getNameunit());
                }
            }
            ic_service.createOrUpdateIngredientcost(entity);
            return "redirect:/predictcost/";
        } catch (Exception e) {
            System.out.println(e);
           return "redirect:/predictcost/";
        }
    }
    
    
     
    @RequestMapping(path = "/delete/{id}")
    public String deletePredictcostById(Model model, @PathVariable("id") Long id) 
                            throws RecordNotFoundException 
    {
        try {
            service.deletePredictcostById(id);
            return "redirect:/predictcost";
        } catch (Exception e) {
           return "redirect:/predictcost";
        }
    }
 
    @RequestMapping(path = "/createPredictcost", method = RequestMethod.POST)
    public String createOrUpdatePredictcost(PredictcostEntity predictcost) 
    {
        try {
            service.createOrUpdatePredictcost(predictcost);
            return "redirect:/predictcost";
        } catch (Exception e) {
           return "redirect:/predictcost";
        }
    } 
            
    @RequestMapping(path = {"/cal/{days}"})
    public String cal_on_day( Model model, @PathVariable("days") Double days )throws RecordNotFoundException  
    {

        try {
            List<IngredientcostEntity> ingredientcosts = ic_service.getAllIngredientcosts();
            
            for(IngredientcostEntity ice : ingredientcosts) {
                if ( ice.getIuid() == ice.getUid() ) {
                        double total = ice.getTotal()  ;
                        double allitems = Math.ceil( total * days / ice.getTotal2()) ;
                        double allcost = allitems * ice.getCost();
                        ice.setCountitem( allitems );
                        ice.setAllcost( allcost );
                } else {
                    List<UnitEntity> units = u_service.getAllUnits();
                    boolean flag_found = false;
                    for ( UnitEntity u : units) {
                        if ( ice.getIuid() == u.getId() && ice.getUid() == u.getUID2() ) {
                            flag_found = true;
                            double total = ice.getTotal() * u.getTotal2() ;
                            double allitems = Math.ceil( total * days / ice.getTotal2() )  ;
                            double allcost = allitems * ice.getCost();
                            ice.setCountitem( allitems );
                            ice.setAllcost( allcost );
                        }
                    }
                    if ( flag_found ){
                    } else {
                        ice.setAllcost( -1.0 );
                    }
                }
                ic_service.createOrUpdateIngredientcost(ice);
            }
              
            List<PredictcostEntity> list = service.getAllPredictcosts();
            List<PredictcostView> list2 = new ArrayList<>();
            for(PredictcostEntity pe : list){
                    PredictcostView dummy = new PredictcostView();
                    dummy.setId( pe.getId() );
                    dummy.setRid( pe.getRid() );
                    dummy.setTotal( pe.getTotal() );
                    dummy.setNamerecipe(pe.getRecipe().getNamerecipe() );
                    list2.add(dummy);
            }
            model.addAttribute("predictcosts", list2);
            List<IngredientcostEntity> list3 =  ic_service.getAllIngredientcosts();
            model.addAttribute("ingredientcosts", list3);
            
            
             
            return "list-predictcosts-result";
        } catch (Exception e) {
            System.out.println(e);
           return "redirect:/predictcost";
        }
    }
}
