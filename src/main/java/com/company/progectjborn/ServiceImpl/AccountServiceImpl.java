package com.company.progectjborn.ServiceImpl;

import com.company.progectjborn.Service.AccountService;
import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BankAccount> selectAccountsByClient(User client) {
        return entityManager.createQuery("" +
                        "select ba from BankAccount as ba " +
                        "join ba.client as cl " +
                        "where cl.id=:clientId", BankAccount.class)
                .setParameter("clientId", client.getId())
                .getResultList();
    }
}