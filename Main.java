import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Wallet wallet = new Wallet();
        wallet.addDeposit(new Deposit(new Date(), 1000, 0.01));
        wallet.addDeposit(new Deposit(new Date(), 1000, 0.02));
        wallet.addDeposit(new Deposit(new Date(), 1000, 0.03));

        System.out.println(wallet.totalValueAt(new Date()));
    }
}