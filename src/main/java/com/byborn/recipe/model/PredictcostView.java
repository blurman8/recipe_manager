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
@Entity
public class PredictcostView {

    @Id
    @Column(name = "ID")
    private Long id;
    private Long rid;
    private Double total;
    private String namerecipe;

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

    public String getNamerecipe() {
        return namerecipe;
    }

    public void setNamerecipe(String in_namerecipe) {
        namerecipe = in_namerecipe;
    }
}
