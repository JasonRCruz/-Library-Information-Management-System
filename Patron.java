public class Patron {
    private String name;
    private String email;
    private int idNumber;
    private double balance;

    public Patron(String name, String email, int idNumber, double balance) {
        this.name = name;
        this.email = email;
        this.idNumber = idNumber;
        this.balance = balance;
    }

    public void adjustBalance(double amount) {
        this.balance += amount;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean equals(Object other) {
        if (other instanceof Patron) {
            Patron otherPatron = (Patron) other;
            return this.idNumber == otherPatron.idNumber;
        } else if (other instanceof Integer) {
            return this.idNumber == (int) other;
        }
        return false;
    }
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", ID: " + idNumber + ", Balance: $" + String.format("%.2f", balance) + ".";
    }
}
