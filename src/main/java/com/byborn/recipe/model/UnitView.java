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
public class UnitView {

    @Id
    @Column(name = "ID")
    private Long id;
    private String nameunit;
    private Double total1;
    private Double total2;
    private Long UID2;
    private String nameunit2;

    public Long getId() {
        return id;
    }

    public void setId(Long in_id) {
        id = in_id;
    }

    public Double getTotal1() {
        return total1;
    }

    public void setTotal1(Double in_total1) {
        total1 = in_total1;
    }

    public Double getTotal2() {
        return total2;
    }

    public void setTotal2(Double in_total2) {
        total2 = in_total2;
    }

    public Long getUID2() {
        return UID2;
    }

    public void setUID2(Long in_UID2) {
        UID2 = in_UID2;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String in_nameunit) {
        nameunit = in_nameunit;
    }
    
    public String getNameunit2() {
        return nameunit2;
    }

    public void setNameunit2(String in_nameunit2) {
        nameunit2 = in_nameunit2;
    }
}
