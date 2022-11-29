package vn.funix.fx17332.java.asm03.models;

import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Customer;
import vn.funix.fx17332.java.asm03.Withdraw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DigitalCustomer extends Customer {
    public void withdraw(String accountNumber, double amount) {
        for (Account account : super.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                ((Withdraw) account).withdraw(amount);
                return;
            }
        }
    }

    @Override
    public void displayInformation() {
        String strPremium;
        DecimalFormat formatter = new DecimalFormat("#,###");
        if (isPremium()) {
            strPremium = "Premium";
        } else {
            strPremium = "Normal";
        }
        System.out.println(String.format("%s |%10s |%15s | %5s", getCustomerId(), getName(), strPremium, formatter.format(getBalance())) + "đ");
        List<Account> accounts = super.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(String.format("%s %10s |%10s |%29s", i + 1, accounts.get(i).getAccountNumber(), accounts.get(i).getAccountType(), formatter.format(accounts.get(i).getBalance()) + "đ"));

        }
    }

}
