import org.BankAccount;
import org.junit.Test;


import static org.junit.Assert.*;

public class BankAccountTest {

    public BankAccountTest() {
    }

    @Test
    public void showBalanceOfZero() {
        BankAccount Paul = new BankAccount(001);
        assertEquals(0, Paul.getBalance());
    }

    @Test
    public void charlesDepositsTwoHundredWithrawsOneHundredAndPreviousTransactionHistoryShouldReflectOneHundredWithdrawn() {
        BankAccount Charles = new BankAccount(002);
        Charles.deposit(200);
        Charles.withdraw(100);
        assertEquals(100, Charles.getBalance());
    }

    @Test
    public void JosephDepositsEightHundred(){
        BankAccount Joseph = new BankAccount(311);
        assertEquals("You have deposited $800",Joseph.deposit(800) );
    }

    @Test
    public void userDepositsFiveHundredWithdrawsTwentyDollarsTransactionHistoryReflectsTwentyWithdrawn(){
        BankAccount Paula = new BankAccount(300);
        Paula.deposit(500);
        Paula.withdraw(20);
        assertEquals("Withdrawn: $20", Paula.getPreviousTransaction());
    }
    @Test
    public void userDepositsFourHundredWithdrawsOneHundredDepositsThreeDollarsTransactionHistoryReflectsThreeDeposited(){
        BankAccount Max = new BankAccount(403);
        Max.deposit(400);
        Max.withdraw(100);
        Max.deposit(3);
        assertEquals("Deposited: $3", Max.getPreviousTransaction());
    }

    @Test
    public void userNeitherDepositsOrWithdrawsAndThereShouldBeNoTransactionHistory(){
        BankAccount Mario = new BankAccount(501);
        assertEquals("No transaction occurred.", Mario.getPreviousTransaction());
    }

    @Test
    public void edDepositsThirtyWithdrawsThirtyAndShouldHaveInsufficientFunds() {
        BankAccount Ed = new BankAccount(005);
        Ed.deposit(30);
        Ed.withdraw(30);
        assertEquals("You have insufficient funds", Ed.withdraw(0));
    }

    @Test
    public void fortyDollarsShouldBeDepositedIntoAccount() {
        BankAccount Tom = new BankAccount(003);
        Tom.deposit(40);
        assertEquals(40, Tom.getBalance());
    }

    @Test
    public void thisWillShowVeronicaHasAmazingCredit() {
        BankAccount Veronica = new BankAccount(006);
        int credit = 0;
        int score = 800;
        credit = credit + score;
        assertEquals("Your current credit score is 800", Veronica.applyForCredit(800));
    }

    @Test
    public void accountReviewsCreditToDiscoverItIsExellent() {
        BankAccount Spencer = new BankAccount(007);
        assertEquals("Excellent credit -- automatic approval low interest", Spencer.reviewCredit(777));
    }

    @Test
    public void accountReviewsCreditToDiscoverItIsGood() {
        BankAccount Archy = new BankAccount(000);
        assertEquals("Good credit -- automatic approval low to high interest", Archy.reviewCredit(660));
    }

    @Test
    public void accountReviewsCreditToDiscoverItIsPoor() {
        BankAccount Megan = new BankAccount(100);
        assertEquals("Poor credit -- requires review and high interest", Megan.reviewCredit(631));
    }

    @Test
    public void accountReviewsCreditToDiscoverItIsBadAndShouldSeekCreditRepair() {
        BankAccount Jeff = new BankAccount(200);
        assertEquals("Bad credit -- automatic denial", Jeff.reviewCredit(380));
    }
}



