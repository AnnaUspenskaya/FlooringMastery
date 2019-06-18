
package com.mycompany.flooringmastery.dao.Mappers;


import com.mycompany.flooringmastery.dto.TaxDTO;
import java.math.BigDecimal;

/**
 *
 * @author Anna
 */
public class TaxMapper {
        public static TaxDTO toTax(String row){
        
        String[] fields= row.split(",");
        TaxDTO tax= new TaxDTO(fields[0]);
        tax.setTax(new BigDecimal(fields[1]));
        return tax;
    }
    public static String toStringCSV(TaxDTO tax){
        return tax.getTaxByState()+","+
                tax.getTax();
    }
}
