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
@Table(name = "ingredient")
@Entity
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "Nameingredient")
    @Length(min = 5, message = "*Your ingredient name must have at least 5 characters")
    //@NotEmpty(message = "*Please provide a ingredient name")
    private String nameingredient;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "Description")
    @Length(min = 5, message = "*Your Description must have at least 5 characters")
    //@NotEmpty(message = "*Please provide a Description")
    private String description;

    
 
    public Long getId() {
        return id;
    }

    public void setId(Long in_id) {
        id = in_id;
    }

    public String getNameingredient() {
        return nameingredient;
    }

    public void setNameingredient(String in_nameingredient) {
        nameingredient = in_nameingredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String in_description) {
        description = in_description;
    }
    
    

}
