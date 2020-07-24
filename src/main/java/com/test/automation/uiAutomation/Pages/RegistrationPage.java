package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;

public class LoginandRegistrationPage extends TestBase{
	
	WebDriver driver;

public static final Logger log = Logger.getLogger(LoginandRegistrationPage.class.getName());


@FindBy(id="email_create")
private WebElement Email_textBox;

@FindBy(css = "i[class*='icon-user left']")
private WebElement CreateAnAccount_Btn;
	
@FindBy(id = "email")
private WebElement Emaild_txt;

@FindBy(id="passwd")
private WebElement Password_Txt;

@FindBy(xpath = "//i[@class='icon-lock left']")
private WebElement SignOn_btn;

	public LoginandRegistrationPage(WebDriver driver) {
		this.driver=driver;
	  //testBase = new TestBase();
		PageFactory.initElements(driver, this);
	}
	
	
public void EnterEmailAddress(String EmailId) {
   waitForElement(driver, Email_textBox, 10);
	Email_textBox.sendKeys(EmailId);
	
}

public CreateAnAccountPage ClickCreateAccount_Btn() {
	
	CreateAnAccount_Btn.click();
	
	return new CreateAnAccountPage(driver);
}
	
public MyAccountPage Login(String Email,String pwd,WebDriver driver) {
	
	waitForElement(driver, 20, Emaild_txt);
	Emaild_txt.sendKeys(Email);
	Password_Txt.sendKeys(pwd);
	SignOn_btn.click();
	
	return new MyAccountPage(driver);
}
	
	
}
