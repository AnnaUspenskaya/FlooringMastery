
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dao.Mappers.ProductMapper;
import com.mycompany.flooringmastery.dto.ProductDTO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Anna
 */
public class ProductDAOFileImpl implements ProductDAO{
   public static final String PRODUCTS_FILE ="data/Products.txt";
   public static final String DELIMITER =",";
   private Map<String, ProductDTO> products = new HashMap<>();
    @Override
    public ProductDTO getProduct(String product) throws FlooringMasteryPersistenceException {
loadProduct();
return products.get(product);
    }
    private void loadProduct() throws FlooringMasteryPersistenceException{
        Scanner scanner;
        try {
            scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(this.PRODUCTS_FILE)));
            String row ="";
            while(scanner.hasNextLine()){
                row=scanner.nextLine();
                ProductDTO p = ProductMapper.toProduct(row);
                this.products.put(p.getProduct(), p);
            }
}catch(FileNotFoundException e) {
	        throw new FlooringMasteryPersistenceException(
	                "Could not load data into memory.", e);
}
	    scanner.close();
        }
    }
    

