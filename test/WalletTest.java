import java.util.Calendar;
import java.util.Date;

import org.junit.*;

import junit.framework.TestCase;

public class WalletTest extends TestCase {

    Wallet wallet;

    private void initWallet() {
        wallet = new Wallet();
        wallet.addDeposit(new Deposit(new Date(), 2000, 0.05));
        wallet.addDeposit(new Deposit(new Date(), 1000, 0.10));
        wallet.addDeposit(new Deposit(new Date(), 1000, 0.03));
    }

    @Test
    public void testAddDay0Balance() {
        initWallet();
        Assert.assertEquals(4000, wallet.totalValueAt(new Date()), 0.001);
    }
    

    @Test
    public void testAfter1YearBalance() {
        initWallet();
        Date date = new Date();
        //sum 1 yar to date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 365);
        date = cal.getTime();

        Assert.assertEquals(4230, wallet.totalValueAt(date), 0.001);
    }

    @Test
    public void testOverWithdraw() {
        initWallet();
        try {
            wallet.withdrawAt(5000, new Date());
            Assert.fail("Should have thrown an exception");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Not enough money", e.getMessage());
        }
    }

    @Test
    public void testNormalWithdraw() {
        initWallet();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 100);
        Date date = cal.getTime();
        wallet.withdrawAt(1000, date);
        Assert.assertEquals(3061.8317606, wallet.totalValueAt(date), 0.001);
    }
}