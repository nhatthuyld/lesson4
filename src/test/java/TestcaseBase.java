
import PageObject.BasePageOne;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestcaseBase {
    WebDriver driver;
    private static String url = "https://www.saucedemo.com/";

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {

        //clean exist image on screenshots fold before run
        BasePageOne.cleanScreenshotFolder("screenshots");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");// to dataprovider run
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);


    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }

    public void openURL() {
        driver.get(url);
    }


}
