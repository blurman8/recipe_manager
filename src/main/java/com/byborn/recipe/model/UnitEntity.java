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
@Table(name = "unit")
@Entity
public class UnitEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Nameunit")
    private String name;

    @Column(name = "Total1")
    private Double total1;

    @Column(name = "Total2")
    private Double total2;

    @Column(name = "UID2")
    private Long UID2;

 

    public Long getId() {
        return id;
    }

    public void setId(Long in_id) {
        id = in_id;
    }

    public String getNameunit() {
        return name;
    }

    public void setNameunit(String in_name) {
        name = in_name;
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

 

}
