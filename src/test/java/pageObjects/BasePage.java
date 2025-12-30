package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    //BasePage class contains only constructor which is used to invoke the Driver

    public BasePage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }
}
