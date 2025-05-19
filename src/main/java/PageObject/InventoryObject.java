package PageObject;

import org.openqa.selenium.WebDriver;

public class InventoryObject extends BasePage {
    public InventoryObject(WebDriver driver) {

        super(driver);

    }

    String addRemoveCssSector =".pricebar button" ;

    public void addRemovetocard() {
        findCssSelector(addRemoveCssSector).click();
    }

    public  String getTextAddRemoveButton(){
        return (findCssSelector(addRemoveCssSector).getText());

    }
}
