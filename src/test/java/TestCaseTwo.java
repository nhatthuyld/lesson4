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


    @Test
    public void checkRemoveButtonNotBack() {

        LogInObject loginpage = new LogInObject(driver);
        logger.info("Login to Problem user");
        loginpage.fillUsername("problem_user");
        loginpage.fillPassword("secret_sauce");
        loginpage.clickLogin();

        //click add to cart
        logger.info("Add to cart the first product");
        InventoryObject inventory = new InventoryObject(driver);
        inventory.addRemovetocard();
        //click remove button
        logger.info("Click remove product");
        inventory.addRemovetocard();
        // Verify that the Remove button does not change back to the Add to Cart button
        String removeText = inventory.getTextAddRemoveButton();
        SoftAssert softAssert = new SoftAssert();
        logger.info("Verify that the Remove button does not change back to the Add to Cart button");
        SoftAssertFunction.checkEquals(driver,softAssert,removeText,"Remove","Verify that the Remove button does not change back to the Add to Cart button");
    }
}