package com.tool;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.utility.BrowserHelper;

import java.io.IOException;

public class Editable {

    private EditField element;
    public Editable(String xpath) throws IOException, GeneralLeanFtException {
        element = BrowserHelper.getInstance().getBrowser().describe(EditField.class, new EditFieldDescription.Builder()
                .xpath(xpath).build());
    }

    public boolean setValue(String value) throws GeneralLeanFtException {
        element.setValue(value);
        return true;
    }
}
