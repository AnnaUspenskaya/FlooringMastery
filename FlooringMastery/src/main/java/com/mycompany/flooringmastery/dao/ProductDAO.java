
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.ProductDTO;

/**
 *
 * @author Anna
 */
public interface ProductDAO {
    ProductDTO getProduct(String product) throws FlooringMasteryPersistenceException;
}
