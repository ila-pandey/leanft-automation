package com.utility;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserDescription;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;

import java.io.IOException;

public class BrowserHelper {

    private Browser browser;
    private static BrowserHelper helper;

    private BrowserHelper() throws IOException, GeneralLeanFtException {
        if(browser == null) {
            String choice = PropertyFileLoader.getProperty(PROPERTYFILE.APPLICATION, "browser");
            switch(choice.trim()) {
                case "firefox":
                    browser = BrowserFactory.launch(BrowserType.FIREFOX);
                    break;
                case "ie":
                    browser = BrowserFactory.launch(BrowserType.INTERNET_EXPLORER);
                    break;
            }
        }
    }

    public static BrowserHelper getInstance() throws IOException, GeneralLeanFtException {
        if(helper == null) {
            helper = new BrowserHelper();
        }
        return helper;
    }

    public boolean navigate(String url) throws GeneralLeanFtException, IOException, InterruptedException{
        browser.navigate(url);
        //browser.sync();
        Thread.sleep(1000);
        return true;
    }

    public boolean switchToTabByTitle(String title) throws GeneralLeanFtException, IOException, InterruptedException {
        browser.sync();
        Thread.sleep(1000);
        Browser[] list = BrowserFactory.getAllOpenBrowsers(new BrowserDescription.Builder().build());
        for(Browser b:list) {
            if(b.getTitle().equals(title)) {
                browser = b;
                break;
            }
        }
        return true;
    }

    public boolean switchToTabByURL(String url) throws GeneralLeanFtException, IOException {
        browser = BrowserFactory.attach(new BrowserDescription.Builder().url(url).build());
        return true;
    }
    public void closeAllTabs() throws GeneralLeanFtException {
        browser.closeAllTabs();
        browser = null;
        helper = null;
    }
    public Browser getBrowser() throws IOException, GeneralLeanFtException {
        return browser;
    }
    public void fullScreen() throws GeneralLeanFtException {
        browser.fullScreen();
    }
    public void sync() throws GeneralLeanFtException {
        browser.sync();
    }

}
