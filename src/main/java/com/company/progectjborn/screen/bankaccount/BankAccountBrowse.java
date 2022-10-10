package com.company.progectjborn.screen.bankaccount;

import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("BankAccount.browse")
@UiDescriptor("bank-account-browse.xml")
@LookupComponent("bankAccountsTable")
public class BankAccountBrowse extends StandardLookup<BankAccount> {

    @Autowired
    private CollectionLoader<BankAccount> bankAccountsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User client = (User) currentAuthentication.getAuthentication().getPrincipal();
        bankAccountsDl.setParameter("client", client);
        bankAccountsDl.load();
    }
}