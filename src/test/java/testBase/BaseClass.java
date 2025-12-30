package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger; //Log4j
    public Properties properties;

    @BeforeClass(groups = {"Sanity","Regression","Master"})
    @Parameters({"OS","Browser"})
    public void setUp(String os,String browser) throws Exception {

        //loading config.properties file
        FileReader file=new FileReader("./src//test//resources//config.properties");
        properties=new Properties();
        properties.load(file);
        logger= LogManager.getLogger(this.getClass());

        //For grid setup
        if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();
            capabilities.setPlatform(Platform.fromString(os.toUpperCase()));
            switch (browser.toLowerCase()){
                case "chrome":capabilities.setBrowserName("chrome"); break;
                case "edge":capabilities.setBrowserName("MicrosoftEdge"); break;
                case "firefox":capabilities.setBrowserName("firefox"); break;
                default: System.out.println("No matching browser...");return;
            }

            driver=new RemoteWebDriver(new URL(properties.getProperty("hubURL")),capabilities );
        }

        //For local setup
        else if(properties.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                default:
                    System.out.println("Invalid Browser name ...");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity","Regression","Master"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(8);
    }

    public String randomNumber(){
        return RandomStringUtils.randomNumeric(10);
    }
    public String randomAlphaNumeric(){
        String alpha=RandomStringUtils.randomAlphabetic(3);
        String num=RandomStringUtils.randomNumeric(3);
        return alpha+"_"+num;
    }
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }


}
