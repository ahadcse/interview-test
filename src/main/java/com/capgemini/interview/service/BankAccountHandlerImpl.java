package com.capgemini.interview.service;

import com.capgemini.interview.exceptions.AccountCreationException;
import com.capgemini.interview.exceptions.DepositException;
import com.capgemini.interview.exceptions.WithdrawalException;
import com.capgemini.interview.exceptions.result.Result;
import com.capgemini.interview.exceptions.result.Results;
import com.capgemini.interview.model.bank.Transaction;
import com.capgemini.interview.model.bank.TransactionType;
import com.capgemini.interview.model.response.BankResponse;
import com.capgemini.interview.model.bank.Account;
import com.capgemini.interview.store.DataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.capgemini.interview.exceptions.result.Results.results;

/**
 * Implements IBankAccountHandler interface
 */
public class BankAccountHandlerImpl implements IBankAccountHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(BankAccountHandlerImpl.class);

    private DataStore dao = DataStore.getInstance();
    private List<IBankAccountEventListener> accountEventListeners = new ArrayList<>();

    /**
     * Class constructor
     */
    public BankAccountHandlerImpl() {
        super();
    }

    /**
     * Support for event listener
     *
     * @param accountEventListener IBankAccountEventListener object
     */
    public void addEventListener(IBankAccountEventListener accountEventListener) {
        accountEventListeners.add(accountEventListener);
    }

    public BankResponse createBankAccount(String accountName) {
        if (StringUtils.isEmpty(accountName)) {
            LOGGER.info("Rejected: Empty account name");
            throw new AccountCreationException("Rejected: Empty account name", Results.results(Result.EMPTY_ACCOUNT_NAME));
        }
        try {
            Account account = new Account();
            account.setAccountName(accountName);
            return new BankResponse(dao.addAccount(account), accountName, 0.0);
        } catch (Exception ex) {
            throw new AccountCreationException("Server Internal Error", Results.results(Result.DB_ERROR));
        }
    }

    public BankResponse withdrawal(int bankAccountId, float amount) {
        if (amount < 0) {
            LOGGER.info("Rejected: Account " + bankAccountId + ": Illegal amount: " + amount);
            throw new WithdrawalException("Rejected: Account " + bankAccountId + ": Illegal amount: " + amount, Results.results(Result.NEGATIVE_AMOUNT_ERROR));
        }

        Account account = dao.getAccount(bankAccountId);
        if (account == null) {
            LOGGER.warn("Rejected: Account " + bankAccountId + " does not exists");
            throw new WithdrawalException("Rejected: Account " + bankAccountId + " does not exists", Results.results(Result.ACCOUNT_NOT_AVAILABLE));
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setType(TransactionType.WITHDRAW);
        dao.addTransaction(transaction);

        float balance = getBalance(account.getAccountId());
        tryNotifyForNegativeBalance(bankAccountId, balance);
        LOGGER.info("Transaction: Account " + bankAccountId + ": withdraw: $" + amount + ", balance: $" + balance);
        return new BankResponse(bankAccountId, transaction.getAccount().getAccountName(), amount, balance);
    }

    private void tryNotifyForNegativeBalance(int bankAccountId, float balance) {
        if (balance < 0.0f) {
            for (IBankAccountEventListener accountEventListener : accountEventListeners) {

                accountEventListener.onNegativeAccountBalance(bankAccountId, balance);
            }
        }
    }

    public BankResponse deposit(int bankAccountId, float amount) {
        if (amount < 0) {
            LOGGER.info("Rejected: Account " + bankAccountId + ": Illegal amount: " + amount);
            throw new DepositException("Rejected: Account " + bankAccountId + ": Illegal amount: " + amount, Results.results(Result.NEGATIVE_AMOUNT_ERROR));
        }

        Account account = dao.getAccount(bankAccountId);
        if (account == null) {
            LOGGER.warn("Rejected: Account " + bankAccountId + " does not exists");
            throw new DepositException("Rejected: Account " + bankAccountId + " does not exists", Results.results(Result.ACCOUNT_NOT_AVAILABLE));
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setType(TransactionType.DEPOSITE);
        dao.addTransaction(transaction);

        float balance = getBalance(account.getAccountId());
        LOGGER.info("Transaction: Account " + bankAccountId + ": deposit: $" + amount + ", balance: $" + balance);
        return new BankResponse(bankAccountId, transaction.getAccount().getAccountName(), amount, balance);
    }

    @Override
    public float getBalance(int bankAccountId) {
        return dao.getAccountBalance(bankAccountId);
    }
}
