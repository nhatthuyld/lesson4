
import PageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


public class TestcaseBase {
    WebDriver driver;
    private static String url = "https://www.saucedemo.com/";
    SoftAssert softAssert;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setupClass() {
        BasePage.cleanScreenshotFolder("screenshots");
    }

    @BeforeMethod
    public void setup() {

        //clean exist image on screenshots fold before run
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        softAssert = new SoftAssert();
        driver.manage().window().maximize();
        driver.get(url);

    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        try {
            softAssert.assertAll();  // Ném lỗi nếu có
        } catch (AssertionError e) {
            System.out.println("❗SoftAssert failed in: " + result.getMethod().getMethodName());
            // Đính kèm lỗi để TestNG ghi nhận fail, nhưng không dừng test suite
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public void openURL() {
        driver.get(url);
    }


}
