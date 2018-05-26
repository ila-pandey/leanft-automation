package com.tool;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;
import com.utility.BrowserHelper;

import java.io.IOException;

public class Element {
    private WebElement element;
    public Element(String xpath) throws IOException, GeneralLeanFtException {
        element=BrowserHelper.getInstance().getBrowser().describe(WebElement.class,new WebElementDescription.Builder()
                .xpath(xpath).build());
    }

    public boolean click() throws IOException, GeneralLeanFtException {
        BrowserHelper.getInstance().getBrowser().sync();
        element.click();
        return true;
    }

    public String getText() throws IOException, GeneralLeanFtException {
        return element.getInnerHTML();
    }

    public void highlight() throws IOException, GeneralLeanFtException {
        BrowserHelper.getInstance().getBrowser().sync();
        element.highlight();
    }

    public String getTextonly() throws GeneralLeanFtException, IOException {
        BrowserHelper.getInstance().getBrowser().sync();
        return element.getInnerText();
    }

    public String getTextDecoration(String getProperty) throws GeneralLeanFtException {
        return element.getComputedStyle(getProperty);
    }

    public String checkIfLink() throws GeneralLeanFtException {
        return element.getTagName();
    }
}
