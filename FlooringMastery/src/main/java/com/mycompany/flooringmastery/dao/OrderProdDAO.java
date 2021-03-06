package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.OrderDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Anna
 */
public interface OrderProdDAO {

    //CRUD
//Create
    OrderDTO addOrder(LocalDate ld, OrderDTO order) throws FlooringMasteryPersistenceException;

//Read
    List<OrderDTO> getAllOrders() throws FlooringMasteryPersistenceException;

    OrderDTO getOrder(int orderN, LocalDate ld) throws FlooringMasteryPersistenceException;
//Update

    void editOrder(int orderN, LocalDate ld, OrderDTO order) throws FlooringMasteryPersistenceException;

//Delete
    void removeOrder(int orderN, LocalDate ld) throws FlooringMasteryPersistenceException;

    void saveWork(LocalDate ld) throws FlooringMasteryPersistenceException;
}
