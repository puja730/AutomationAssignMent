package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;

public class LoginPage extends TestBase {
	
WebDriver driver;
	
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	
	@FindBy(xpath = "//input[@name='login[username]']")
	private WebElement LoginEmailAdd;
	
	@FindBy(xpath = "//input[@name='login[password]']")
	private WebElement LoginPassword;
	
	@FindBy(xpath = "//button[@name='send']")
	private WebElement SendButton;
	
	public LoginPage(WebDriver driver) {
			    super();
				this.driver = driver;
				
				PageFactory.initElements(driver, this);
		 }
	
	public MyAccountPage Login(String Email,String passw) {
		
		LoginEmailAdd.sendKeys(Email);
		LoginPassword.sendKeys(passw);
		
		return new MyAccountPage(driver);
		
	}
	

}
