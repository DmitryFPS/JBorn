package com.company.progectjborn.screen.report;

import com.company.progectjborn.Service.TransactionService;
import com.company.progectjborn.entity.Report;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.User;
import io.jmix.core.Metadata;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Report.browse")
@UiDescriptor("report-browse.xml")
@LookupComponent("reportsTable")
public class ReportBrowse extends StandardLookup<Report> {

    @Autowired
    private CollectionContainer<Transaction> transactionDc;

    @Autowired
    private InstanceContainer<Report> reportsInstDc;

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private Metadata metadata;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInit(InitEvent event) {
        Report report = metadata.create(Report.class);
        reportsInstDc.setItem(report);
    }

    @Subscribe("clickReportOk")
    public void onClickReportOkClick(Button.ClickEvent event) {
        Report report = reportsInstDc.getItem();
        List<Transaction> transactions = transactionService.getTransaction(
                (User) currentAuthentication.getAuthentication().getPrincipal(),
                report.getType(), report.getStartDate(), report.getEndDate());
        transactionDc.setItems(transactions);
    }
}