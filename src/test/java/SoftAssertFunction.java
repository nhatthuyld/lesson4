import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.asserts.SoftAssert;

public class SoftAssertFunction implements ITestListener {
    private static final Logger log = LogManager.getLogger(SoftAssertFunction.class);

    public static void checkEquals(WebDriver driver, SoftAssert softAssert, String actual, String expected, String stepDescription) {
        if (!actual.equals(expected)) {
            log.error("❌ Step [{}] failed: expected [{}], but got [{}]", stepDescription, expected, actual);
            ScreenshotUtil.capture(driver, stepDescription);
        }
        softAssert.assertEquals(actual, expected, stepDescription);
    }

    public static void checkEqualsInt(WebDriver driver, SoftAssert softAssert, int actual, int expected, String stepDescription) {
        try {
            softAssert.assertEquals(actual, expected, stepDescription);
        } catch (AssertionError e) {
            System.out.println("❌ " + stepDescription);
            System.out.println("Expected: " + expected + ", but got: " + actual);
        }

    }
}


