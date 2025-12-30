package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void account_registration(){

        logger.info("**** Starting TC001_AccountRegistrationTest TestCase ****");
        try{
        HomePage homePage=new HomePage(driver);
        homePage.clickMyAccount();
        logger.info("Clicked on my Account link...");
        homePage.clickRegister();
        logger.info("Clicked on my Register link...");
        logger.info("Entering customer details...");

            RegistrationPage rp = new RegistrationPage(driver);
            rp.setFirstName("Hasen");
            rp.setLastName("Test");
            //rp.setEmail("testhasen@opencart.com");
            //If we pass same email every time the application does not accept so we created static test case to generate random string
            rp.setEmail(randomString() + "@openHcart.com");
            //This will be used to create random email
            rp.setTelephone(randomNumber());        //This will be used to create random telephone num
            String randomPassword = randomAlphaNumeric(); //This will be used to create random password
            rp.setPassword(randomPassword);
            rp.setPasswordConfirm(randomPassword);
            rp.setCheckPolicy();
            rp.clcikContinue();
            logger.info("Validating account creation...");
            if(rp.getConfirmMessage().equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }
            else{
                logger.error("TestCase Failed...");
                logger.debug("Debug Logs...");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e){

            Assert.fail();
        }
        logger.info("**** Finished TC001_AccountRegistrationTest TestCase ****");

    }

}
