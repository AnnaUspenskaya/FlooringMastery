
package com.mycompany.flooringmastery.ui;

/**
 *
 * @author Anna
 */
public interface UserIO {
    void print(String msg);

    int readInt(String msg);

    int readInt(String msg, int min, int max);

    double readDouble(String msg);

    String readString(String msg);
}
