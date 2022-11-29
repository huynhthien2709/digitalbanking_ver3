package vn.funix.fx17332.java.asm03;

import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Customer;
import vn.funix.fx17332.java.asm03.models.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "002709000001";
    private static final String CUSTOMER_NAME = "THIEN";

    private static final DigitalCustomer newDigiCustomer = new DigitalCustomer();
    private static Utils utils = new Utils();
    private  static  DecimalFormat formatter = new DecimalFormat("#,###");

    public static void main(String[] args) {
        newDigiCustomer.setCustomerId(CUSTOMER_ID);
        newDigiCustomer.setName(CUSTOMER_NAME);
        activeBank.addCustomer(newDigiCustomer);
        displayMainMenu();
        boolean quit;
        int choice;
        do {
            quit = false;
            do {
                try {
                    choice = sc.nextInt();
                    break;
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Chuc nang khong dung dinh dang. Vui long nhap lai: ");
                }
            } while (true);
            switch (choice) {
                case 1:
                    customerInfo();
                    break;
                case 2:
                    addSavingsAccount();
                    break;
                case 3:
                    addLoanAccount();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    transactionHistory();
                    break;
                case 0:
                    quit = true;
                    System.out.println("Ban da thoat khoi chuong trinh");
                    break;
                default:
                    System.out.println("Khong co chuc nang nay. Vui long chon dung chuc nang");
                    break;
            }
        } while (choice != 0);
    }




    public static void displayMainMenu() {
        System.out.println("+----------+-----------------------------------+---------+");
        System.out.println("| NGAN HANG SO  |  FX17332@v3.0.0                        |");
        System.out.println("+----------+-----------------------------------+---------+");
        System.out.println("| 1. Thong tin khach hang                                |");
        System.out.println("| 2. Them tai khoan ATM                                  |");
        System.out.println("| 3. Them tai khoan tin dung                             |");
        System.out.println("| 4. Rut tien                                            |");
        System.out.println("| 5. Lich su giao dich                                   |");
        System.out.println("| 0. Thoat                                               |");
        System.out.println("+----------+-----------------------------------+---------+");
        System.out.print("Chuc nang: ");

    }

    public static void customerInfo() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
        System.out.println("Chuc nang: ");

    }

    private static void addSavingsAccount() {
        SavingAccount newSavingAccount = new SavingAccount();
        System.out.println("Nhap ma so tai khoan gom 6 chu so: ");
        String accNum = "";
        String accountType = "SAVINGS";
        boolean check = false;
        do {
            boolean valid = true;
            accNum = sc.next();
            if (!validNumber(accNum)) {
                valid = false;
            }
            for (Account account : newDigiCustomer.getAccounts()) {
                if (account.getAccountNumber().equals(accNum) && account.getAccountNumber() != null) {
                    System.out.println("So tai khoan da ton tai.");
                    valid = false;
                }
            }
            if (valid) {
                check = true;
                newSavingAccount.setAccountNumber(accNum);
                newSavingAccount.setAccountType(accountType);
            }
        } while (!check);
        System.out.println("Nhap so du: ");
        double balance;
        do {
            balance = sc.nextDouble();
            try {
                if (balance < 50000) {
                    System.out.println("So du khong duoc nho hon 50,000");
                } else {
                    newSavingAccount.setBalance(balance);
                }
            } catch (Exception e) {
                System.out.println("So du khong hop le");
            }
        } while (balance < 50000);
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        customer.addAccount(newSavingAccount);
        System.out.println("So tai khoan " + newSavingAccount.getAccountNumber() + " co " + formatter.format(balance) + " trong tai khoan");
        System.out.println("Chuc nang: ");

    }

    private static void addLoanAccount() {
        LoanAccount newLoanaccount = new LoanAccount();
        System.out.println("Nhap so tai khoan gom 6 chu so: ");
        String loanNum = "";
        String accountType = "LOAN";
        boolean check = false;
        do {
            boolean valid = true;
            loanNum = sc.next();
            try {
                if (!validNumber(loanNum)) {
                    valid = false;
                }
                for (Account account : newDigiCustomer.getAccounts()) {
                    if (account.getAccountNumber().equals(loanNum) && account.getAccountNumber() != null) {
                        System.out.println("So tai khoan da ton tai.");
                        valid = false;
                    }
                }
                if (valid) {
                    check = true;
                    newLoanaccount.setAccountNumber(loanNum);
                    newLoanaccount.setAccountType(accountType);
                }

            } catch (Exception e) {
                System.out.println("So tai khoan khong hop le. Vui long nhap lại: ");
            }
        } while (!check);
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        customer.addAccount(newLoanaccount);
        System.out.println("So tai khoan " + newLoanaccount.getAccountNumber() + " da duoc them vao he thong.");
        System.out.println("Chuc nang: ");
    }

    public static boolean validNumber(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai:  ");
                return false;
            }
        }
        if (id.length() != 6) {
            System.out.println("Do dai so tai khoan khong hop le. Vui long nhap lai: ");
            return false;
        }
        return true;
    }

    private static void withdraw() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        System.out.println("Nhap so tai khoan: ");
        String accNum = "";
        boolean check = false;
        do {
            boolean valid = false;
            boolean valid1 = false;
            try {
                accNum = sc.next();
                if (!validNumber(accNum)) {
                    valid = true;
                } else {
                    for (Account account : customer.getAccounts()) {
                        if (account.getAccountNumber().equals(accNum)) {
                            valid1 = true;
                            check = false;
                        }
                    }
                    if (valid1 == false) {
                        System.out.println("So tai khoan khong ton tai. Vui long nhap lai: ");
                    }
                }
                if (valid || !valid1) {
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("So tai khoan khong hop le");
            }
        } while (check);
        System.out.println("Nhap so tien: ");
        double amount = sc.nextDouble();
        ((DigitalCustomer) customer).withdraw(accNum, amount);
    }
    private static void transactionHistory() {
         String customerId = CUSTOMER_ID;
         if(!customerId.equals("")){
             for (Customer customer : activeBank.getCustomers()){
                 if(customer.getCustomerId().equals(customerId)){
                     System.out.println(utils.getDivider());
                     System.out.println("|  LICH SU GIAO DICH                                 |");
                     System.out.println(utils.getDivider());
                     customer.displayInformation();
                     System.out.println(String.format("%s  | %15s |%20s", "So tai khoan", "Withdraw", "Time"));
                     for (Account account : customer.getAccounts()){
                         for (Transaction transaction : account.getTransaction()){
                             System.out.println(String.format("%s        | %15s |%27s", account.getAccountNumber(), formatter.format(transaction.getAmount()), transaction.getTime()));
                         }
                     }
                 }
             }
         }
        System.out.println("Chuc nang: ");
    }
}
