package PageObject;

import org.openqa.selenium.WebDriver;

public class InventoryObject extends BasePage {
    public InventoryObject(WebDriver driver) {

        super(driver);

    }

    public void addRemovetocart(String nthproduct) {
        findCssSelector(nthproduct).click();
    }

    public  String getTextAddRemoveButton(String nthproduct){
        return (findCssSelector(nthproduct).getText());

    }
}
