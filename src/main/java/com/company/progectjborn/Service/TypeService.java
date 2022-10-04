package com.company.progectjborn.Service;

import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;

import java.util.List;

public interface TypeService {
    void initializeClientField(User user, Type type);

    List<Type> selectTypesByClient(User client);
}