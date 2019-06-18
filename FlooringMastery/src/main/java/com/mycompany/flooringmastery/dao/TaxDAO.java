
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.TaxDTO;

/**
 *
 * @author Anna
 */
public interface TaxDAO {
    TaxDTO getTax(String state) throws FlooringMasteryPersistenceException;
}
