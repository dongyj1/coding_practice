package ObjectOrientedDesign.ATMSystem;

/**
 * Created by dyj on 7/4/18.
 */
public class Session {

    enum Status {
        Login,
        Logout,
        Other
    }

    private Status status;
    private DebitCard debitCard;
    private Account account;

    public boolean isLogin() {
        return status == Status.Login;
    }

    public void login(DebitCard debitCard) {
        status = Status.Login;
        this.debitCard = debitCard;
    }

    public void logout() {
        status = Status.Logout;
        this.debitCard = null;
    }

    public DebitCard getCurrentDebitcard() {
        return this.debitCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
