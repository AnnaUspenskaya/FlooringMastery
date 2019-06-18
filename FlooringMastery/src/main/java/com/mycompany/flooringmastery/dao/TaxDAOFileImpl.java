package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dao.Mappers.TaxMapper;
import com.mycompany.flooringmastery.dto.TaxDTO;
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
public class TaxDAOFileImpl implements TaxDAO {

    public static final String TAXES_FILE = "data/Taxes.txt";
    public static final String DELIMITER = ",";

    private Map<String, TaxDTO> taxes = new HashMap<>();

    @Override
    public TaxDTO getTax(String state) throws FlooringMasteryPersistenceException {
        loadTax();
        return taxes.get(state);
    }

    private void loadTax() throws FlooringMasteryPersistenceException {

        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAXES_FILE)));
            String row = "";
            while (scanner.hasNextLine()) {
                row = scanner.nextLine();
                TaxDTO t = TaxMapper.toTax(row);
                this.taxes.put(t.getTaxByState(), t);
            }
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not load data into memory.", e);
        }
        scanner.close();
    }

}
