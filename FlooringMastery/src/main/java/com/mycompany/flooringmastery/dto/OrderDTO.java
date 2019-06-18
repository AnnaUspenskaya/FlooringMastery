package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Anna
 */
public class OrderDTO {

    private int orderN;
    private String customer;
    private String state;
    private BigDecimal tax;
    private String product;
    private BigDecimal area;
    private BigDecimal cost;
    private BigDecimal labor;
    private BigDecimal costTotal;
    private BigDecimal laborTotal;
    private BigDecimal taxTotal;
    private BigDecimal total;
    private LocalDate ld;

    public OrderDTO(int orderN) {
        this.orderN = orderN;
    }

    public int getOrderN() {
        return orderN;
    }

    public void setOrderN(int orderN) {
        this.orderN = orderN;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getLd() {
        return ld;
    }

    public void setLd(LocalDate ld) {
        this.ld = ld;
    }
}
