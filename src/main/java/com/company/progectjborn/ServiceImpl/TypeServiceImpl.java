package com.company.progectjborn.ServiceImpl;

import com.company.progectjborn.Service.TypeService;
import com.company.progectjborn.entity.Type;
import com.company.progectjborn.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initializeClientField(User user, Type type) {
        type.setClient(user);
    }

    @Override
    public List<Type> selectTypesByClient(User client) {
        return entityManager.createQuery("" +
                        "select t from Type_ as t " +
                        "join t.client as cl " +
                        "where cl.id=:clientId", Type.class)
                .setParameter("clientId", client.getId())
                .getResultList();
    }
}