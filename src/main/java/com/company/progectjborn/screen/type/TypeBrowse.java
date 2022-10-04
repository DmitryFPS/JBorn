package com.company.progectjborn.screen.type;

import com.company.progectjborn.Service.TypeService;
import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Type_.browse")
@UiDescriptor("type-browse.xml")
@LookupComponent("typesTable")
public class TypeBrowse extends StandardLookup<Type> {
    @Autowired
    private CollectionContainer<Type> typesDc;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInit(InitEvent event) {
        typesDc.setItems(typeService.selectTypesByClient((User) currentAuthentication.getUser()));
    }
}