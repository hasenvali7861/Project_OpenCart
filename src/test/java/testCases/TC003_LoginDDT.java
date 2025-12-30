package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyhAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
Data is valid -  Login is successful - Testcase passes - Logout
Data is valid -  Login is Unsuccessful - Testcase failed

Data is Invalid -  Login is successful - Testcase Failed - Logout
Data is Invalid -  Login is Unsuccessful - Testcase passes
 */
public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData" , dataProviderClass = DataProviders.class,groups = "Datadriven")
    //Need to add (dataProviderClass = DataProviders.class) attribute in the above line as the data provider method is from DataProviders class which is in another package
    public void verify_login(String email,String pwd,String result) throws Exception {

        logger.info("*** Starting TC002_LoginTest ***");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.enterEmail(email);
            lp.enterPassword(pwd);
            lp.clickLogin();

            MyhAccountPage ap = new MyhAccountPage(driver);
            boolean targetpage = ap.isMyAccountExists();
        /*
            Data is valid -  Login is successful - Testcase passes - Logout
                             Login is Unsuccessful - Testcase failed

            Data is Invalid -  Login is successful - Testcase Failed - Logout
                               Login is Unsuccessful - Testcase passes
        */
            if (result.equalsIgnoreCase("Valid")) {
                if (targetpage == true) {
                    ap.clickLogout();
                    Assert.assertTrue(true);

                } else {
                    Assert.assertTrue(false);
                }
            } else if (result.equalsIgnoreCase("Invalid")) {
                if (targetpage == true) {
                    ap.clickLogout();
                    Assert.assertTrue(false);

                } else {
                    Assert.assertTrue(true);
                }
            }

        }
        catch (Exception e){
            Assert.fail();
        }

        logger.info("*** Finished TC003_LoginDDT ***");


    }




}
