
package com.mycompany.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Anna
 */
public class TaxDTO {

    private BigDecimal tax;
    private BigDecimal taxTotal;
    private String taxByState;

    public TaxDTO(String taxByState) {
        this.taxByState = taxByState;
    }

    public String getTaxByState() {
        return taxByState;
    }

    public void setTaxByState(String taxByState) {
        this.taxByState = taxByState;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

}
