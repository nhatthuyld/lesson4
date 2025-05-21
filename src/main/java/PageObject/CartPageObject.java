package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPageObject extends BasePage {
    String checkOutBtnCss = "checkout";
    String listNameCss=".inventory_item_name" ;
    String listPriceCss=".inventory_item_price";

    public CartPageObject(WebDriver driver) {
        super(driver);

    }

    public  String getNameOfProduct(String nthProductCssSlt){
       return(findCssSelector(nthProductCssSlt).getText());
    }

    public void clickCheckOutButton(){
        findByID(checkOutBtnCss).click();
    }

    public  List<ProductObject> getProductListInCart(){

        //get list product in cart
        List<WebElement> productNames = driver.findElements(By.cssSelector(listNameCss));
        List<WebElement> productPrices = driver.findElements(By.cssSelector(listPriceCss));

        List<ProductObject> productListInCart = new ArrayList<>();
        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText();
            double price = Double.parseDouble(productPrices.get(i).getText().replace("$", ""));
            productListInCart.add(new ProductObject(name, price));
        }
        return productListInCart;
    }
}