    // This class will represent our Java Bank employee.
    // We will import Scanner, Logger, and sql packages

package org;

import java.util.Scanner;
import java.sql.*;
import java.util.logging.Logger;

public class BankEmployee extends BankUser {

    // Logger object deployed to provide feedback of account activities

    private static Logger logger = Logger.getLogger(String.valueOf(BankAccount.class));


    // Private access modifiers used below to restrict access so that they can only
    // be accessed by the defining class, which here, is the Java BankEmployee

    private int employeeID;
    Scanner scan = new Scanner(System.in);

    public BankEmployee(int eid) {
        super(eid);
        this.employeeID = eid;
    }

    // Our createAccount method which prompts employee to enter first name followed by
    // last four of user's SSN. Generates new account with user id.

    public void createAccount() {

        logger.info(">> New account initiated <<");

        // Here we declare our Connection instance, "c".

        Connection c;

        try {

            // In order to establish a connection with our database, we must first load & register
            // our driver. We do so utilizing the "Class.forName" method. Each SQL vendor must
            // create their own Driver that's compatible for Java - there are no "generic" Drivers.
            // Below, we load our Driver for Postgresql.

            Class.forName("org.postgresql.Driver");

            // Here we initialize our instance of Connection, setting it equal to the abstract
            // DriverManager class, which calls the .getConnection method in order to establish
            // a connection with the database and register Driver from selected vendor (in this case,
            // Postgresql).

            c = DriverManager
                    .getConnection("jdbc:postgresql://javadb-0.cpz1wwb1y5uo.us-east-1.rds.amazonaws.com:5432/postgres",
                            "username", "yourPassword");

            // Statement which creates new table for bank user within our database with the following properties:
            // new bank user id which will act as our primary key, user's name, and account number.
            // Although we're utilizing Java Strings, we still structure it in the proper SQL syntax unique to our
            // specific vendor so the commands execute without exception.

            String sql1 = "create table new_bank_user(bank_id varchar(10) primary key, name varchar(10), acct_num varchar(10));";

            // Preparing our statement for creating new table for bank users with the PreparedStatement. This interface
            // allows us to create a PreparedStatement instance and then execute our SQL command. We have chosen to use
            // the PreparedStatement OVER the Statement interface due to the fact that it is more secure ( mitigates
            // SQL injection), not to mention the fact that it's more dynamic and efficient.

            // Use of Statement interface IS permitted, though should not be used when dealing with user information.

            PreparedStatement ps = c.prepareStatement(sql1);
            ps.execute();

            // Inserting new account records into the database using Scanner object and for loop. Here we have decided
            // to insert into our 3 columns/properties the values of bank id, user name, and their account number.

            String sql = "Insert into new_bank_user (bank_id,name,acct_num) values(?,?,?);";

            PreparedStatement ps1 = c.prepareStatement(sql);

            // For loop will iterate here a total of 3 times, prompting us to input the values for our 3 columns.
            // In this example below, we will be inputting 3 separate users, though we can expand that number (or
            // decrease it) if need be.

            
            for (int i = 0; i < 3; i++) {

                // Prompts are given, then stored in appropriate variables.

                System.out.println("Please enter user id >");
                String id = scan.nextLine();
                System.out.println("Please enter user name >");
                String name = scan.nextLine();
                System.out.println("Please enter user account number >");
                String acct_num = scan.nextLine();

                // Parameter indexes are set with the proper stored variables from Scanner above.

                ps1.setString(1, id);
                ps1.setString(2, name);
                ps1.setString(3, acct_num);

                // Here our PreparedStatement object preps the batch of insert commands:

                ps1.addBatch();
            }

            // Executing the batch:

            ps1.executeBatch();

            // Query to select all and view our table in the database:

            String sqlStmnt = "Select * from new_bank_user;";
            PreparedStatement ps2 = c.prepareStatement(sqlStmnt);

            // We create an instance of the ResultSet, setting it equal to the results of our query being
            // executed from previous PreparedStatement.

            ResultSet rs = ps2.executeQuery();
            System.out.println(
                    "id\t\tname\t\tacct_num");
            System.out.println(
                    "-------------------------------------------------------");

            // While loop will repeatedly execute, printing out each value of our requested
            // columns as long as their is another result set to be obtained.

            while (rs.next()) {

                System.out.println(rs.getString(1) + "\t\t"
                        + rs.getString(2)
                        + "\t\t"
                        + rs.getString(3));
                System.out.println();
            }

            // In the event of an exception, we can print the Stack Trace, which allows us to trouble shoot
            // and better pinpoint where and what issue to resolve.

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        // Finally, the print statement informs us that we were successful in attempt to connect to database and
        // update it with new Java Bank users.

        System.out.println("Connected to database successfully!");
    }
}
