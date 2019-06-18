package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dao.ProductDAO;
import com.mycompany.flooringmastery.dao.TaxDAO;
import com.mycompany.flooringmastery.dto.OrderDTO;
import com.mycompany.flooringmastery.dto.ProductDTO;
import com.mycompany.flooringmastery.dto.TaxDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Anna
 */
public class FlooringMasteryServiceLayerFileImpl implements FlooringMasteryServiceLayer {

    TaxDAO myTax;
    ProductDAO myProduct;
    FlooringMasteryDao dao;
    TaxDTO tax;
    ProductDTO product;
    OrderDTO order;

    public FlooringMasteryServiceLayerFileImpl(TaxDAO myTax, ProductDAO myProduct, FlooringMasteryDao dao) {
        this.myTax = myTax;
        this.myProduct = myProduct;
        this.dao = dao;

    }

    public LocalDate setDate() {
        LocalDate ld = LocalDate.now();
        ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return ld;
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) throws FlooringMasteryPersistenceException, FlooringMasteryDataValidationException {

        try {

            validateOrderData(order);
            TaxDTO tax = myTax.getTax(order.getState());
            if (tax == null) {
                throw new FlooringMasteryDataValidationException("Invalide state");
            }
            order.setTax(tax.getTax());
            ProductDTO product = myProduct.getProduct(order.getProduct());
            if (product == null) {
                throw new FlooringMasteryDataValidationException("Invalide state");
            }
            order.setCost(product.getCost());
            order.setLabor(product.getLabor());
            product.setCostTotal(product.getCost().multiply(order.getArea()));
            order.setCostTotal(product.getCostTotal());
            product.setLaborTotal(product.getLabor().multiply(order.getArea()));
            order.setLabor(product.getLabor());
            order.setLaborTotal(product.getLaborTotal());
            BigDecimal netCost = product.getLaborTotal().add(product.getCostTotal());
            tax.setTaxTotal(tax.getTax().multiply(netCost));
            order.setTaxTotal((netCost.multiply(tax.getTax())).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
            LocalDate ld = setDate();
            order.setLd(ld);
            order.setTotal(netCost.add(order.getTaxTotal()).setScale(2, RoundingMode.HALF_UP));
            dao.addOrder(ld, order);
        } catch (FlooringMasteryPersistenceException e) {
        }

        return order;
    }

    @Override
    public List<OrderDTO> getAllOrders(LocalDate ld) throws FlooringMasteryPersistenceException {
        return dao.getAllOrders(ld);
    }

    @Override
    public OrderDTO editOrder(int orderN, LocalDate ld, OrderDTO order) throws FlooringMasteryPersistenceException {
        dao.editOrder(orderN, ld, order);
        if (order.getCustomer() == null
                || order.getCustomer().trim().length() == 0) {
            order.setCustomer(order.getCustomer());
        }
        if (order.getState() == null
                || order.getState().trim().length() == 0) {
            order.setState(order.getState());
        }
        if (order.getProduct() == null
                || order.getProduct().trim().length() == 0) {
            order.setProduct(order.getProduct());
        }
        if (order.getArea() == null) {
            order.setArea(order.getArea());
        }
        TaxDTO tax = myTax.getTax(order.getState());
        order.setTax(tax.getTax());
        ProductDTO product = myProduct.getProduct(order.getProduct());
        order.setCost(product.getCost());
        order.setLabor(product.getLabor());
        product.setCostTotal(product.getCost().multiply(order.getArea()));
        order.setCostTotal(product.getCostTotal());
        product.setLaborTotal(product.getLabor().multiply(order.getArea()));
        order.setLabor(product.getLabor());
        order.setLaborTotal(product.getLaborTotal());
        BigDecimal netCost = product.getLaborTotal().add(product.getCostTotal());
        tax.setTaxTotal(tax.getTax().multiply(netCost));
        order.setTaxTotal((netCost.multiply(tax.getTax())).divide(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
        ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        order.setLd(ld);
        order.setTotal(netCost.add(order.getTaxTotal()).setScale(2, RoundingMode.HALF_UP));

        dao.addOrder(ld, order);
        return order;
    }

    @Override
    public void removeOrder(int orderN, LocalDate ld) throws FlooringMasteryPersistenceException {
        dao.removeOrder(orderN, ld);
    }

    @Override
    public LocalDate getDate(String date) {
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        return ld;
    }

    @Override
    public void saveWork() throws FlooringMasteryPersistenceException {

        LocalDate ld = LocalDate.now();
        ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        dao.saveWork(ld);
    }

    @Override
    public OrderDTO getOrder(LocalDate ld, int orderN) throws FlooringMasteryPersistenceException {
        return dao.getOrder(orderN, ld);
    }

    private void validateOrderData(OrderDTO order) throws
            FlooringMasteryDataValidationException {
        if (order.getCustomer() == null
                || order.getCustomer().trim().length() == 0
                || order.getState() == null
                || order.getState().trim().length() == 0
                || order.getProduct() == null
                || order.getProduct().trim().length() == 0
                || order.getArea() == null) {
            throw new FlooringMasteryDataValidationException(
                    "ERROR: All fields [Customer Name, State, Product, Area] are required."
            );
        }
    }
}
