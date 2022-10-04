package com.company.progectjborn.listener;

import com.company.progectjborn.Service.TransactionService;
import com.company.progectjborn.entity.Transaction;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventListener {

    @Autowired
    private TransactionService transactionService;

    @EventListener
    public void onTransactionSaving(EntitySavingEvent<Transaction> event) {
        Transaction transaction = event.getEntity();
        transactionService.performDataTransaction(transaction.getTransferAmount(),
                transaction.getFromAccId(), transaction.getToAccId());
    }
}