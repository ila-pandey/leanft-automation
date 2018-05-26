package com.testsuites;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.stdwin.ScrollBar;
import com.hp.lft.sdk.stdwin.ScrollBarDescription;
import com.hp.lft.sdk.web.*;
import com.report.Report;
import com.tool.Element;
import com.tool.FrameUI;
import com.utility.BrowserHelper;
import com.utility.PROPERTYFILE;
import com.utility.PropertyFileLoader;
import org.junit.*;
import com.hp.lft.sdk.*;

import unittesting.*;

import javax.print.DocFlavor;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PO_Tests extends UnitTestClassBase {

    BrowserHelper browser;
    Browser b;

    private static final String PCOS_SHIP_TO_CODES="pcosShipToCodes";
    private static final String Left_FRAME="leftFrame";
    private static final String RIGHT_FRAME="rightFrame";
    private static final String PCOS_SUBMIT ="pcosSubmit";
    private static final String TAG_NAME="tagName";
    private static final String RADIO_XPATH="radioSortBy";
    private static final String INCLUDED_DELETED_CHECKBOX="includeDeletedCheckbox";
    private static final String DELETED_CODES="deletedCodes";
    private static final String EXPECTED_TEXT_COLOR="expectedTextColor";
    private static final String EXPECTED_TEXT_FORMAT="expectedTextFormat";
    private static final String CODE_ZA="codeZA";
    private static final String ALL_CODE="allCode";
    private static final String TOP_LINK="topLink";
    private static final String EXPECTED_HEADER="expectedHeader";
    private static final String CODE_LINKS="codeLinks";
    private static final String EXPECTED_HEADER_POINT="expectedHeaderPoint";


    public PO_Tests() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new PO_Tests();
        globalSetup(PO_Tests.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
        browser=BrowserHelper.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        browser.closeAllTabs();
    }

    public void poNotesLogin() throws GeneralLeanFtException, InterruptedException, IOException, ReportException {
        browser.navigate(PropertyFileLoader.getProperty(PROPERTYFILE.APPLICATION,"PO_Notes_URL"));
        browser.sync();
        Thread.sleep(2000);
        try{
            String expected_title=PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,"po_notes_home_title");
            String actual_title=browser.getBrowser().getTitle();
            if(!expected_title.contains(actual_title)){
                new AssertionError();
            }
            Report.report("PO Home Page","Title",Status.Passed);
        } catch (ReportException e) {
            Report.report("PO Home Page","Title",Status.Failed);
        }
    }

    @Test
    public void test() throws GeneralLeanFtException, InterruptedException, ReportException, IOException, CloneNotSupportedException {

        poNotesLogin();
        browser.sync();

        FrameUI leftFrame = new FrameUI(PropertyFileLoader.getProperty(PROPERTYFILE.DATA, Left_FRAME));
        WebElement pcosShipToCodes = leftFrame.frameElement(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,
                PCOS_SHIP_TO_CODES));
        pcosShipToCodes.highlight();
        pcosShipToCodes.click();
        browser.sync();
        Thread.sleep(2000);

        FrameUI rightFrame = new FrameUI(PropertyFileLoader.getProperty(PROPERTYFILE.DATA, RIGHT_FRAME));
        RadioGroup radioSortBy = rightFrame.radioGroup(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,
                RADIO_XPATH));
        radioSortBy.select(1);
        WebElement pcosSubmit = rightFrame.frameElement(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,
                PCOS_SUBMIT));
        pcosSubmit.click();
        TimeUnit.SECONDS.sleep(1);

        WebElement[] codeLinks=rightFrame.elementArray(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,CODE_LINKS));
        ArrayList<String> codesArray=new ArrayList<String>();
        for (int i=0;i<codeLinks.length;i++){
            codesArray.add(codeLinks[i].getInnerText());
           // System.out.println(codeLinks[i].getInnerText());
        }

        WebElement[] tableArray = rightFrame.tableArray(PropertyFileLoader.getProperty(PROPERTYFILE.DATA, TAG_NAME));

        ArrayList<String> arrayList=new ArrayList<String>();

       /* for(int i=1;i<tableArray.length-1;i++){
            SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
            try{
                arrayList.add(tableArray[i-1].getInnerText().substring(0,2));
                if(i==tableArray.length-2){
                    arrayList.add(tableArray[i+1].getInnerText().substring(0,2));
                }
               // System.out.println(tableArray[i].getInnerText().substring(0,2));
                Date date1=formatter.parse(tableArray[i].getInnerText().substring(9,19));
                Date date2=formatter.parse(tableArray[i+1].getInnerText().substring(9,19));
                if(date1.compareTo(date2)<0){               //descending order
                    System.out.println(i);
                    throw new Exception("Date 1 is before Date2");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

       /* for(int c=0;c<codesArray.size();c++){
            if(codesArray.get(c).equals(arrayList.get(c))){
                System.out.println("Pass");
            }else {
                System.out.println("fail");
                break;
            }
        }*/

        Thread.sleep(1000);
        rightFrame.chkBox(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH, INCLUDED_DELETED_CHECKBOX)).click();
        pcosSubmit.click();

        Thread.sleep(2000);
        WebElement[] deletedCodes = rightFrame.elementArray(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,
                DELETED_CODES));

       /* for (WebElement dCodes : deletedCodes) {

            String actualTextColor = dCodes.getComputedStyle("color");
            Assert.assertEquals(PropertyFileLoader.getProperty(PROPERTYFILE.DATA, EXPECTED_TEXT_COLOR),
                    actualTextColor);
            String actualTextFormat = dCodes.getComputedStyle("text-decoration");
            Assert.assertEquals(PropertyFileLoader.getProperty(PROPERTYFILE.DATA, EXPECTED_TEXT_FORMAT),
                    actualTextFormat);
        }*/

        rightFrame.frameElement(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH, CODE_ZA)).click();
        System.out.println(rightFrame.frameElement(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH,CODE_ZA)).getLocation());
        Thread.sleep(2000);
       /* Scrollbar scrollbar=new Scrollbar();
        int orientation=scrollbar.getOrientation();
        int value=scrollbar.getValue();
        System.out.println(orientation+"  "+value);*/

      //  String html=browser.getBrowser().getPage().runJavaScript("document.body.innerHTML;");

        /*String value = rightFrame.runJavaScript("window.pageYOffset;");
        System.out.println(value);*/
      //  Long value= Long.valueOf(rightFrame.runJavaScript("return window.pageYOffset;"));

        String htmlpath="window.pageYOffset;";

        String a= String.valueOf(rightFrame.runJavaScript("window.pageYOffset;",Integer.class));

        System.out.println(a);

        WebElement[] allCode = rightFrame.elementArray(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH, ALL_CODE));
        WebElement[] topLink = rightFrame.elementArray(PropertyFileLoader.getProperty(PROPERTYFILE.XPATH, TOP_LINK));

        for (int j=1;j<topLink.length;j++) {
            if (allCode[j].getInnerText().equals("ZA")) {
                    topLink[j].click();
                    Point actualPosition=allCode[0].getLocation();
                    System.out.println(actualPosition);
                    
                    break;
            }
        }
        String a1= String.valueOf(rightFrame.runJavaScript("window.pageYOffset;",Integer.class));

        System.out.println(a1);


    }
}