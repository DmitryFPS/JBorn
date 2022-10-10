package com.company.progectjborn.ServiceImpl;

import com.company.progectjborn.Service.TypeService;
import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {
    @Override
    public void initializeClientField(User user, Type type) {
        type.setClient(user);
    }
}