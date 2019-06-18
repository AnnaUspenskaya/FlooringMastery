
package com.mycompany.flooringmastery.controller;

import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.OrderDTO;
import com.mycompany.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mycompany.flooringmastery.dao.WrongEntryException;
import com.mycompany.flooringmastery.service.FlooringMasteryDataValidationException;
import com.mycompany.flooringmastery.ui.UserIO;
import com.mycompany.flooringmastery.ui.UserIOConsoleImpl;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

/**
 *
 * @author Anna
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    FlooringMasteryDao dao;
    FlooringMasteryServiceLayer service;
    private UserIO io = new UserIOConsoleImpl();

    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        listOrders();
                        //io.print("DISPLAY ORDERS");
                        break;
                    case 2:
                        addOrder();
                        //io.print("ADD AN ORDER");
                        break;
                    case 3:
                        editOrder();
                        //io.print("EDIT AN ORDER");
                        break;
                    case 4:
                        removeOrder();
                        //io.print("REMOVE AN ORDER");
                        break;
                    case 5:
                        saveWork();
                        //io.print("SAVE CURRENT WORK");
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (WrongEntryException | FlooringMasteryPersistenceException | NumberFormatException e) {
            view.errorMessage(e.getMessage());

        }

    }

    private int getMenuSelection() {
        return view.printManuandGetSelection();
    }

    private void listOrders() throws FlooringMasteryPersistenceException {
        boolean error = false;
        do {
            String ld = view.getDate();

            try {
                LocalDate date = LocalDate.parse(ld, DateTimeFormatter.ofPattern("MMddyyyy"));

                view.displayAllOrdersBanner();
                List<OrderDTO> orderList = service.getAllOrders(date);

                view.displayOrderList(orderList);
                error = false;
            } catch (FlooringMasteryPersistenceException | DateTimeParseException ex) {
                error = true;
                view.displayNoOrder();
            }
        } while (error);
    }

    private void addOrder() throws FlooringMasteryPersistenceException, WrongEntryException {
        try {
            view.displayNewOrderBanner();
            OrderDTO currentOrder = view.getNewOrder();
            service.addOrder(currentOrder);
            view.displayOrder(currentOrder);
            view.displayNewOrderSuccess();
        } catch (FlooringMasteryDataValidationException | NumberFormatException e) {
            view.errorMessage(e.getMessage());
            view.wrongEntry();
            addOrder();

        }

    }

    private void editOrder() throws FlooringMasteryPersistenceException {
        String ld = view.getDate();
        LocalDate date = service.getDate(ld);
        int orderN = view.getOrderN();
        view.displayEditOrderBanner();
        OrderDTO order = service.getOrder(date, orderN);
        boolean hasError = false;
        do {
            try {
                OrderDTO currentOrder = view.editOrder(order);
                service.editOrder(orderN, date, currentOrder);
                service.addOrder(currentOrder);
                view.displayOrder(currentOrder);
                view.displayNewOrderSuccess();
                hasError = false;
            } catch (FlooringMasteryDataValidationException | NumberFormatException e) {
                hasError = true;
                view.errorMessage(e.getMessage());
                view.wrongEntry();
            }
        } while (hasError);
    }

    private void saveWork() throws FlooringMasteryPersistenceException {

        service.saveWork();
        view.displaySaveSuccessBanner();

    }

    private void removeOrder() throws FlooringMasteryPersistenceException {
        try {
            view.displayRemoveOrderBanner();
            String ld = view.getDate();
            LocalDate date = service.getDate(ld);
            int orderN = view.getOrderN();
            service.removeOrder(orderN, date);
            view.displayRemoveSuccessBanner();
        } catch (FlooringMasteryPersistenceException e) {
            view.wrongEntry();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
