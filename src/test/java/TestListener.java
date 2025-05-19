import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    private static final Logger logger = (Logger) LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(" Took screen shot for fail: " + result.getName());
        Object testClass = result.getInstance();
        WebDriver driver = ((TestcaseBase) testClass).getDriver();
        ScreenshotUtil.capture(driver, result.getName());
    }
}
