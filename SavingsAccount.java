public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.02;

    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }
}