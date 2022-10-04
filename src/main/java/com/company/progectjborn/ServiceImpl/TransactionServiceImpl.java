package com.company.progectjborn.ServiceImpl;

import com.company.progectjborn.Service.TransactionService;
import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.User;
import com.company.progectjborn.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void performDataTransaction(Long amount, BankAccount fromAccId, BankAccount toAccId) {
        if (fromAccId == null && toAccId == null) {
            checkCorrectnessOfFilling();
        } else if (fromAccId != null && toAccId != null) {
            transferFromAccountToAccount(amount, fromAccId, toAccId);
        } else if (toAccId != null) {
            transferFunds(amount, toAccId);
        } else {
            withdrawFunds(amount, fromAccId);
        }
    }

    @Override
    public List<Transaction> selectTransactionsByClient(User client) {
        List<Transaction> list = entityManager.createQuery("" +
                        "select t from Transaction_ as t " +
                        "join t.type as tt " +
                        "join tt.client as cl " +
                        "where cl.id=:clientId", Transaction.class)
                .setParameter("clientId", client.getId())
                .getResultList();
        return list;
    }

    private void transferFunds(Long amount, BankAccount toAccId) {
        entityManager.createQuery("update BankAccount as ba set ba.amount=(ba.amount + :amount) where ba.id=:bankAccountToAccId")
                .setParameter("amount", amount)
                .setParameter("bankAccountToAccId", toAccId.getId()).executeUpdate();
    }

    private void withdrawFunds(Long amount, BankAccount fromAccId) {
        checkThereAreEnoughFunds(amount, fromAccId);
        entityManager.createQuery("update BankAccount as ba set ba.amount=(ba.amount - :amount) where ba.id=:bankAccountIFromAccId")
                .setParameter("amount", amount)
                .setParameter("bankAccountIFromAccId", fromAccId.getId()).executeUpdate();
    }

    private void transferFromAccountToAccount(Long amount, BankAccount fromAccId, BankAccount toAccId) {
        transferFunds(amount, toAccId);
        withdrawFunds(amount, fromAccId);
    }

    private void checkThereAreEnoughFunds(Long amount, BankAccount fromAccId) {
        if (amount > fromAccId.getAmount()) {
            throw new CustomException("There are not enough funds in the account");
        }
    }

    private void checkCorrectnessOfFilling() {
        throw new CustomException("Incorrectly filled in the parameters");
    }
}