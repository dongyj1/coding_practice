package ObjectOrientedDesign.ATMSystem;

/**
 * Created by dyj on 7/4/18.
 */
public class Account {
    private long id;
    private DebitCard debitCard;
    private User user;
    private float balance;

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public long getId() {

        return id;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public User getUser() {
        return user;
    }

    public float getBalance() {
        return balance;
    }
}
