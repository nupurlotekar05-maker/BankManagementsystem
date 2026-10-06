package Bankmanagementsystem;



import java.util.*;

class UserAccount {
    private String name;
    private String email;
    private int pin;
    private String accountType;
    private double balance;
    private ArrayList<String> transactionHistory;

    public UserAccount(String name, String email, int pin, String accountType) {
        this.name = name;
        this.email = email;
        this.pin = pin;
        this.accountType = accountType;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getPin() { return pin; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public ArrayList<String> getTransactionHistory() { return transactionHistory; }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(" Deposited: Rs " + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(" Withdrawn: Rs " + amount);
        } else {
            System.out.println(" Insufficient balance!");
        }
    }

    public void transfer(UserAccount toAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            toAccount.balance += amount;
            transactionHistory.add(" Transferred Rs " + amount + " to " + toAccount.getName());
            toAccount.transactionHistory.add(" Received Rs " + amount + " from " + name);
        } else {
            System.out.println(" Insufficient balance!");
        }
    }

    public void addInterest() {
        if (accountType.equalsIgnoreCase("Savings")) {
            double interest = balance * 0.04;
            balance += interest;
            transactionHistory.add(" Interest added: Rs " + interest);
            System.out.println(" Interest of Rs " + interest + " added successfully!");
        } else {
            System.out.println(" Current account does not earn interest.");
        }
    }

    public void loanEMI(double principal, double rate, int months) {
        double monthlyRate = rate / 12 / 100;
        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                     (Math.pow(1 + monthlyRate, months) - 1);
        System.out.println("\n Your EMI Details:");
        System.out.println("Principal: Rs " + principal);
        System.out.println("Rate: " + rate + "%");
        System.out.println("Months: " + months);
        System.out.println("Calculated EMI: Rs " + String.format("%.2f", emi));
    }
}

public class Online1BankingSystem {
    Scanner sc = new Scanner(System.in);
    UserAccount users[] = new UserAccount[10];
    int count = 0;
    UserAccount currentUser;

    // ANSI Colors
    final String RESET = "\u001B[0m";
    final String CYAN = "\u001B[36m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String RED = "\u001B[31m";
    final String BLUE = "\u001B[34m";
    final String PURPLE = "\u001B[35m";

    // Progress animation
    private void progress(String message) {
        System.out.print(message);
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print("▓");
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(" ✅");
    }

    public void start() {
        int choice;
        do {
            System.out.println(CYAN + "\n═══════════════════════════════════════════" + RESET);
            System.out.println(PURPLE + "           WELCOME TO DIGITAL BANK  " + RESET);
            System.out.println(CYAN + "═══════════════════════════════════════════" + RESET);
            System.out.println("1️  Register (Create Account)");
            System.out.println("2️  Login");
            System.out.println("3️  Forgot PIN");
            System.out.println("4️  Admin: Show All Accounts");
            System.out.println("5️  Exit");
            System.out.println(CYAN + "───────────────────────────────────────────" + RESET);
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> forgotPIN();
                case 4 -> adminView();
                case 5 -> System.out.println(GREEN + "\n Thank you for banking with us!" + RESET);
                default -> System.out.println(RED + " Invalid choice! Try again." + RESET);
            }
        } while (choice != 5);
    }

    private void register() {
        sc.nextLine();
        System.out.println("\n" + BLUE + " Register (Create Account)" + RESET);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter 4-digit PIN: ");
        int pin = sc.nextInt();

        int typeChoice;
        String accountType = "";
        do {
            System.out.println("\nSelect Account Type:");
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.print("Enter choice: ");
            typeChoice = sc.nextInt();
            if (typeChoice == 1) accountType = "Savings";
            else if (typeChoice == 2) accountType = "Current";
            else System.out.println(RED + "Invalid choice! Please select again." + RESET);
        } while (typeChoice != 1 && typeChoice != 2);

        progress("Creating your account ");
        users[count++] = new UserAccount(name, email, pin, accountType);
        System.out.println(GREEN + "\n " + accountType + " account created successfully for " + name + "!" + RESET);
    }

    private void login() {
        sc.nextLine();
        System.out.println("\n" + YELLOW + " Login" + RESET);
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        progress("Verifying credentials ");

        for (int i = 0; i < count; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPin() == pin) {
                currentUser = users[i];
                System.out.println(GREEN + "\n Login successful! Welcome, " + currentUser.getName() + "!" + RESET);
                menu();
                return;
            }
        }
        System.out.println(RED + " Invalid email or PIN!" + RESET);
    }

    private void forgotPIN() {
        sc.nextLine();
        System.out.println("\n" + BLUE + " Forgot PIN" + RESET);
        System.out.print("Enter your registered email: ");
        String email = sc.nextLine();

        progress("Searching your account ");
        for (int i = 0; i < count; i++) {
            if (users[i].getEmail().equals(email)) {
                System.out.println(GREEN + " Your PIN is: " + users[i].getPin() + RESET);
                return;
            }
        }
        System.out.println(RED + " No account found with this email." + RESET);
    }

    private void menu() {
        int choice;
        do {
            System.out.println("\n" + CYAN + "─────────────── BANKING MENU ───────────────" + RESET);
            System.out.println("1️ Deposit Money");
            System.out.println("2️ Withdraw Money");
            System.out.println("3️ Transfer Money");
            System.out.println("4️ View Balance");
            System.out.println("5️ Transaction History");
            System.out.println("6️ Add Interest (Savings only)");
            System.out.println("7️ Loan EMI Calculator");
            System.out.println("8️ Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: Rs ");
                    double dep = sc.nextDouble();
                    progress("Processing deposit ");
                    currentUser.deposit(dep);
                    System.out.println(GREEN + " Deposited successfully!" + RESET);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: Rs ");
                    double w = sc.nextDouble();
                    progress("Processing withdrawal ");
                    currentUser.withdraw(w);
                }
                case 3 -> transferMoney();
                case 4 -> {
                    progress("Fetching balance ");
                    System.out.println(" Current Balance: Rs " + currentUser.getBalance());
                }
                case 5 -> showTransactionHistory();
                case 6 -> currentUser.addInterest();
                case 7 -> loanCalculator();
                case 8 -> {
                    progress("Logging out ");
                    System.out.println(YELLOW + " Logged out successfully!" + RESET);
                }
                default -> System.out.println(RED + " Invalid choice!" + RESET);
            }
        } while (choice != 8);
    }

    private void transferMoney() {
        sc.nextLine();
        System.out.print("Enter recipient email: ");
        String email = sc.nextLine();
        UserAccount recipient = null;
        for (int i = 0; i < count; i++) {
            if (users[i].getEmail().equals(email)) {
                recipient = users[i];
                break;
            }
        }
        if (recipient != null) {
            System.out.print("Enter amount to transfer: Rs ");
            double amt = sc.nextDouble();
            progress("Transferring amount ");
            currentUser.transfer(recipient, amt);
            System.out.println(GREEN + " Transfer successful!" + RESET);
        } else {
            System.out.println(RED + " Recipient not found!" + RESET);
        }
    }

    private void showTransactionHistory() {
        ArrayList<String> history = currentUser.getTransactionHistory();
        System.out.println("\n Transaction History:");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : history) System.out.println("• " + t);
        }
    }

    private void loanCalculator() {
        System.out.print("Enter loan principal: Rs ");
        double p = sc.nextDouble();
        System.out.print("Enter annual interest rate (%): ");
        double r = sc.nextDouble();
        System.out.print("Enter number of months: ");
        int m = sc.nextInt();
        progress("Calculating EMI ");
        currentUser.loanEMI(p, r, m);
    }

    private void adminView() {
        System.out.println("\n" + PURPLE + "‍Admin Panel - All Accounts" + RESET);
        if (count == 0) {
            System.out.println(RED + " No accounts available yet!" + RESET);
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + users[i].getName() + " | " + users[i].getEmail() +
                               " | " + users[i].getAccountType() + " | Balance: Rs " + users[i].getBalance());
        }
    }

    public static void main(String[] args) {
        Online1BankingSystem bank = new Online1BankingSystem();
        bank.start();
    }
}


