package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;

public class RegistrationPage extends TestBase{
	
	WebDriver driver;

public static final Logger log = Logger.getLogger(RegistrationPage.class.getName());


@FindBy(id="firstname")
private WebElement FirstName_textBox;

@FindBy(id = "middlename")
private WebElement Middlename_txtbox;
	
@FindBy(id = "lastname")
private WebElement lastname_txtbox;

@FindBy(id="email_address")
private WebElement email_address_Txt;

@FindBy(id = "password")
private WebElement password_txtbox;

@FindBy(id = "confirmation")
private WebElement confirmpasswd_txtbox;

@FindBy(xpath="//button[@title='Register']")
private WebElement RegisterButton;

	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
	  //testBase = new TestBase();
		PageFactory.initElements(driver, this);
	}
	
	
public MyAccountPage EnterRegistrationDetails(WebDriver driver,String Firstname,String MiddleName,String lastname,String email,String passwd,String cpasswd) {
   
	waitForElement(driver, FirstName_textBox, 10);
    FirstName_textBox.sendKeys(Firstname);
    
    waitForElement(driver, Middlename_txtbox, 10);
    Middlename_txtbox.sendKeys(MiddleName);
    
    waitForElement(driver, lastname_txtbox, 10);
    lastname_txtbox.sendKeys(lastname);
    
    waitForElement(driver, email_address_Txt, 10);
    email_address_Txt.sendKeys(email);
    
    waitForElement(driver, password_txtbox, 10);
    password_txtbox.sendKeys(passwd);
    
    waitForElement(driver, confirmpasswd_txtbox, 10);
    confirmpasswd_txtbox.sendKeys(cpasswd);
    
    waitForElement(driver, RegisterButton, 10);
    RegisterButton.click();
	
    return new MyAccountPage(driver);
}


	

	
}
