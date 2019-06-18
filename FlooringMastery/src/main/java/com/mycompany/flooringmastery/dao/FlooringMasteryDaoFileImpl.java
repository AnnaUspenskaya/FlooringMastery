package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dao.Mappers.OrderMapper;
import com.mycompany.flooringmastery.dto.OrderDTO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Anna
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    public static final String ORDER_FILE = "Orders_";
    private Map<LocalDate, Map<Integer, OrderDTO>> orders = new HashMap<>();

    @Override
    public void addOrder(LocalDate ld, OrderDTO order) throws FlooringMasteryPersistenceException {
        if (!orders.containsKey(ld)) {
            loadOrder(ld);
        }
        if (!orders.containsKey(ld)) {
            Map<Integer, OrderDTO> ordersByNumber = new HashMap<>();
            ordersByNumber.put(order.getOrderN(), order);
        }
        orders.get(ld).put(order.getOrderN(), order);
    }

    @Override
    public List<OrderDTO> getAllOrders(LocalDate ld) throws FlooringMasteryPersistenceException {

        Map<LocalDate, Map<Integer, OrderDTO>> orderS = loadOrder(ld);
        return new ArrayList<OrderDTO>(orderS.get(ld).values());

    }

    @Override
    public OrderDTO getOrder(int orderN, LocalDate ld) throws FlooringMasteryPersistenceException {
        if (!orders.containsKey(ld)) {
            loadOrder(ld);
        }
        if (!orders.containsKey(ld)) {
            return null;
        }
        return orders.get(ld).get(orderN);
    }

    @Override
    public void editOrder(int orderN, LocalDate ld, OrderDTO order) throws FlooringMasteryPersistenceException {
        Map<Integer, OrderDTO> currentOrders;
        if (orders.containsKey(ld)) {
            currentOrders = orders.get(ld);
            if (currentOrders.containsKey(orderN)) {
                currentOrders.put(orderN, order);
            }
        } else {
            currentOrders = loadOrder(ld);
            if (currentOrders.containsKey(orderN)) {
                currentOrders.put(orderN, order);
            }
        }
        orders.put(ld, currentOrders);
    }

    @Override
    public void removeOrder(int orderN, LocalDate ld) throws FlooringMasteryPersistenceException {

        Map<Integer, OrderDTO> currentOrders;
        if (orders.containsKey(ld)) {
            currentOrders = orders.get(ld);
            if (currentOrders.containsKey(orderN)) {
                currentOrders.remove(orderN);
            }
        } else {
            currentOrders = loadOrder(ld);
            if (currentOrders.containsKey(orderN)) {
                currentOrders.remove(orderN);
            }
        }

        orders.put(ld, currentOrders);
        orders.get(ld).remove(orderN);
    }

    @Override
    public void saveWork(LocalDate ld) throws FlooringMasteryPersistenceException {
        writeOrder(ld);
    }

    private Map loadOrder(LocalDate ld) throws FlooringMasteryPersistenceException {

        Map<Integer, OrderDTO> ordersByN = new HashMap<>();//reserves  new space in memory 
        String date = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = ORDER_FILE + date + ".txt";

        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(new FileReader(fileName)));
            String row = "";
            while (scanner.hasNextLine()) {
                row = scanner.nextLine();
                OrderDTO o = OrderMapper.toOrder(row);
                ordersByN.put(o.getOrderN(), o);
            }
            orders.put(ld, ordersByN);
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not load data into memory", e);
        }
        return orders;
    }

    private void writeOrder(LocalDate ld) throws FlooringMasteryPersistenceException {
        String date = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = ORDER_FILE + date + ".txt";
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save order", e);
        }
        Map<Integer, OrderDTO> myOrders = orders.get(ld);
        if (myOrders == null) {
            return;
        }
        List<OrderDTO> ordersList = new ArrayList<>(orders.get(ld).values());
        for (OrderDTO order : ordersList) {
            out.println(OrderMapper.toStringCSV(order));
            out.flush();

        }
        out.close();

    }
}
