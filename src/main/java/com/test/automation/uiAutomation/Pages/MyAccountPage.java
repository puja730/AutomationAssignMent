package com.test.automation.uiAutomation.Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.automation.Generic.Base.TestBase;

public class MyAccountPage extends TestBase{
	WebDriver driver;
	
	public static final Logger log = Logger.getLogger(MyAccountPage.class.getName());
	
	@FindBy(xpath = "//span[text()='Thank you for registering with Main Website Store.']")
	private WebElement RegistrationMessage;
	
	@FindBy(xpath = "//a[contains(text(),'Mobile')]")
	private WebElement MobileTab;
	
 public MyAccountPage(WebDriver driver) {
	    super();
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
 }
 
 public void VerifyMyAccountPage() {
		
	 waitForElement(driver, 20, RegistrationMessage);
	 	
	 	Assert.assertTrue(RegistrationMessage.isDisplayed(), "Suucessful Registration message should be displayed");
	 }
 
 public Mobilepage ClickOnMobile() {
	 
	 MobileTab.click();
	 
	 return new Mobilepage(driver);
 }
 
}