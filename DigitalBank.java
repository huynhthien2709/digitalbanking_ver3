package vn.funix.fx17332.java.asm03.models;

import vn.funix.fx17332.java.asm02.models.Account;
import vn.funix.fx17332.java.asm02.models.Bank;
import vn.funix.fx17332.java.asm02.models.Customer;


public class DigitalBank extends Bank {


    public Customer getCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(String customerId, String name) {
        Customer newCustomer = new Customer();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                System.out.println("customer has existed");
                return;
            } else {
                newCustomer.setCustomerId(customerId);
                newCustomer.setName(name);
            }
        }
        customers.add(newCustomer);
        System.out.println(customers.size());
    }

    public void withdraw(String customerId, String accountNumer, double amount) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(getCustomerById(customerId))) {
                ((DigitalCustomer) customer).withdraw(accountNumer, amount);
                return;
            }
        }
    }


}
