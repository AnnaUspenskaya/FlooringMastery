
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.OrderDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface FlooringMasteryServiceLayer {

    //CRUD
    
    OrderDTO addOrder(OrderDTO order) throws FlooringMasteryPersistenceException,FlooringMasteryDataValidationException;

    List<OrderDTO> getAllOrders(LocalDate ld) throws FlooringMasteryPersistenceException;
    
    OrderDTO getOrder(LocalDate ld, int orderN) throws FlooringMasteryPersistenceException;
    
    OrderDTO editOrder(int orderN, LocalDate ld, OrderDTO order) throws FlooringMasteryPersistenceException;
    
    void removeOrder(int orderN, LocalDate ld) throws FlooringMasteryPersistenceException; 
     
    void saveWork() throws FlooringMasteryPersistenceException;   
    
    LocalDate setDate ();
            
    LocalDate getDate(String date);
   
}
