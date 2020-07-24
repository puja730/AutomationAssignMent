package com.test.automation.uiAutomation.Pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.test.automation.Generic.CommonMethods.Constants;
import com.test.automation.Generic.CommonMethods.WebdriverFunctions;



public class SignUpPageSAP extends WebdriverFunctions{

public static final String Registration_Header="Registration";	
public static final Logger log = Logger.getLogger(HomePageSAP.class.getName());	
WebDriver driver;

@FindBy(xpath = "//h1[contains(text(),'Registration')]")
private WebElement RegistrationHeader_Text;

@FindBy(xpath = "//div[@id='ids_container']//iframe")
private WebElement Iframe_Signup;

@FindBy(id="firstName")
private WebElement FirstName_txt;

@FindBy(id="lastName")
private WebElement LastName_txt;

@FindBy(id="mail")
private WebElement Email_txt;

@FindBy(id="newPasswordInput")
private WebElement Password_txt;

@FindBy(id="retypeNewPasswordInput")
private WebElement ReTypePassword_txt;

@FindBy(id="pdAccept")
private WebElement PrivacyStatement_Chkbox;

@FindBy(id="touAccept")
private WebElement SAPConversational_Chkbox;

@FindBy(xpath = "//div[@class='btn--content' and text()='OK']")
private WebElement OK_btn;

@FindBy(id="sapStoreRegisterFormSubmit")
private WebElement Register_btn;

@FindBy(xpath = "//h1[contains(text(),'Thank you for registering')]")
private WebElement Regsitration_SuccessMsg;

// constructor
public SignUpPageSAP(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);

}

public void FillRegistrationForm(WebDriver driver,String FirstName,String LastName,String Email,String Password,String ConfirmPassword) {
	
	try {
				log.info("============= Started SignUp ===========");
		
		VerifyRegistrationPage(driver);
			
		SendKeys(driver,FirstName_txt, FirstName);
		
		SendKeys(driver,LastName_txt, LastName);
		
		SendKeys(driver,Email_txt, Email);
		
		SendKeys(driver,Password_txt, Password);
		
		SendKeys(driver,ReTypePassword_txt, ConfirmPassword);
							
		log.info("============= Registration Form Filled  ===========");
		
		//getScreenShot("Registration_Form");
		driver.switchTo().defaultContent();
		
		JavascrptClick(driver,OK_btn);
		
		log.info("============= clicked on OK button  ===========");
		
		SwitchToFrame_SignupPage();
		
		ScrollDown(driver);
		
		SelectCheckBox(driver, PrivacyStatement_Chkbox);
		
		SelectCheckBox(driver, SAPConversational_Chkbox);
		
		JavascrptClick(driver,Register_btn);
		
        log.info("============= clicked on Register button of register button  ===========");
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log.info("**********Signup Function aborted due to exception*******");
	}
	
}


public void VerifyRegistrationPage(WebDriver driver) {
	
	SwitchToFrame_SignupPage();	
	System.out.println("Text of Registration page "+RegistrationHeader_Text.getAttribute("innerText"));	
	Assert.assertTrue(IsDisplayed(driver,RegistrationHeader_Text),Registration_Header+ Constants.Assert_Msg);
	log.info("=============Hard Assertion ===========");
	//getScreenShot("verifyRegistration");

}



public void VerifyRegistrationCompletionMessage() {
	
	SwitchToFrame_SignupPage();
	Assert.assertTrue(IsDisplayed(driver,Regsitration_SuccessMsg),Constants.Assert_Msg);
	log.info("=============Hard Assertion ===========");
	//getScreenShot("verifyRegistration_CompletionMessage");

}

public void SwitchToFrame_SignupPage() {
	try {
		driver.switchTo().defaultContent();
		waitForElement(driver, 50, Iframe_Signup);
		List<WebElement> frames = driver.findElements(By.xpath("//div[@id='ids_container']//iframe"));
		driver.switchTo().frame(frames.get(0));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}
