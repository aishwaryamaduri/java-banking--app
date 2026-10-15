public abstract class Account {
    private final String accountNumber;
    private double balance;

    protected Account(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (!Double.isFinite(initialBalance) || initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber.trim();
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        validateTransactionAmount(amount);
        balance += amount;
    }

    public void withdraw(double amount) {
        validateTransactionAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance for this withdrawal.");
        }
        balance -= amount;
    }

    public abstract String getAccountType();

    private void validateTransactionAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }
}