package com.tool;

import com.hp.lft.sdk.Description;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.*;
import com.hp.lft.sdk.web.Frame;
import com.utility.BrowserHelper;

import java.awt.*;
import java.io.IOException;

public class FrameUI {

    private Frame frame;

    public FrameUI(String name) throws IOException, GeneralLeanFtException {
        frame=BrowserHelper.getInstance().getBrowser().describe(Frame.class,new FrameDescription.Builder()
                .name(name).build());
    }

    public WebElement frameElement(String xpath) throws GeneralLeanFtException {
        return frame.describe(WebElement.class,new WebElementDescription.Builder().xpath(xpath).build());
    }

    public Table pageTable(int n) throws GeneralLeanFtException {
        return frame.describe(Table.class,new TableDescription.Builder().index(n).build());
    }

    public RadioGroup radioGroup(String xpath) throws GeneralLeanFtException {
        return frame.describe(RadioGroup.class,new RadioGroupDescription.Builder().xpath(xpath).build());
    }

    public CheckBox chkBox(String xpath) throws GeneralLeanFtException {
        return frame.describe(CheckBox.class,new CheckBoxDescription.Builder().xpath(xpath).build());
    }

    public WebElement[] tableArray(String tagName) throws GeneralLeanFtException, CloneNotSupportedException {
        return frame.findChildren(WebElement.class,new WebElementDescription.Builder().tagName(tagName).build());
    }

    public WebElement[] elementArray(String xpath) throws GeneralLeanFtException, CloneNotSupportedException {
        return frame.findChildren(WebElement.class,new WebElementDescription.Builder().xpath(xpath).build());
    }

    public Table[] tArray(String tagName) throws GeneralLeanFtException, CloneNotSupportedException {
        return frame.findChildren(Table.class,new TableDescription.Builder().tagName(tagName).build());
    }

    public String runJavaScript(String htmlpath) throws GeneralLeanFtException {
        return frame.runJavaScript(htmlpath);
    }

    public <T> T runJavaScript(String s, Class<T> type) throws GeneralLeanFtException {
        return frame.runJavaScript(s,type);
    }
}
