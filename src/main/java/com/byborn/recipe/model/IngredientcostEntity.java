package com.byborn.recipe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 *
 * @author mana
 */
@Table(name = "ingredientcost")
@Entity
public class IngredientcostEntity {

    @Id
    @Column(name = "INID")
    private Long inid;

    @Column(name = "Nameingredient")
    private String nameingredient;

    @Column(name = "Total")
    private Double total;
    
    @Column(name = "Total2")
    private Double total2;

    @Column(name = "Cost")
    private Double cost;

    @Column(name = "Countitem")
    private Double countitem;

    
    @Column(name = "Allcost")
    private Double allcost;

    @Column(name = "UID")
    private Long uid;

    @Column(name = "IUID")
    private Long iuid;

    @Column(name = "Nameunit")
    private String nameunit;

    public Long getInid() {
        return inid;
    }

    public void setInid(Long in_inid) {
        inid = in_inid;
    }
    
    public String getNameingredient() {
        return nameingredient;
    }

    public void setNameingredient(String in_nameingredient) {
        nameingredient = in_nameingredient;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double in_total) {
        total = in_total;
    }

    public Double getTotal2() {
        return total2;
    }

    public void setTotal2(Double in_total2) {
        total2 = in_total2;
    }
    
    public Double getCountitem() {
        return countitem;
    }

    public void setCountitem(Double in_countitem) {
        countitem = in_countitem;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double in_cost) {
        cost = in_cost;
    }

    public Double getAllcost() {
        return allcost;
    }

    public void setAllcost(Double in_allcost) {
        allcost = in_allcost;
    }

    public Long getIuid() {
        return iuid;
    }

    public void setIuid(Long in_iuid) {
        iuid = in_iuid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long in_uid) {
        uid = in_uid;
    }
    
    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String in_nameunit) {
        nameunit = in_nameunit;
    }

}
