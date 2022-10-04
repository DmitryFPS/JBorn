package com.company.progectjborn.screen.transaction;

import io.jmix.ui.screen.*;
import com.company.progectjborn.entity.Transaction;

@UiController("Transaction_.edit")
@UiDescriptor("transaction-edit.xml")
@EditedEntityContainer("transactionDc")
public class TransactionEdit extends StandardEditor<Transaction> {
}