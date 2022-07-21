/* @author Joseph Walker, Team 3 - Juggernauts
*  Project 0 Banking app Phase I & Phase 2 with Postgresql
*  Entry point to Java org.zero.Bank application */

package org;

import java.util.logging.Logger;

public class Bank {

    // Main method serves as the point of entry into the Java Bank app.
    // Here we will instantiate our BankUser object and BankEmployee object.
    // Utilization of the various class methods for both classes will be demonstrated.


    // Instantiating our logger object:

    private static Logger logger = Logger.getLogger(String.valueOf(Bank.class));

    public static void main(String[] args) {

        BankUser John = new BankUser(200);
        John.accessMenu();

        BankEmployee Matt = new BankEmployee(474);
        Matt.createAccount();

    }
}