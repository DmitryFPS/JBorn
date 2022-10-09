package com.company.progectjborn.ServiceImpl;

import com.company.progectjborn.Service.TransactionService;
import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;
import com.company.progectjborn.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
    public List<Transaction> getTransaction(User client, Type type, Date startDate, Date endDate) {
        return entityManager.createQuery(
                        "select t " +
                                "from Transaction_ as t " +
                                "join t.type as ty " +
                                "join ty.client as cl " +
                                "where cl =:client and t.type =:type and t.createDate between :startDate and :endDate ",
                        Transaction.class)
                .setParameter("client", client)
                .setParameter("type", type)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
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