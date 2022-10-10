package com.company.progectjborn.Service;

import com.company.progectjborn.entity.BankAccount;

public interface TransactionService {
    void performDataTransaction(Long amount, BankAccount fromAccId, BankAccount toAccId);
}