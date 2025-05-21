import PageObject.CartPageObject;
import PageObject.InventoryObject;
import PageObject.LogInObject;
import PageObject.ProductObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Listeners(TestListener.class)
public class TestCaseTwoFive extends TestcaseBase {

    private static final Logger logger = LogManager.getLogger(TestCaseTwoFive.class);

    @Test(priority = 1)
    public void checkRemoveButtonNotBack() {
        SoftAssert softAssert = new SoftAssert();

        logger.info("Testcase 2  ");
        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Problem user");
        loginpage.fillUsername("problem_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart the first product");
        InventoryObject inventory = new InventoryObject(driver);
        inventory.addRemovetocart("div.inventory_item:nth-of-type(1) button");

        //click remove button
        logger.info("Click remove product");
        inventory.addRemovetocart("div.inventory_item:nth-of-type(1) button");

        // Verify that the Remove button does not change back to the Add to Cart button
        String removeText = inventory.getTextAddRemoveButton(".inventory_list div:nth-of-type(1) button");
        logger.info("Verify that the Remove button does not change back to the Add to Cart button");
        SoftAssertFunction.checkEquals(driver, softAssert, removeText, "Remove", "Verify that the Remove button does not change back to the Add to Cart button");
        softAssert.assertAll();
    }

    //testcase3
    @Test(priority = 2)
    public void checkCanNotAddToCart() {
        SoftAssert softAssert = new SoftAssert();

        logger.info("Testcase 3");
        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Problem user");
        loginpage.fillUsername("problem_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart the third product");
        InventoryObject inventory = new InventoryObject(driver);
        inventory.addRemovetocart("div.inventory_item:nth-of-type(3) button");

        //click remove button
        logger.info("Click remove product");
        inventory.addRemovetocart("div.inventory_item:nth-of-type(3) button");

        // Verify that the Remove button does not change back to the Add to Cart button
        String removeText = inventory.getTextAddRemoveButton("div.inventory_item:nth-of-type(1) button");
        logger.info("Verify Add to Cart button is not changed to Remove button");
        SoftAssertFunction.checkEquals(driver, softAssert, removeText, "Add to cart", "Verify that the Remove button does not change back to the Add to Cart button");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void checkAddToCartSuccessfull() {
        String nameOfProduct;
        String nameOfProductInCart;
        SoftAssert softAssert = new SoftAssert();


        logger.info("Testcase 4");
        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Standard user");
        loginpage.fillUsername("standard_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart the first product");
        InventoryObject inventory = new InventoryObject(driver);
        inventory.addRemovetocart("div.inventory_item:nth-of-type(1) button");
        nameOfProduct = inventory.findCssSelector("div.inventory_item:nth-of-type(1) button .inventory_item_name ").getText();

        //click cart icon
        inventory.clickCartIcon();
        //get Name of product in cart page
        CartPageObject cartpage = new CartPageObject(driver);
        nameOfProductInCart = cartpage.getNameOfProduct(".inventory_list div:nth-of-type(1) .inventory_item_name");

        //Check product is added
        logger.info("Verify product is added");
        SoftAssertFunction.checkEquals(driver, softAssert, nameOfProduct, nameOfProductInCart, "Verify that Product is added");
        softAssert.assertAll();


    }

    @Test(priority = 4)
    public void checkCheckOutSuccesful() throws InterruptedException {

        logger.info("Testcase 5");
        List<ProductObject> productlist = new ArrayList<>();
        List<ProductObject> productCartList = new ArrayList<>();
        SoftAssert softAssert = new SoftAssert();

        String name;
        double price;
        double totalprice = 0;

        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Standard user");
        loginpage.fillUsername("standard_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart three products");
        InventoryObject inventory = new InventoryObject(driver);

        //add list product


        //add product 1 to cart
        inventory.addRemovetocart("div.inventory_item:nth-of-type(1) button");
        name = inventory.findCssSelector("div.inventory_item:nth-of-type(1) .inventory_item_name").getText();
        price = Double.parseDouble(inventory.findCssSelector("div.inventory_item:nth-of-type(1) .inventory_item_price ").getText().replace("$", ""));
        productlist.add(new ProductObject(name, price));
        totalprice += price;

        //add product 2 to cart
        inventory.addRemovetocart("div.inventory_item:nth-of-type(2) button");
        name = inventory.findCssSelector("div.inventory_item:nth-of-type(2) .inventory_item_name").getText();
        price = Double.parseDouble(inventory.findCssSelector("div.inventory_item:nth-of-type(2) .inventory_item_price ").getText().replace("$", ""));
        productlist.add(new ProductObject(name, price));
        totalprice += price;

        //add product 3 to cart
        inventory.addRemovetocart("div.inventory_item:nth-of-type(3) button");
        name = inventory.findCssSelector("div.inventory_item:nth-of-type(3) .inventory_item_name").getText();
        price = Double.parseDouble(inventory.findCssSelector("div.inventory_item:nth-of-type(3) .inventory_item_price ").getText().replace("$", ""));
        productlist.add(new ProductObject(name, price));
        totalprice += price;

        //click cart icon
        inventory.clickCartIcon();
        CartPageObject cartPageObject = new CartPageObject(driver);

        //get list product in cart
        productCartList = cartPageObject.getProductListInCart();

        //Equal size of product list and products in cart
        SoftAssertFunction.checkEqualsInt(driver, softAssert, productCartList.size(), productlist.size(), "Verify that the total products in list is equal products in cart page");

        for (int i = 0; i < productlist.size(); i++) {
            softAssert.assertEquals(productlist.get(i).getName(), productCartList.get(i).getName());
            softAssert.assertEquals(productlist.get(i).getPrice(), productCartList.get(i).getPrice(), 0.01);

        }

        //click checkout
        cartPageObject.clickCheckOutButton();

        //fill info
        cartPageObject.findCssSelector("#first-name").sendKeys("thuy");
        cartPageObject.findCssSelector("#last-name").sendKeys("tran");
        cartPageObject.findCssSelector("#postal-code").sendKeys("7000");
        cartPageObject.findCssSelector("#continue").click();

        //equal total price
        double totalpriceCheckOut = Double.parseDouble(cartPageObject.findCssSelector(".summary_total_label").getText().replace("Total: $", ""));
        totalprice = totalprice + totalprice*0.08;
        totalprice = Math.round(totalprice * 100.0) / 100.0;
        softAssert.assertEquals(totalpriceCheckOut,totalprice);

        //click finish
        cartPageObject.findCssSelector("#finish");

        //Verify Checkout Completed page displays without error
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertFalse(currentUrl.contains("/error") || currentUrl.contains("404"), "Trang bị lỗi chuyển hướng");
        softAssert.assertAll();


    }
}