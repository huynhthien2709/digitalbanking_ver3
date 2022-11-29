package vn.funix.fx17332.java.asm03.models;

import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Customer;
import vn.funix.fx17332.java.asm03.ReportService;
import vn.funix.fx17332.java.asm03.Withdraw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class LoanAccount extends Account implements Withdraw, ReportService {
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    Utils utils = new Utils();
    DecimalFormat formatter = new DecimalFormat("#,###");
    Customer customer = new Customer();
    private boolean check;

    public LoanAccount() {
        setBalance(LOAN_ACCOUNT_MAX_BALANCE);
    }

    @Override
    public boolean withdraw(double amount) {
        Transaction newTransaction = new Transaction();
        List<Transaction> transactionList = new ArrayList<Transaction>();
        double newBalance = 0.0;
        if (isAccept(amount)) {
            if (getBalance() - amount < 50000) {
                System.out.println("Han muc khong du de thuc hien giao dich.");
                return false;
            }
            newBalance = getBalance() - (amount + (getTransactionFee(amount)));
            setBalance(newBalance);
            log(amount);
            newTransaction.setAmount(amount);
            newTransaction.setTime(newTransaction.getTime());
            transaction.add(newTransaction);
            System.out.println("Giao dich thanh cong");
            System.out.println("Chuc nang: ");

            return true;
        }
        return false;
    }


    @Override
    public boolean isAccept(double amount) {
        if (amount > LOAN_ACCOUNT_MAX_BALANCE) {
            System.out.println("So tien rut vươt qua han muc");
            return false;
        }
        if (LOAN_ACCOUNT_MAX_BALANCE < 50000) {
            System.out.println("So du khong du de thuc hien giao dich");
            System.out.println("Chuc nang: ");
            return false;
        }
        return true;
    }

    @Override
    public void log(double amount) {
        System.out.println(utils.getDivider());
        System.out.printf("%30s %s%n", utils.getTitle(), getAccountType());
        System.out.printf("NGAY G/D: %32s%n", utils.getDateTime());
        System.out.printf("ATM ID: %34s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %35s%n", getAccountNumber());
        System.out.printf("SO TIEN: %33s%n", formatter.format(amount));
        System.out.printf("SO DU: %35s%n", formatter.format(getBalance()));
        System.out.printf("PHI + VAT: %31s%n", formatter.format(getTransactionFee(amount)));
        System.out.println(utils.getDivider());

    }

    public double getTransactionFee(double amount) {
        double fee = 0.0;
        if (isPremium()) {
            fee = amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        } else {
            fee = amount * LOAN_ACCOUNT_WITHDRAW_FEE;
        }
        return fee;
    }


}
