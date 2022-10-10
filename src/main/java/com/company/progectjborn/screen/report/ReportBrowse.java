package com.company.progectjborn.screen.report;

import com.company.progectjborn.entity.Report;
import com.company.progectjborn.entity.Transaction;
import com.company.progectjborn.entity.User;
import io.jmix.core.Metadata;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Report.browse")
@UiDescriptor("report-browse.xml")
@LookupComponent("reportsTable")
public class ReportBrowse extends StandardLookup<Report> {

    @Autowired
    private CollectionLoader<Transaction> transactionDl;

    @Autowired
    private InstanceContainer<Report> reportsInstDc;

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
        User client = (User) currentAuthentication.getAuthentication().getPrincipal();
        transactionDl.setParameter("client", client);
        transactionDl.setParameter("type", report.getType());
        transactionDl.setParameter("startDate", report.getStartDate());
        transactionDl.setParameter("endDate", report.getEndDate());
        transactionDl.load();
    }
}