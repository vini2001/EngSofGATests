import java.util.Date;

public class Deposit {
    private Date deposiDate;
    private double amount;
    private double annualInterestRate;

    public Deposit(Date deposiDate, double amount, double annualInterestRate) {
        this.deposiDate = deposiDate;
        this.amount = amount;
        this.annualInterestRate = annualInterestRate;
    }

    public double getAmount() {
        return amount;
    }

    public void withdrawAt(double amount, Date date) {
        long days = (date.getTime() - deposiDate.getTime()) / (1000 * 60 * 60 * 24);
        double amountOnDay0 = amount / Math.pow(1 + annualInterestRate / 365, days);
        this.amount -= amountOnDay0;
    }

    private double getDailyInterestRate() {
        return Math.pow(1 + annualInterestRate, 1.0/365.0);
    }

    public double valueAt(Date date) {
        long days = (date.getTime() - deposiDate.getTime()) / (1000 * 60 * 60 * 24);
        double value = amount * Math.pow(getDailyInterestRate(), days);

        return value;
    }

    @Override
    public String toString() {
        return "Deposit{" + "deposiDate=" + deposiDate + ", amount=" + amount + ", annualInterestRate=" + annualInterestRate + '}';
    }
}
