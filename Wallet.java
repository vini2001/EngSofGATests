import java.util.ArrayList;
import java.util.Date;

public class Wallet {

    
    private ArrayList<Deposit> deposits = new ArrayList<>();

    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }
    
    public void listDeposits() {
        for (Deposit deposit : deposits) {
            System.out.println(deposit);
        }
    }

    public double totalValueAt(Date date) {
        double total = 0;
        for (Deposit deposit : deposits) {
            double value = deposit.valueAt(date);
            total += value;
        }
        return total;
    }

    public void listDepositsAt(Date date) {
        for (Deposit deposit : deposits) {
            System.out.println(deposit.valueAt(date));
        }
    }

    public void withdrawAt(double amount, Date date) {
        double total = totalValueAt(date);
        if (total < amount) {
            throw new IllegalArgumentException("Not enough money");
        }

        double remainingToWithdraw = amount;
        for (Deposit deposit : deposits) {
            double value = deposit.valueAt(date);
            if (value > remainingToWithdraw) {
                deposit.withdrawAt(remainingToWithdraw, date);
                break;
            } else {
                deposit.withdrawAt(deposit.valueAt(date), date);
                remainingToWithdraw -= value;
            }
        }
    }
}
