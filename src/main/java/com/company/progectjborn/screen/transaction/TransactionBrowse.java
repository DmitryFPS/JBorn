package com.company.progectjborn.screen.transaction;

import com.company.progectjborn.Service.TransactionService;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Transaction_.browse")
@UiDescriptor("transaction-browse.xml")
@LookupComponent("transactionsTable")
public class TransactionBrowse extends StandardLookup<Transaction> {

    @Autowired
    private CollectionContainer<Transaction> transactionsDc;
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInit(InitEvent event) {
        transactionsDc.setItems(transactionService.selectTransactionsByClient((User) currentAuthentication.getUser()));
    }
}