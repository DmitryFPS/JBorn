package com.company.progectjborn.ServiceImpl;

import com.company.progectjborn.Service.BankService;
import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    @Override
    public void initializeClientField(User user, BankAccount bankAccount) {
        bankAccount.setClient(user);
    }
}