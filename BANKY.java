/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.io.*;
import java.util.*;

// Account class to represent individual bank accounts
class Account implements Serializable {
    private String accountId;
    private String accountHolderName;
    private double balance;

    // Constructor
    public Account(String accountId, String accountHolderName, double balance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // Getter methods
    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw funds from the account
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false; // Insufficient funds
        }
    }

    // Method to transfer funds from this account to another account
    public boolean transfer(Account receiver, double amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
            return true;
        } else {
            return false; // Transfer failed due to insufficient funds
        }
    }
}

// Bank class to manage multiple accounts
class Bank {
    private ArrayList<Account> accounts;
    private static final String FILENAME = "accounts.dat";

    // Constructor
    public Bank() {
        accounts = new ArrayList<>();
        // Load accounts from file
        loadAccounts();
    }

    // Method to add a new account to the bank
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Method to get an account by account ID
    public Account getAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null; // Account not found
    }

    // Method to save accounts to file
    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load accounts from file
    private void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            accounts = (ArrayList<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File doesn't exist or empty, or failed to read accounts
            // Do nothing, accounts will be initialized as empty
        }
    }

    // Method to perform a fund transfer between two accounts
    public boolean transferFunds(String senderId, String receiverId, double amount) {
        Account sender = getAccountById(senderId);
        Account receiver = getAccountById(receiverId);
        if (sender != null && receiver != null) {
            return sender.transfer(receiver, amount);
        } else {
            return false; // One or both accounts not found
        }
    }

    // Method to close the bank (save accounts to file)
    public void closeBank() {
        saveAccounts();
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to BankY!");

        // Example usage
        System.out.println("Creating accounts...");
        System.out.print("Enter account ID for account 1: ");
        String accountId1 = scanner.nextLine();
        System.out.print("Enter account holder name for account 1: ");
        String accountHolderName1 = scanner.nextLine();
        System.out.print("Enter initial balance for account 1: ");
        double balance1 = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account1 = new Account(accountId1, accountHolderName1, balance1);
        bank.addAccount(account1);

        System.out.print("Enter account ID for account 2: ");
        String accountId2 = scanner.nextLine();
        System.out.print("Enter account holder name for account 2: ");
        String accountHolderName2 = scanner.nextLine();
        System.out.print("Enter initial balance for account 2: ");
        double balance2 = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account2 = new Account(accountId2, accountHolderName2, balance2);
        bank.addAccount(account2);

        // Deposit and withdraw example
        System.out.println("\nPerforming transactions...");
        System.out.print("Enter amount to deposit into account 1: ");
        double depositAmount = scanner.nextDouble();
        account1.deposit(depositAmount);

        System.out.print("Enter amount to withdraw from account 2: ");
        double withdrawAmount = scanner.nextDouble();
        account2.withdraw(withdrawAmount);

        // Transfer example
        System.out.print("Enter amount to transfer from account 1 to account 2: ");
        double transferAmount = scanner.nextDouble();
        bank.transferFunds(accountId1, accountId2, transferAmount);

        // Close the bank (save accounts to file)
        bank.closeBank();

        // Closing scanner
        scanner.close();
        System.out.println("\nBankY operations completed.");
    }
}
