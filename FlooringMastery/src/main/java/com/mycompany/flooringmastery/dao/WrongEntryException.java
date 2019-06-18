
package com.mycompany.flooringmastery.dao;

/**
 *
 * @author Anna
 */
public class WrongEntryException extends Exception{

    public WrongEntryException(String message) {
        super(message);
    }

    public WrongEntryException(String message, Throwable cause) {
        super(message, cause);
    }
    
} 

