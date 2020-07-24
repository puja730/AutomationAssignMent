package com.test.automation.UiTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {
	
	static WebDriver driver=null;
	static WebDriverWait wait;
	
	public static String RandomeStringGenrateofEightDigit(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	            + "0123456789"
	            + "abcdefghijklmnopqrstuvxyz"; 
		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 

		// generate a random number between 
		// 0 to AlphaNumericString variable length 
		int index 
		= (int)(AlphaNumericString.length() 
		* Math.random()); 

		// add Character one by one in end of sb 
		sb.append(AlphaNumericString 
		  .charAt(index)); 
		} 

		return sb.toString();
		
	}
	
	
	public static WebElement Wait(WebElement element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public static void Click(WebElement element) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void SendKeys(WebElement element,String value) {		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(value);
	}
	
	public static void SelectDdpByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}
	
	public static void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
	}
	
	public static void ScrollUp(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-100)");
	}
	
	static By Account=By.xpath("//span[contains(text(),'Account') and @class='label']");
	static By registration=By.xpath("//a[@title='Register']");
	static By Firstname=By.id("firstname");
	static By Lastname=By.id("lastname");
	static By Email=By.id("email_address");
	static By Passwd=By.id("password");
	static By ConfirmPass=By.id("confirmation");
	static By RegisterButton=By.xpath("//button[@title='Register']");	
	static By Logout=By.xpath("//a[@title='Log Out']");	
	static By LogIn=By.xpath("//a[@title='Log In']");
	static By RegisterMsg=By.xpath("//span[text()='Thank you for registering with Main Website Store.']");	
	static By EmailId=By.xpath("//input[@name='login[username]']");
	static By Pwd=By.xpath("//input[@name='login[password]']");
	static By SendButton=By.xpath("//button[@name='send']");	
	static By Mobile=By.xpath("//a[contains(text(),'Mobile')]");
	static By FirstMobile=By.xpath("(//button[@title='Add to Cart'])[1]");	
	static By ProceedToChkout=By.xpath("//ul[@class='checkout-types bottom']//span[contains(text(),'Proceed to Checkout')]");	
	static By ChkoutType=By.id("onepage-guest-register-button");	
	static By BfirstName=By.id("billing:firstname");
	static By blastname=By.id("billing:lastname");
	static By bemail=By.id("billing:email");
	static By badd1=By.id("billing:street1");
	static By bcity=By.id("billing:city");
	static By bRegionid=By.xpath("//li[@id='billing-new-address-form']//select[@id='billing:region_id']");
	static By bzip=By.id("billing:postcode");
	static By btelephone=By.id("billing:telephone");
	static By ShipToAddressContinue=By.xpath("//button[@onclick='billing.save()']");	
	static By ShippingMethod=By.xpath("//button[@onclick='shippingMethod.save()']");
	static By MoneyOrder=By.xpath("//label[contains(text(),'Check / Money order ')]");
	static By PaymentSaveContinue=By.xpath("//button[@onclick='payment.save()']");	
	static By Placeorder=By.xpath("//span[contains(text(),'Place Order')]");	
	static By OrderSuccessMsg=By.xpath("//h1[contains(text(),'Your order has been received.')]");	
	static By OrderNo=By.xpath("//p[contains(text(),'Your order # is: ')]");
	
	
	
   public static void main(String[] args) {		
	try {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();		
		driver.get("http://live.demoguru99.com/index.php/");		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		driver.findElement(Account).click();		
		Click(driver.findElement(registration));		
		SendKeys(driver.findElement(Firstname), "Puja");		
		SendKeys(driver.findElement(Lastname), "Kumari");		
		String EmailExt = RandomeStringGenrateofEightDigit(8)+"@gmail.com";
		SendKeys(driver.findElement(Email), EmailExt);		
		SendKeys(driver.findElement(Passwd), "Test0001");
		SendKeys(driver.findElement(ConfirmPass), "Test0001");		
		Click(driver.findElement(RegisterButton));		
		if(Wait(driver.findElement(RegisterMsg)).isDisplayed()) {			
			System.out.println("Registation has been done");
		}
		
		Click(driver.findElement(Account));
		Click(driver.findElement(Logout));		
		Click(driver.findElement(Account));
		Click(driver.findElement(LogIn));		
		SendKeys(driver.findElement(EmailId), EmailExt);
		SendKeys(driver.findElement(Pwd), "Test0001");
		Click(driver.findElement(Mobile));
		Click(driver.findElement(FirstMobile));
		
		
		Click(driver.findElement(ProceedToChkout));
		ScrollDown();
		Click(driver.findElement(ChkoutType));
		SendKeys(driver.findElement(BfirstName), "Puja");
		SendKeys(driver.findElement(blastname), "kumari");
		SendKeys(driver.findElement(bemail), EmailExt);
		SendKeys(driver.findElement(badd1), "Deen dayal upadhaya marg");
		SendKeys(driver.findElement(bcity), "NewYork");
		SelectDdpByText(driver.findElement(bRegionid), "Alaska");
		SendKeys(driver.findElement(bzip), "110096");
		SendKeys(driver.findElement(btelephone), "8266070523");
		ScrollDown();
		Click(driver.findElement(ShipToAddressContinue));
		ScrollUp(driver);
		Click(driver.findElement(ShippingMethod));
		Click(driver.findElement(MoneyOrder));
		Click(driver.findElement(PaymentSaveContinue));
		Click(driver.findElement(Placeorder));		
		if(Wait(driver.findElement(OrderSuccessMsg)).isDisplayed()) {
			System.out.println("Order has been Placed successfully");
		}
		else {
			System.out.println("order has not been placed");
		}
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}}
