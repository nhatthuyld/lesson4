import PageObject.InventoryObject;
import PageObject.LogInObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Listeners(TestListener.class)
public class TestCaseTwo extends TestcaseBase {

    private static final Logger logger = LogManager.getLogger(TestCaseTwo.class);

    @Test(priority = 1)
    public void checkRemoveButtonNotBack() {
        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Problem user");
        loginpage.fillUsername("problem_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart the first product");
        InventoryObject inventory = new InventoryObject(driver);
        inventory.addRemovetocart(".inventory_list div:nth-of-type(1) button");
        //click remove button
        logger.info("Click remove product");
        inventory.addRemovetocart(".inventory_list div:nth-of-type(1) button");
        // Verify that the Remove button does not change back to the Add to Cart button
        String removeText = inventory.getTextAddRemoveButton(".inventory_list div:nth-of-type(1) button");
        logger.info("Verify that the Remove button does not change back to the Add to Cart button");
        SoftAssertFunction.checkEquals(driver,softAssert,removeText,"Removee","Verify that the Remove button does not change back to the Add to Cart button");
    }
    //testcase3
    @Test(priority = 2)
    public void checkCanNotAddToCart() {

        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Problem user");
        loginpage.fillUsername("problem_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart the third product");
        InventoryObject inventory = new InventoryObject(driver);
        inventory.addRemovetocart(".inventory_list div:nth-of-type(3) button");
        //click remove button
        logger.info("Click remove product");
        inventory.addRemovetocart(".inventory_list div:nth-of-type(3) button");
        // Verify that the Remove button does not change back to the Add to Cart button
        String removeText = inventory.getTextAddRemoveButton(".inventory_list div:nth-of-type(3) button");
        logger.info("Verify Add to Cart button is not changed to Remove button");
        SoftAssertFunction.checkEquals(driver,softAssert,removeText,"Aadd to cart","Verify that the Remove button does not change back to the Add to Cart button");
    }
}