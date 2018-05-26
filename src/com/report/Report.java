package com.report;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.utility.BrowserHelper;

import java.awt.image.RenderedImage;
import java.io.IOException;

public class Report {
    public static boolean report(String page, String descripion, Status state) throws InterruptedException,
            IOException, GeneralLeanFtException, ReportException {
        Thread.sleep(1000);
        Reporter.setReportTitle("POC");
        RenderedImage image=BrowserHelper.getInstance().getBrowser().getPage().getSnapshot();
        Reporter.reportEvent(page,descripion,state,image);
        return true;
    }
}
