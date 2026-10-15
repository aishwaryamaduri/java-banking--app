import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final Bank bank = new Bank();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            Integer choice = readInteger("Choose an option: ");
            if (choice == null) {
                break;
            }

            switch (choice) {
                case 1:
                    createCustomerAndAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewDetails();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using the banking application.");
                    break;
                default:
                    System.out.println("Error: Enter a menu number from 1 to 6.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n=== Console Banking Application ===");
        System.out.println("1. Create customer and open account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Check balance");
        System.out.println("5. View customer and account details");
        System.out.println("6. Exit");
    }

    private void createCustomerAndAccount() {
        String customerId = readRequiredText("Customer ID: ");
        String name = readRequiredText("Customer name: ");
        String accountNumber = readRequiredText("New account number: ");
        if (customerId == null || name == null || accountNumber == null) {
            return;
        }

        System.out.println("1. Savings account");
        System.out.println("2. Current account");
        Integer typeChoice = readInteger("Choose account type: ");
        if (typeChoice == null) {
            return;
        }
        String accountType;
        if (typeChoice == 1) {
            accountType = "Savings";
        } else if (typeChoice == 2) {
            accountType = "Current";
        } else {
            System.out.println("Error: Choose 1 for Savings or 2 for Current.");
            return;
        }

        Double initialBalance = readAmount("Opening balance (zero or more): ");
        if (initialBalance == null) {
            return;
        }
        try {
            Customer customer = bank.openAccount(customerId, name, accountNumber,
                    accountType, initialBalance);
            System.out.println("Account created successfully for " + customer.getName() + ".");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private void depositMoney() {
        Account account = askForAccount();
        if (account == null) {
            return;
        }
        Double amount = readAmount("Deposit amount: ");
        if (amount == null) {
            return;
        }
        try {
            account.deposit(amount);
            System.out.printf("Deposit successful. New balance: %.2f%n", account.getBalance());
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private void withdrawMoney() {
        Account account = askForAccount();
        if (account == null) {
            return;
        }
        Double amount = readAmount("Withdrawal amount: ");
        if (amount == null) {
            return;
        }
        try {
            account.withdraw(amount);
            System.out.printf("Withdrawal successful. New balance: %.2f%n", account.getBalance());
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private void checkBalance() {
        Account account = askForAccount();
        if (account != null) {
            System.out.printf("Balance for account %s: %.2f%n",
                    account.getAccountNumber(), account.getBalance());
        }
    }

    private void viewDetails() {
        Account account = askForAccount();
        if (account == null) {
            return;
        }
        Customer customer = bank.findCustomerByAccount(account.getAccountNumber());
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Customer name: " + customer.getName());
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Account type: " + account.getAccountType());
        System.out.printf("Balance: %.2f%n", account.getBalance());
        if (account instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) account;
            System.out.printf("Estimated interest at 2%%: %.2f%n",
                    savingsAccount.calculateInterest());
        }
    }

    private Account askForAccount() {
        String accountNumber = readRequiredText("Account number: ");
        if (accountNumber == null) {
            return null;
        }
        Account account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: No account found with that number.");
        }
        return account;
    }

    private String readRequiredText(String prompt) {
        while (true) {
            String value = readLine(prompt);
            if (value == null) {
                return null;
            }
            if (!value.trim().isEmpty()) {
                return value.trim();
            }
            System.out.println("Error: This value cannot be empty.");
        }
    }

    private Integer readInteger(String prompt) {
        String value = readLine(prompt);
        if (value == null) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            System.out.println("Error: Enter a whole number.");
            return -1;
        }
    }

    private Double readAmount(String prompt) {
        String value = readLine(prompt);
        if (value == null) {
            return null;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException exception) {
            System.out.println("Error: Enter a valid amount.");
            return Double.NaN;
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }
}