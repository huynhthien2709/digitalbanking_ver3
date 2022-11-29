package vn.funix.fx17332.java.asm03.models;

import jdk.jshell.execution.Util;
import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Customer;
import vn.funix.fx17332.java.asm03.ReportService;
import vn.funix.fx17332.java.asm03.Withdraw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SavingAccount extends Account implements ReportService, Withdraw {
    public static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    List<Customer> customers = new ArrayList<Customer>();
    private String accountType;
    Utils utils = new Utils();
    private String title;
//    Account account = new Account();
    public static final Scanner sc = new Scanner(System.in);
    DecimalFormat formatter = new DecimalFormat("#,###");
    Customer customer = new Customer();

    @Override
    public void log(double amount) {
        System.out.println(utils.getDivider());
        System.out.printf("%30s %s%n", utils.getTitle(), getAccountType());
        System.out.printf("NGAY G/D: %32s%n", utils.getDateTime());
        System.out.printf("ATM ID: %34s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %35s%n", getAccountNumber());
        System.out.printf("SO TIEN: %33s%n", formatter.format(amount));
        System.out.printf("SO DU: %35s%n", formatter.format(getBalance()));
        System.out.printf("PHI + VAT: %31s%n", "0đ");
        System.out.println(utils.getDivider());

    }




    @Override
    public boolean withdraw(double amount) {
        Transaction newTransaction = new Transaction();
        double newBalance = 0.0;
        if (isAccept(amount)) {
            if (getBalance() - amount < 50000) {
                System.out.println("So du khong du de thuc hien giao dich");
            } else {
                newBalance = getBalance() - amount;
                setBalance(newBalance);
                System.out.println("Giao dich thanh cong.");
                log(amount);
                newTransaction.setAmount(amount);
                newTransaction.setTime(newTransaction.getTime());
                transaction.add(newTransaction);
                System.out.println("Chuc nang: ");

            }
            return true;
        }
        return false;
    }
    @Override
    public boolean isAccept(double amount) {
        if (amount < 50000) {
            System.out.println("So tien phai lơn hơn 50,000");
            return false;
        }
        if (!isPremium() && amount > SAVINGS_ACCOUNT_MAX_WITHDRAW) {
            System.out.println("Ban chi duoc rut toi da 5,000,000");
            return false;
        }
        if (getBalance() < 50000) {
            System.out.println("So du khong du de thuc hien giao dich");
            return false;
        }
        if (amount % 10000 != 0) {
            System.out.println("So tien rut phai la boi so cua 10,000");
            return false;
        }
        return true;
    }


}
