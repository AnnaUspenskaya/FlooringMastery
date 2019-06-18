package com.mycompany.flooringmastery.dao.Mappers;

import com.mycompany.flooringmastery.dto.OrderDTO;
import java.math.BigDecimal;

/**
 *
 * @author Anna
 */
public class OrderMapper {

    public static OrderDTO toOrder(String row) {
        String[] fields = row.split("::");
        OrderDTO Order = new OrderDTO(Integer.parseInt(fields[0]));
        Order.setCustomer(fields[1]);
        Order.setState(fields[2]);
        Order.setTax(new BigDecimal(fields[3]));
        Order.setProduct(fields[4]);
        Order.setArea(new BigDecimal(fields[5]));
        Order.setCost(new BigDecimal(fields[6]));
        Order.setLabor(new BigDecimal(fields[7]));
        Order.setCostTotal(new BigDecimal(fields[8]));
        Order.setLaborTotal(new BigDecimal(fields[9]));
        Order.setTaxTotal(new BigDecimal(fields[10]));
        Order.setTotal(new BigDecimal(fields[11]));
        return Order;
    }

    public static String toStringCSV(OrderDTO Order) {
        return Order.getOrderN() + "::"
                + Order.getCustomer() + "::"
                + Order.getState() + "::"
                + Order.getTax() + "::"
                + Order.getProduct() + "::"
                + Order.getArea() + "::"
                + Order.getCost() + "::"
                + Order.getLabor() + "::"
                + Order.getCostTotal() + "::"
                + Order.getLaborTotal() + "::"
                + Order.getTaxTotal() + "::"
                + Order.getTotal();
    }

}
