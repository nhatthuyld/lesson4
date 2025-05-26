package test;

import Base.TestcaseBase;
import Common.SoftAssertFunction;
import Listener.TestListener;
import PageObject.LogInObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListener.class)
public class TestCaseOne extends TestcaseBase {

    private static final Logger logger = LogManager.getLogger(TestCaseOne.class);


    @Test
    public void checkErrMessageForLockedAccount() {

        LogInObject loginpage = new LogInObject(driver);
        loginpage.fillUsername("locked_out_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();
        logger.info("Verify Error Message For Locked Account");
        SoftAssertFunction.checkEquals(driver, softAssert, loginpage.getMessageError(), "Epic sadface: Sorry, this user has been locked out.", "Verify warning message displays as \"Epic sadface: Sorry, this user has been locked out.\"");

    }
}