package ObjectOrientedDesign.ATMSystem;

import java.util.Collections;
import java.util.List;

/**
 * Created by dyj on 7/4/18.
 */
public class ATMMachine implements ATMFunctions{
    float balance; // if limited balance in ATM
    Session currentSession;

    public void takeDebitCard(DebitCard debitCard) {

        currentSession.login(debitCard);
    }

    @Override
    public List<Account> login(String passcode) {
        String truePasscode = currentSession.getCurrentDebitcard().getPasscode();
        if (!passcode.equals(truePasscode)) {
            return Collections.EMPTY_LIST;
        }
        return currentSession.getCurrentDebitcard().getAccounts();
    }

    @Override
    public void selectAccount(Account account) {
        currentSession.setAccount(account);
    }

    @Override
    public float checkBalance() {
        return currentSession.getAccount().getBalance();
    }

    @Override
    public void depositMoney(float amount) {
        if (amount <= 0) {
            return;
        }
        float currentBalance = currentSession.getAccount().getBalance();
        currentSession.getAccount().setBalance(currentBalance + amount);
    }

    @Override
    public float withdrawMoney(float amount) {
        float currentBalance = currentSession.getAccount().getBalance();
        if (amount <= 0 || amount > currentBalance) {
            return currentBalance;
        }
        currentSession.getAccount().setBalance(currentBalance - amount);
        return currentSession.getAccount().getBalance();
    }

    @Override
    public void logout() {
        currentSession.logout();
    }

}
