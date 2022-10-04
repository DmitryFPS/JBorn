package com.company.progectjborn.Service;

import com.company.progectjborn.entity.BankAccount;
import com.company.progectjborn.entity.User;

import java.util.List;

public interface AccountService {
    List<BankAccount> selectAccountsByClient(User client);
}