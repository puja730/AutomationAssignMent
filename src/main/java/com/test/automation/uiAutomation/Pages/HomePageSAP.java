package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;
import com.test.automation.Generic.CommonMethods.WebdriverFunctions;

public class HomePageSAP extends WebdriverFunctions{
	
public static final Logger log = Logger.getLogger(HomePageSAP.class.getName());	
WebDriver driver;
	
@FindBy(xpath = "//div[contains(text(),'Sign up')]")
private WebElement SignUp_Btn;
	
		
// Constructor
public HomePageSAP(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);

}

public SignUpPageSAP GetSignupPage(WebDriver driver) {

log.info("=======Verify Home Page of SAP app===========");

	
Click(driver,SignUp_Btn);
	
return new SignUpPageSAP(driver);
}

	
	

}
