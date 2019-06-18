package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Anna
 */
public class ProductDTO {

    private String product;
    private BigDecimal cost;
    private BigDecimal labor;
    private BigDecimal costTotal;
    private BigDecimal laborTotal;

    public ProductDTO(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getLabor() {
        return labor;
    }

    public void setLabor(BigDecimal labor) {
        this.labor = labor;
    }

    public BigDecimal getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(BigDecimal costTotal) {
        this.costTotal = costTotal;
    }

    public BigDecimal getLaborTotal() {
        return laborTotal;
    }

    public void setLaborTotal(BigDecimal laborTotal) {
        this.laborTotal = laborTotal;
    }

}
