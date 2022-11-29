package vn.funix.fx17332.java.asm03.models;

import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Customer;
import vn.funix.fx17332.java.asm03.Withdraw;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private String time = utils.getDateTime();
    private boolean status;
    private static Utils utils = new Utils();
    Customer customer = new Customer();



    public Transaction() {

    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }


    public void transactionHistory(){
        Account account = new Account();
        Transaction transaction = new Transaction();
        customer.displayInformation();
        System.out.println(account.getAccountNumber()+ " " + transaction.getAmount() + " " + transaction.getTime());


    }

}
