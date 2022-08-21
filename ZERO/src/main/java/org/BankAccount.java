package org;

    // Importing the Scanner & Logger classes from java.util package

import java.util.Scanner;
import java.util.logging.Logger;

    // BankAccount class will host the majority of our app methods. BankAccount
    // class will permit manipulation of user account, including but not limited
    // to account access, depositing funds, withdrawing funds, and applying for
    // additional lines of credit if creditworthiness allows.

public class BankAccount {

    // Logger object deployed to provide feedback of account activities

    private static Logger logger = Logger.getLogger(String.valueOf(BankAccount.class));

    // Private access modifier used below to restrict access so that they can only
    // be accessed by the defining class, which here, is BankAccount. Attributes
    // for this class include customer id, credit, balance, and ability to review
    // the last transaction of account.

    private int balance;
    private int previousTransaction;
    private int customerID;
    private int credit;

    // Here we create our Class constructor w/args customer name, customer ID, and account ID

    public BankAccount(int uid) {

        customerID = uid;

    }

    // Method for retrieving customerID(alias uid) variable for identifying accounts

    public int getCustomerID() {

        return customerID;
    }

    // Method for retrieving customer balance
    public int getBalance() {

        logger.info("Balance viewed");

        return balance;
    }

    // Method for retrieving customer credit

    public String getCredit() {

        logger.info(">> Credit checked <<");

        // Though scores can drop as low as 300, most credit scores tend to range from 600 to 750.
        // "Base" FICO Scores (FICO Score 8) range from 300-850.
        // Source/WORKS CITED:
        // * Wohlner,Roger(April 2019). "How Low Can Your Credit Score Go?". Lending Tree. Retrieved
        // July 15, 2022 from https://www.lendingtree.com/credit-repair/how-low-can-your-credit-score-go
        // /#:~:text=Though%20scores%20can%20drop%20as,payments%2C%20bankruptcies%20or%20loan%20defaults.


        if (credit < 300){

            // User is prompted to apply for lines of credit. This initiates a soft pull, so we can
            // get a snapshot of their creditworthiness and make a lending decision. Here at Java Bank,
            // we don't focus so much on user income - we're a credit based lender! Come apply with us today :)

            return "Please apply for credit lines to review credit";
        }
        return "Your credit is currently " + credit;
    }

    // Deposit method: If statement for amount being equal to zero.
    // If amount equals zero, there is no action to be taken.

    public String deposit(int amount) {

        logger.info(">> Funds deposited <<");

        if (amount != 0) {

            balance = balance + amount;
            previousTransaction = amount;
        }
        return "You have deposited $" + amount;
    }

    // Method for Withdrawing Money

    public String withdraw(int amount) {

        logger.info(">> Funds withdrawn <<");

        // Setting the amount at greater than or equal to integer 1 ensures
        // there will be funds to transfer, mitigating the risk of overdraft fees
        // for our end user.

        if (amount >= 1) {

            balance = balance - amount;
            previousTransaction = -amount;
            return "Your withdraw of $" + amount + " was successful!";
        }

        // In the unfortunate event user does not have funds conducive for a successful
        // withdrawal, they are notified promptly by our state-of-the art technology.

        return "You have insufficient funds";
    }

    // Method showing previous transaction. Here we run if/else if
    // statements while leveraging Math.abs, which simply returns the
    // ABSOLUTE VALUE of a value.

    public String getPreviousTransaction() {

        logger.info(">> Previous transaction viewed <<");

        // If amount was greater than zero, we know this was a gain in funds,
        // signaling a deposit has occurred.

        if (previousTransaction > 0) {

           return "Deposited: $" + previousTransaction;

           // A transfer below the $0 threshold triggers the system to notify user
           // there was a withdrawal, followed by a dollar amount.

        } else if (previousTransaction < 0) {

            return "Withdrawn: $" + Math.abs(previousTransaction);

            // Any WHOLE number that is not greater than zero or less than zero is equal
            // to zero. Therefore, with zero funds moving either way, no transaction has
            // transpired.

        } else {

            return "No transaction occurred.";
        }
    }

    // Method which allows entity to transfer funds from one BankAccount object to another.

    public String transfer(BankAccount to) {

        logger.info(">> Transfer of funds initiated <<");

        System.out.println();

        // We begin by ensuring our user has confirmation where their balance currently stands

        System.out.println("Your current balance is " + getBalance());
        Scanner transfer = new Scanner(System.in);
        System.out.println("Please enter amount you wish to transfer >");
        int amount = transfer.nextInt();
        int currentBalance = getBalance();

        // As long as balance in current account is greater than zero AND exceeds the amount
        // in which they wish to transfer (to avoid overdraft/no funds remaining), Java Bank
        // allows user to proceed with transfer process.

        if (currentBalance > 0 && currentBalance >= amount) {

            withdraw(amount);
            to.deposit(amount);
            return "Thanks! The transfer to was successful. " +
                    "Your balance is now " + to.getBalance();

            // If EITHER of the above two mentioned conditions prove to be false, the
            // transfer is denied.

        } else {

            return"Request denied due to insufficient funds. " +
                    "Please go to main menu to make a deposit first";
        }
    }

    // Method which challenges user & employee access to account services. Once credentials
    // are validated, entry to main interface of account services are unlocked

    public String accessMenu() {

        logger.info(">> Menu successfully accessed << ");

        Scanner access = new Scanner(System.in);
        System.out.println("Please enter account ID for access > ");

        // Each BankUser object is instantiated with a unique account id. This unique account
        // id must be entered in order for user to prove identity and gain access to our
        // Java Bank main menu successfully.

        int id = access.nextInt();
        if (id == customerID) {
            mainMenu();
        }

        // In the event user enters an account id that is not congruent with the one on record,
        // they will be prompted repeatedly to provide the correct account id. Here at Java Bank
        // we believe in giving people multiple chances.

        while (id != customerID){

            System.out.println("Invalid code: please re-enter >");
            id = access.nextInt();
            if (id == customerID) {

                mainMenu();
            }
        }
        return "";
    }

    // Method which allows client to apply for credit with our financial institution, Java Bank.
    // Approval is contingent upon user's current credit score. In our main menu, user (or BankEmployee)
    // is prompted to enter applicant's current credit score.

    public String applyForCredit(int score ) {

        logger.info(">> Credit applied for <<");

        credit = 0;
        credit = credit + score;

        return "Your current credit score is " + score;
    }

    // Method which provides employee access to manually review credit score of user

    public String reviewCredit(int credit){

        logger.info(">> Credit application status reviewed <<");

        // User must FIRST apply for credit in order to review status of their credit application.
        // Once application has been submitted, we extrapolate creditworthiness and make a real time
        // lending decision based off credit score.

        getCredit();

        // Lending decision of credit score below 600:

        if (credit < 600){
            return "Bad credit -- automatic denial";
        }

        // Lending decision of credit score between 600 and 640:

        if (credit >= 600 && credit <= 640) {
            return "Poor credit -- requires review and high interest";
        }

        // Lending decision of credit score between 641 and 680:

        if (credit >= 641 && credit <= 680){
            return "Good credit -- automatic approval low to high interest";
        }
        else{
            // For any users ABOVE the 680 credit score threshold, it's a pleasure doing business with you!

            return "Excellent credit -- automatic approval low interest";
        }
    }

    // Function displaying MAIN MENU. Variable "option" allows us to choose from menu options.
    // Here is where we add Scanner object to prompt user for information

    public void mainMenu() {

        logger.info(">> Accessed Main Menu << ");

        // Our user has successfully entered their credentials and gained access to the
        // Java Bank main menu. They are greeted with various options, ranging from checking
        // their balance, making a deposit, withdrawal, applying for credit, and exiting our
        // main menu once complete.

        char option = '\0';

        Scanner info = new Scanner(System.in);

        System.out.println("Welcome to the Java Bank!");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("A. Check your balance");
        System.out.println("B. Make a deposit");
        System.out.println("C. Make a withdrawal");
        System.out.println("D. View previous transaction");
        System.out.println("E. Apply for credit");
        System.out.println("F. Check credit");
        System.out.println("G. Review credit application");

        System.out.println("H. Exit");

        // Do-while loop will ALWAYS execute at least once, and subsequently check the
        // condition within the while loop. We begin by executing a prompt for the user
        // to enter an option and will continue the prompt until they select the exit option.

        do {
            System.out.println();
            System.out.println("Enter an option: ");
            System.out.println();
            System.out.println("A. Check your balance");
            System.out.println("B. Make a deposit");
            System.out.println("C. Make a withdrawal");
            System.out.println("D. View previous transaction");
            System.out.println("E. Apply for credit");
            System.out.println("F. Check credit");
            System.out.println("G. Review credit application");
            System.out.println();
            char option1 = info.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch (option) {

                // Case 'A' allows the user to check their account balance

                case 'A':
                    System.out.println("====================================");
                    System.out.println("Your balance is currently $" + balance);
                    System.out.println("====================================");
                    break;

                // Case 'B' allows the user to deposit money into their account.
                // Here we call our "Deposit" method.

                case 'B':
                    System.out.println("Enter an amount to deposit: ");
                    int amount = info.nextInt();
                    System.out.println(deposit(amount));
                    System.out.println();
                    break;

                // Case 'C' allows the user to withdraw money into their account.
                // Here we call our "Withdraw" method.

                case 'C':
                    System.out.println("Enter an amount to withdraw: ");
                    int amount2 = info.nextInt();
                    System.out.println(withdraw(amount2));
                    System.out.println();
                    break;

                // Case 'D' allows user to view their most recent transactions.
                // Here we call the accessor method for transaction activity.

                case 'D':
                    System.out.println("====================================");
                    System.out.println(getPreviousTransaction());
                    System.out.println("====================================");
                    break;

                // Case 'E' allows user to apply for credit with the Java Bank.
                // Here we call our applyForCredit method

                case 'E':
                    System.out.println("Employee or user, please enter current credit score: ");
                    int score = info.nextInt();
                    System.out.println(applyForCredit(score));
                    System.out.println();
                    break;

                // Case 'F' allows user to view their credit after applying.
                // Here we call the accessor method for credit.

                case 'F':
                    System.out.println("====================================");
                    System.out.println(getCredit());
                    System.out.println("====================================");
                    break;

                // Case 'G' allows the employee to manually review user's credit application.
                // Here we call our "credit review" method.

                case 'G':

                    System.out.println("Please enter user's credit: ");
                    int result = info.nextInt();
                    System.out.println(reviewCredit(result));
                    System.out.println();
                    break;

                // Case 'H' exits out of our menu

                case 'H':
                    System.out.println("====================================");
                    break;

                // The default case informs the user that they entered an invalid character.

                default:
                    System.out.println("Error: invalid option. Please enter A,B,C,D,E,G,F or H");
                    break;
            }

            // Our while loop condition is as long as char 'H'/'h' has not been selected, we will continue
            // to prompt our user to enter an option. Once char 'H'/'h' is submitted, our condition has
            // evaluated to false, and we break out of our loop to graciously thank our user!

        } while (option != 'H');

        System.out.println("Thank you for banking with us!");
    }
}
