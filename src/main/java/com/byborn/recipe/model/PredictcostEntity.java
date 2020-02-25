package com.byborn.recipe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author mana
 */
@Table(name = "predictcost")
@Entity
public class PredictcostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RID")
    private Long rid;

    @Column(name = "Total")
    private Double total;
 

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RID", referencedColumnName = "ID", nullable = true, insertable = false, updatable = false)
    private RecipeEntity recipe;

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double in_total) {
        total = in_total;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity in_recipe) {
        recipe = in_recipe;
    }

}
