package com.company.progectjborn.screen.type;

import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Type_.browse")
@UiDescriptor("type-browse.xml")
@LookupComponent("typesTable")
public class TypeBrowse extends StandardLookup<Type> {

    @Autowired
    private CollectionLoader<Type> typesDl;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User client = (User) currentAuthentication.getAuthentication().getPrincipal();
        typesDl.setParameter("client", client);
        typesDl.load();
    }
}