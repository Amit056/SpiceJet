package spicejetLogin;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginToSpiceJet {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeMethod
	public void login() throws Throwable
	{
		String name="amit";
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.spicejet.com/");
		driver.findElement(By.xpath("//div[text()='one way']")).click();

		driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']/descendant::*/input[@autocapitalize]")).sendKeys("delhi");
		driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']/descendant::*/input[@autocapitalize]")).clear();
		driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']/descendant::*/input[@autocapitalize]")).sendKeys(Keys.CLEAR+"kolkata");
		driver.findElement(By.xpath("//div[@data-testid='undefined-month-April-2023']/descendant::div[18]/descendant::div/div/div/div[text()='8']")).click();
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-18u37iz r-19h5ruw r-184en5c']/div[@class='css-1dbjc4n']")).click();
		for (int i = 1; i <=1; i++) {
			driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();
		}
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n']/div[.='Students']")).click();
		wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']"))));
		driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Terms & Conditions']")));
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1tf5bf9 r-1777fci r-1ww30s9']/div/div/*[name()='svg']")).click();
		WebElement element = driver.findElement(By.xpath("//div[text()='Continue']"));
		Actions a=new Actions(driver);
		a.moveToElement(element).click().perform();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@data-testid='continue-search-page-cta']")).click();
		driver.findElement(By.xpath("//input[@data-testid='first-inputbox-contact-details']")).sendKeys("amit");
		driver.findElement(By.xpath("//input[@data-testid='last-inputbox-contact-details']")).sendKeys("ray");
		driver.findElement(By.xpath("//input[@data-testid='contact-number-input-box']")).sendKeys("6260330596s");
		driver.findElement(By.xpath("//input[@data-testid=\"emailAddress-inputbox-contact-details\"]")).sendKeys("akray0413@gmail.com");
		driver.findElement(By.xpath("//input[@data-testid='city-inputbox-contact-details']")).sendKeys("Deoghar");

	}
	@Test(dataProvider = "passengerDetails")
	public void passengerDetails(String fName,String lastName,String phNum,String id )
	{

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");
		int i=0;
			try {
			driver.findElement(By.xpath("//input[@data-testid='traveller-"+i+"-first-traveller-info-input-box']")).sendKeys(fName);
			driver.findElement(By.xpath("//input[@data-testid='traveller-"+i+"-last-traveller-info-input-box']")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@data-testid='sc-member-mobile-number-input-box']")).sendKeys(phNum);
			driver.findElement(By.xpath("//div[text()='Student ID Card*']/../../descendant::*/input[@class]")).sendKeys(id);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[text()='Next']"))));
			driver.findElement(By.xpath("//div[text()='Next']")).click();
			i++;
			}
			catch(Exception e)
			{
				driver.findElement(By.xpath("//input[@data-testid='traveller-"+i+"-first-traveller-info-input-box']")).sendKeys(fName);
				driver.findElement(By.xpath("//input[@data-testid='traveller-"+i+"-last-traveller-info-input-box']")).sendKeys(lastName);
				driver.findElement(By.xpath("//input[@data-testid='sc-member-mobile-number-input-box']")).sendKeys(phNum);
				driver.findElement(By.xpath("//div[text()='Student ID Card*']/../../descendant::*/input[@class]")).sendKeys(id);
				
			}
		}
	

	@DataProvider
	public Object[][] passengerDetails()
	{

		Object[][] m=new Object[2][4];
		m[0][0]="amit";
		m[0][1]="ray";
		m[0][2]="6260330596";
		m[0][3]="ID123";

		m[1][0]="ray";
		m[1][1]="amit";
		m[1][2]="698756889";
		m[1][3]="ID12345";
		return m;

	}
	@AfterMethod
	public void close()
	{
		//driver.close();
	}
}
