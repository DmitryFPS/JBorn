package com.company.progectjborn.screen.transaction;

import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Transaction_.browse")
@UiDescriptor("transaction-browse.xml")
@LookupComponent("transactionsTable")
public class TransactionBrowse extends StandardLookup<Transaction> {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private CollectionLoader<Transaction> transactionsDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User client = (User) currentAuthentication.getAuthentication().getPrincipal();
        transactionsDl.setParameter("client", client);
        transactionsDl.load();
    }
}