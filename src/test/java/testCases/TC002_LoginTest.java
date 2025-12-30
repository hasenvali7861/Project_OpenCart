package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyhAccountPage;
import testBase.BaseClass;


public class TC002_LoginTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_login() throws Exception {
        logger.info("*** Starting TC002_LoginTest ***");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            logger.info("Entering email ans password");
            LoginPage lp = new LoginPage(driver);
            lp.enterEmail(properties.getProperty("email"));
            lp.enterPassword(properties.getProperty("password"));
            lp.clickLogin();


            logger.info("Validating My Account");
            MyhAccountPage ap = new MyhAccountPage(driver);
            Assert.assertTrue(ap.isMyAccountExists());
        }
        catch (Exception e){
            Assert.fail();
        }
        logger.info("*** Finished TC002_LoginTest ***");


    }


}
