package com.byborn.recipe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.FetchType;

import javax.validation.constraints.NotNull;

/**
 *
 * @author mana
 */
@Table(name = "recipemenu")
@SecondaryTable(name = "ingredient")
@SecondaryTable(name = "unit")
@Entity
public class RecipemenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "Recipe is mandatory")
    @Column(name = "RID")
    private Long rid;

    @NotNull(message = "Ingredient is mandatory")
    @Column(name = "INID")
    private Long inid;

    @NotNull(message = "Total is mandatory")
    @Column(name = "Total")
    private Double total;

    @NotNull(message = "Unit is mandatory")
    @Column(name = "UID")
    private Long uid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INID", referencedColumnName = "ID", nullable = true, insertable = false, updatable = false)
    private IngredientEntity ingredient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RID", referencedColumnName = "ID", nullable = true, insertable = false, updatable = false)
    private RecipeEntity recipe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UID", referencedColumnName = "ID", nullable = true, insertable = false, updatable = false)
    private UnitEntity unit;

    public RecipemenuEntity() {
    }
    
    public RecipemenuEntity(Long in_id, Long in_rid, Long in_inid, Double in_total, Long in_uid) {
        id = in_id;
        rid = in_rid;
        inid = in_inid;
        total = in_total;
        uid = in_uid;
    }
    
    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientEntity in_ingredient) {
        ingredient = in_ingredient;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity in_unit) {
        unit = in_unit;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity in_recipe) {
        recipe = in_recipe;
    }

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

}
