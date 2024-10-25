package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow testCase01 testCase02... format or what is provided in instructions
     */
    @Test
    public void testCase01() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

       
        WebElement nameInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='whsOnd zHQkBf'])[1]")));//explicit wait
        System.out.println("wait 1");
        Wrappers.enterText(nameInputBox, "Crio Learner");

        WebElement practicingAutomationTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='KHxj8b tL9Q4c' and @aria-label='Your answer']")));
        String practicingAutomationText = "I want to be the best QA Engineer!";
        String epochTimeString = Wrappers.getEpochTimeAsString();
        Wrappers.enterText(practicingAutomationTextArea, practicingAutomationText + " " + epochTimeString);
        System.out.println("wait 2");
        // implicit wait

        Wrappers.radioButton(driver, "6 - 10");
        System.out.println("wait 3");
        // implicit wait

        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");

        WebElement dropDoWebElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'DEh1R')]")));
        Wrappers.clickOnElement(driver, dropDoWebElement);// implicit wait
        System.out.println("wait 5");

        WebElement address = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='vRMGwf oJeWuf'])[10]")));// implicit wait
        System.out.println("wait 5");
        // implicit wait
        address.click();
        System.out.println("wait 5");
        // implicit wait

        WebElement dateInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='date']")));
        // implicit wait
        System.out.println("wait 5");
        // implicit wait
        String sevenDaysAgoDate = Wrappers.getdateSevenDaysAgo();
        Wrappers.enterText(dateInputBox, sevenDaysAgoDate);
        System.out.println("wait 6");

        WebElement hourElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Hour']")));// implicit wait
        System.out.println("wait 5");
        WebElement miElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Submit')]")));//explicit wait
        
        Wrappers.enterText(hourElement, "07");
        Wrappers.enterText(miElement, "30");
        Wrappers.clickOnElement(driver, submitBtn);
        System.out.println("wait 7");
WebElement successMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='vHW8K']")));//explicit wait
        String expectedSuccessMsg = "Thanks for your response, Automation Wizard!";
        Assert.assertEquals(successMsgElement.getText().trim(), expectedSuccessMsg);
    }

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();
    }
}
