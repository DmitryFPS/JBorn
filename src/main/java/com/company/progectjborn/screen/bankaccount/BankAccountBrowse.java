package com.company.progectjborn.screen.bankaccount;

import com.company.progectjborn.Service.AccountService;
import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("BankAccount.browse")
@UiDescriptor("bank-account-browse.xml")
@LookupComponent("bankAccountsTable")
public class BankAccountBrowse extends StandardLookup<BankAccount> {

    @Autowired
    private CollectionContainer<BankAccount> bankAccountsDc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInit(InitEvent event) {
        bankAccountsDc.setItems(accountService.selectAccountsByClient((User) currentAuthentication.getUser()));
    }
}