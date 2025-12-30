package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtfirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtlastName;

    @FindBy(xpath =  "//input[@id='input-email']")
    WebElement txtemail;

    @FindBy(xpath =  "//input[@id='input-telephone']"
    )
    WebElement txttelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtpassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtpasswordConfirm;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement checkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement bthcontinue;

    @FindBy(xpath = "//div[@id='content']//h1[text()='Your Account Has Been Created!']")
    WebElement confirmationMessage;

    public void setFirstName(String fname){
        txtfirstName.sendKeys(fname);
    }

    public void setLastName(String lname){
        txtlastName.sendKeys(lname);
    }

    public void setEmail(String email_txt){
        txtemail.sendKeys(email_txt);
    }

    public void setTelephone(String telephone_txt){
        txttelephone.sendKeys(telephone_txt);
    }

    public void setPassword(String pswd){
        txtpassword.sendKeys(pswd);
    }

    public void setPasswordConfirm(String pswdCnf){
        txtpasswordConfirm.sendKeys(pswdCnf);
    }

    public void setCheckPolicy(){
       checkPolicy.click();
    }
    public void clcikContinue(){
       bthcontinue.click();
    }

    public String getConfirmMessage(){
        try {
            return (confirmationMessage.getText());
        }
        catch (Exception e){
            return (e.getMessage());
        }
    }






}
