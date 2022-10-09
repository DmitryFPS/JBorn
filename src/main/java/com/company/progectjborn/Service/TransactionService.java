package com.company.progectjborn.Service;

import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    void performDataTransaction(Long amount, BankAccount fromAccId, BankAccount toAccId);

    List<Transaction> getTransaction(User client, Type type, Date startDate, Date endDate);
}