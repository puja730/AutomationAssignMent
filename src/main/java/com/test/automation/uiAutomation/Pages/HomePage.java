package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.test.automation.Generic.Base.TestBase;
/**
 * 
 * @author Puja Kumari
 *
 */
public class HomePage extends TestBase{
	
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	
	
	 WebDriver driver;
	
	@FindBy(xpath = "//span[contains(text(),'Account') and @class='label']")	
	 WebElement AccountButton;
	
	@FindBy(xpath = "//a[@title='Register']")
	WebElement RegisterLink;
	
	@FindBy(xpath = "//a[@title='Log Out']")
	 WebElement LogOut;
	
	@FindBy(xpath = "//a[@title='Log In']")
	 WebElement Login;
	
	public HomePage(WebDriver driver){
		
		this.driver = driver;		
		PageFactory.initElements(driver, this);
	}
	
	
	public RegistrationPage NavigateToRegistrationPage(WebDriver driver) {
		AccountButton.click();
		RegisterLink.click();
		return new RegistrationPage(driver);	
	}
	
	public void Logout() {
		AccountButton.click();
		LogOut.click();
	}
	
	public LoginPage LogInLink() {
		
		AccountButton.click();
		Login.click();
		return new LoginPage(driver);
	}
		
}
