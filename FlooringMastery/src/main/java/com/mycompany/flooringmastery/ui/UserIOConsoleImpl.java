
package com.mycompany.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author Anna
 */
public class UserIOConsoleImpl implements UserIO {
      @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String msg) {
        double result = 0;
        try {
            result = Double.parseDouble(readString(msg));
        } catch (NumberFormatException ex) {
            print("Please enter a number.");
            result = readDouble(msg);
        }
        return result;
    }

    @Override
    public String readString(String msg) {
        print(msg);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    @Override
    public int readInt(String msg) {
        int result = 0;
        try {
            result = Integer.parseInt(readString(msg));
        } catch (NumberFormatException ex) {
            print("Please enter a number.");
            result = readInt(msg);
        }
        return result;
    }

    @Override
    public int readInt(String msg, int min, int max) {
        int result = 0;
        try {
            result = Integer.parseInt(readString(msg));
            if (result < min || result > max) {
            }
        } catch (NumberFormatException ex) {
            print("Please enter a number between " + min + " and " + max);
            result = readInt(msg);
        }
        return result;
    }
}
