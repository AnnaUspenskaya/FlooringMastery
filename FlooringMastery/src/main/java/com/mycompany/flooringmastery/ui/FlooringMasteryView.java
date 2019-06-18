package com.mycompany.flooringmastery.ui;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.OrderDTO;
import com.mycompany.flooringmastery.dto.ProductDTO;
import com.mycompany.flooringmastery.dto.TaxDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Anna
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    int counter = 1;
    TaxDTO tax;
    ProductDTO product;

    public int printManuandGetSelection() {
        io.print("▶▶▶FLOORING MASTERY◀◀◀");
        io.print("°°°Main Menu°°°");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices", 1, 5);
    }

    public OrderDTO getNewOrder() throws FlooringMasteryPersistenceException {

        String customer = io.readString("Customer Name: ");
        String state = io.readString("State(OH/PA/IN/MI): ").toUpperCase();
        String product = io.readString("Product type(Carpet/Laminate/Tile/Wood): ").toLowerCase();
        String area = io.readString("Area: ");
        BigDecimal areaBd = new BigDecimal(area);
        OrderDTO currentOrder = new OrderDTO(counter);
        currentOrder.setCustomer(customer);
        currentOrder.setState(state);
        currentOrder.setProduct(product);
        currentOrder.setArea(areaBd);
        counter++;
        return currentOrder;

    }

    public OrderDTO editOrder(OrderDTO newOrder) {

        String customer = io.readString("Customer Name (" + newOrder.getCustomer() + "):");
        String state = io.readString("State(OH/PA/IN/MI) (" + newOrder.getState() + "):").toUpperCase();
        String product = io.readString("Product type(Carpet/Laminate/Tile/Wood) (" + newOrder.getProduct() + "):").toLowerCase();
        String area = io.readString("Area (" + newOrder.getArea() + "):");
        io.readString(" ");
        BigDecimal areaBd = new BigDecimal(area);
        newOrder.setCustomer(customer);
        newOrder.setState(state);
        newOrder.setProduct(product);
        newOrder.setArea(areaBd);

        return newOrder;
    }

    public String promptToSave() {
        String question = io.readString("Do you want to save this order to the file? [y/n]");
        String answer = question.toLowerCase();
        return answer;

    }

    public void displayNewOrderBanner() {
        io.print("°°NEW ORDER°°");
    }

    public void displayEditOrderBanner() {
        io.print("°°EDIT ORDER°°");
    }

    public void displayNewOrderSuccess() {
        io.print("--The new order has been added.--");
        io.readString("Hit enter to continue.");
    }

    public void displayOrderList(List<OrderDTO> orderList) {

        for (OrderDTO currentOrder : orderList) {
            displayOrder(currentOrder);

        }
        io.readString("Hit enter to continue");

    }

    public void displayOrder(OrderDTO order) {

        io.print(" \n"
                + "--> Order Number: " + order.getOrderN() + " <--"
                + "\n Customer name: " + order.getCustomer()
                + "\n State: " + order.getState()
                + "\n Tax Rate: " + order.getTax()
                + "\n Product type: " + order.getProduct()
                + "\n Area: " + order.getArea()
                + "\n Cost per square foot: " + order.getCost()
                + "\n Labor cost per square foot: " + order.getLabor()
                + "\n Material cost: " + order.getCostTotal()
                + "\n Labor cost: " + order.getLaborTotal()
                + "\n Tax: " + order.getTaxTotal()
                + "\n Total: " + order.getTotal()
                + " ");

    }

    public String getDate() {
        return io.readString("Please, enter date in mmddyyyy format");
    }

    public int getOrderN() {
        String orderNstring = io.readString("Please, enter the order number.");
        int orderN = Integer.parseInt(orderNstring);
        return orderN;
    }

    public void displayAllOrdersBanner() {
        io.print("");
        io.print("°°ALL ORDERS°°");
    }

    public void displayDisplayOrderBanner() {
        io.print("");
        io.print("°°Display Order°°");
    }

    public String getOrderNumber() {
        return io.readString("Please, enter the order number.");
    }

    public String getOrderLd() {
        return io.readString("Please, enter the order date.");
    }

    public void displayRemoveOrderBanner() {
        io.print("");
        io.print("°°REMOVE ORDER°°");
    }

    public void displayRemoveSuccessBanner() {
        io.print("°°The order has been removed°°");
        io.readString("Hit enter to continue");
    }

    public void displayEditSuccessBanner() {
        io.print("°°The order has been updated°°");
        io.readString("Hit enter to continue");
    }

    public void displaySaveSuccessBanner() {
        io.print("");
        io.print("°°Your work has been saved°°");
        io.readString("Hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("---Good Bye!---");
    }

    public void displayUnknownCommandBanner() {
        io.print("---Unknown Command---");
    }

    public void displayNoOrder() {
        io.print("There is no order for that date.");
    }

    public void errorMessage(String errorMsg) {
        io.print("==Oh, No!!It's  an ERROR!!==");
        io.print(errorMsg);
    }

    public void wrongEntry() {
        io.print("Make sure the entered data is correct.");
    }

}
