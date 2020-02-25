package com.byborn.recipe.controller;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 
/**
 *
 * @author mana
 */
@Controller
@RequestMapping("/")
public class HomeController {
 
    
    @RequestMapping()
    public String Home(Model model) 
    {
        return "home"; 
    }
  
    
}
