package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryObject extends BasePage {
    public InventoryObject(WebDriver driver) {

        super(driver);

    }

    public void addRemovetocart(String nthproduct) {
        findCssSelector(nthproduct).click();
    }

    public  String getTextAddRemoveButton(String nthproductCss){
        return (findCssSelector(nthproductCss).getText());

    }

    public  void clickCartIcon(){
        findCssSelector(".shopping_cart_link").click();
    }

    public  void scrollToAddRemoveButton(String nthproductCss){
        By AddBtn = By.cssSelector(nthproductCss);
        scrollToClickElement(AddBtn);
    }

}
