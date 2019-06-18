package com.mycompany.flooringmastery.dao.Mappers;

import com.mycompany.flooringmastery.dto.ProductDTO;
import java.math.BigDecimal;

/**
 *
 * @author Anna
 */
public class ProductMapper {

    public static ProductDTO toProduct(String row) {
        String[] fields = row.split(",");
        ProductDTO product = new ProductDTO(fields[0]);
        product.setCost(new BigDecimal(fields[1]));
        product.setLabor(new BigDecimal(fields[2]));
        return product;
    }

    public static String toStringCSV(ProductDTO product) {
        return product.getProduct() + ","
                + product.getCost() + ","
                + product.getLabor();
    }
}
