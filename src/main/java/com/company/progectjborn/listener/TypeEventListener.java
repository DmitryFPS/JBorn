package com.company.progectjborn.listener;

import com.company.progectjborn.Service.TypeService;
import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TypeEventListener {

    @Autowired
    private CurrentAuthentication authentication;

    @Autowired
    private TypeService typeService;

    @EventListener
    public void onTypeSaving(EntitySavingEvent<Type> event) {
        typeService.initializeClientField((User) authentication.getAuthentication().getPrincipal(), event.getEntity());
    }
}