import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Customer> customers = new ArrayList<>();

    public Customer openAccount(String customerId, String name, String accountNumber,
            String accountType, double initialBalance) {
        Account account;
        if (accountType == null) {
            throw new IllegalArgumentException("Choose Savings or Current account.");
        }

        if (accountType.equalsIgnoreCase("Savings")) {
            account = new SavingsAccount(accountNumber, initialBalance);
        } else if (accountType.equalsIgnoreCase("Current")) {
            account = new CurrentAccount(accountNumber, initialBalance);
        } else {
            throw new IllegalArgumentException("Choose Savings or Current account.");
        }

        Customer customer = new Customer(customerId, name, account);
        if (findCustomerById(customer.getCustomerId()) != null) {
            throw new IllegalArgumentException("That customer ID already exists.");
        }
        if (findAccount(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException("That account number already exists.");
        }

        customers.add(customer);
        return customer;
    }

    public Account findAccount(String accountNumber) {
        if (accountNumber == null) {
            return null;
        }
        for (Customer customer : customers) {
            Account account = customer.getAccount();
            if (account.getAccountNumber().equals(accountNumber.trim())) {
                return account;
            }
        }
        return null;
    }

    public Customer findCustomerByAccount(String accountNumber) {
        if (accountNumber == null) {
            return null;
        }
        for (Customer customer : customers) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber.trim())) {
                return customer;
            }
        }
        return null;
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}