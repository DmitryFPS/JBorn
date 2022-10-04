package com.company.progectjborn.listener;

import com.company.progectjborn.Service.BankService;
import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BankAccountEventListener {

    @Autowired
    private CurrentAuthentication authentication;

    @Autowired
    private BankService bankService;

    @EventListener
    public void onBankAccountSaving(EntitySavingEvent<BankAccount> event) {
        bankService.initializeClientField((User) authentication.getAuthentication().getPrincipal(), event.getEntity());
    }
}