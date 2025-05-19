package PageObject;

import org.openqa.selenium.WebDriver;

public class LogInObject extends BasePage {

    String userNameSelector = "#user-name";
    String passWordSelector = "#password";
    String loginButtonSelector = "#login-button";
    String errMessageLockedUserSelector = "h3[data-test='error']";

    public LogInObject(WebDriver driver) {

        super(driver);

    }
    public void fillUsername(String username) {
        findCssSelector(userNameSelector).sendKeys(username);
    }

    public void fillPassword(String password) {
        findCssSelector(passWordSelector).sendKeys(password);
    }
    public String getMessageError() {

        String errMessage = findCssSelector(errMessageLockedUserSelector).getText();
        return errMessage;
    }

    public void  clickLogin(){
        findCssSelector(loginButtonSelector).click();
    }

}
