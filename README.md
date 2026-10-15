# Console Banking Application

A beginner-friendly, menu-driven banking application written in Java. It demonstrates object-oriented programming with customers and savings or current accounts. Data is stored in memory and is cleared when the program exits.

## Features

- Create a customer and open a Savings or Current account.
- Deposit money, withdraw money, and check the balance.
- View customer and account details.
- Reject invalid amounts, duplicate customer IDs and account numbers, and account numbers that do not exist.
- Prevent withdrawals beyond the available balance. Current accounts do not have an overdraft feature.
- Show an estimated 2% interest amount for Savings accounts. This estimate is informational and is not added to the account balance.

## Object-Oriented Programming

- **Encapsulation:** `Account` keeps the account number and balance private. `Customer` keeps its ID, name, and account private. Public methods provide controlled access and transactions.
- **Inheritance:** `SavingsAccount` and `CurrentAccount` extend the abstract `Account` class and reuse its common behavior.
- **Polymorphism:** `Bank` finds and returns accounts as `Account` references. The menu can deposit, withdraw, and display account types through those references; each subclass provides its own `getAccountType()` implementation.
- **Abstraction:** `Account` is abstract. It defines shared account behavior and requires subclasses to implement `getAccountType()`.

## Project Files

- `Account.java` - abstract base class and shared account operations.
- `SavingsAccount.java` - savings account type and interest estimate.
- `CurrentAccount.java` - current account type.
- `Customer.java` - customer details and associated account.
- `Bank.java` - in-memory customer and account management.
- `Main.java` - console menu and user interaction.

## Requirements

Install a Java Development Kit (JDK) 8 or newer and make sure `java` and `javac` are available in the terminal.

## Compile and Run

Open this project folder in VS Code. Open the integrated terminal and run these commands from the folder containing the Java files:

```powershell
javac *.java
java Main
```

The `.gitignore` file excludes the generated `.class` files, so they will not be included when you upload the project.

## Try the Application

1. Choose `1` to create a customer and account. Enter a customer ID such as `C001`, a name such as `Alex`, an account number such as `A100`, choose `1` for Savings, and enter an opening balance such as `100`.
2. Choose `2`, enter `A100`, and deposit `50`.
3. Choose `3`, enter `A100`, and withdraw `25`.
4. Choose `4` and enter `A100` to see the updated balance.
5. Choose `5` and enter `A100` to see customer and account details.
6. Repeat account creation with a different customer and account number, choosing `2` for Current. Try withdrawing more than its balance to see the insufficient-balance validation.
7. Choose `6` to exit.

You can also try entering a negative or zero deposit, using an unknown account number, or entering nonnumeric menu input to see the error handling.

## Upload to GitHub

Create an empty repository on GitHub, then replace the URL below with your repository URL and run these commands from the project folder:

```powershell
git init
git add Account.java SavingsAccount.java CurrentAccount.java Customer.java Bank.java Main.java README.md .gitignore
git commit -m "Complete console banking application"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
git push -u origin main
```