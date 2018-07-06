package ObjectOrientedDesign.ATMSystem;

import java.util.List;

/**
 * Created by dyj on 7/4/18.
 *
 ATM Design
 1. Clarify
 Assumption: input debit card, output money
 2. Input restrictions
 Debit Card only.
 3. Output restrictions
 Only times of 20 bucks
 4. Will output be overflow?
 No There are always enough money for ATM
 5. Will 1 card owns multiple accounts?
 Probably.

 Use cases:
 Take debit card
 Authorization/Log in
 Select Account
 Check balance
 Deposit money
 Withdraw money
 Logout

 */
public interface ATMFunctions {
    /**
     * Take in a debit card
     * @param debitCard
     */
    void takeDebitCard(DebitCard debitCard);

    /**
     * User login with passcode and return a list of accounts owned by the debit card.
     * @param passcode
     * @return
     */
    List<Account> login(String passcode);

    /**
     * User select an account.
     * @param account
     */
    void selectAccount(Account account);

    /**
     * User check balance of account.
     * @return
     */
    float checkBalance();

    /**
     * deposit money into account.
     * @param amount
     */
    void depositMoney(float amount);

    /**
     * User withdraw money from account.
     * @param amount
     * @return
     */
    float withdrawMoney(float amount);

    /**
     * User logout
     */
    void logout();
}
