package com.company.progectjborn.Service;

import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;

public interface BankService {
    void initializeClientField(User user, BankAccount bankAccount);
}