package PageObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    private static final Logger log = LogManager.getLogger(BasePage.class);


    public void open_Url(String url_string) {
        driver.get(url_string);
    }

    public WebElement findXpath(String xpath_string) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_string)));
        return element;
    }

    public WebElement findXpathToCLickAble(String xpath_string) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_string)));
        return element;
    }

    public WebElement findCssSelector(String CSS_string) {
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofMillis(10000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CSS_string)));
        return element;    }

    public WebElement findByID(String id_string) {
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofMillis(10000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id_string)));
        return element;
    }

    public void SendString(String xpath_String, String s) {

        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofMillis(10000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath_String)));
        element.sendKeys(s);
    }
    public void scrollToClickElement(By locator){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            // Scroll đến phần tử
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Click phần tử
            element.click();
        } catch (Exception e) {
            System.out.println("❌ Can not scroll to element: " + e.getMessage());
        }
    }

    public void closeAlert(){
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("❌No alert to be displayed " + e.getMessage());
        }
    }

    public static void cleanScreenshotFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            log.info("Folder does not exist or is not a directory: " + folderPath);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        if (files == null || files.length == 0) {
            log.info("No screenshot files to delete in: " + folderPath);
            return;
        }

        for (File file : files) {
            if (file.delete()) {
                log.info("Deleted screenshot: " + file.getName());
            } else {
                log.info("Failed to delete: " + file.getName());
            }
        }
    }




}
