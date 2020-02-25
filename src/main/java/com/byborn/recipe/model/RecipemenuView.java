package com.byborn.recipe.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
    
 
/**
 *
 * @author mana
 */
@Entity
public class RecipemenuView {

    @Id
    @Column(name = "ID")
    private Long id;
    private Long rid;
    private Long inid;
    private Double total;
    private Long uid;
    private String namerecipe;
    private String nameingredient;
    private String nameunit;
 

    public Long getId() {
        return id;
    }

    public void setId(Long in_id) {
        id = in_id;
    }
    
    public Long getRid() {
        return rid;
    }

    public void setRid(Long in_rid) {
        rid = in_rid;
    }

    public Long getInid() {
        return inid;
    }

    public void setInid(Long in_Inid) {
        inid = in_Inid;
    }
    
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double in_total) {
        total = in_total;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long in_uid) {
        uid = in_uid;
    }    

    public String getNamerecipe() {
        return namerecipe;
    }

    public void setNamerecipe(String in_namerecipe) {
        namerecipe = in_namerecipe;
    } 

    public String getNameingredient() {
        return nameingredient;
    }

    public void setNameingredient(String in_nameingredient) {
        nameingredient = in_nameingredient;
    } 

   public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String in_nameunit) {
        nameunit = in_nameunit;
    }  
 
}
