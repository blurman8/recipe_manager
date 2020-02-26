package com.byborn.recipe.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
 
/**
 *
 * @author mana
 */
@Table(name = "recipe")
@Entity
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "Namerecipe")
    @Length(min = 5, message = "*Your recipe name must have at least 5 characters")
    //@NotEmpty(message = "*Please provide a recipe name")
    private String namerecipe;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "Description")
    @Length(min = 5, message = "*Your Description must have at least 5 characters")
    //@NotEmpty(message = "*Please provide a Description")
    private String description;

    public RecipeEntity () {
        
    } 
    public RecipeEntity ( Long in_id, String in_namerecipe, String in_description ) {
        id = in_id;
        namerecipe = in_namerecipe;
        description = in_description;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long in_id) {
        id = in_id;
    }

    public String getNamerecipe() {
        return namerecipe;
    }

    public void setNamerecipe(String in_namerecipe) {
        namerecipe = in_namerecipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String in_description) {
        description = in_description;
    }

}
