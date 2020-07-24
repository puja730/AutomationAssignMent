package com.test.automation.UiTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.test.automation.Generic.Base.TestBase;
import com.test.automation.Generic.CommonMethods.CommonMethods;
import com.test.automation.Generic.CommonMethods.Constants;
import com.test.automation.uiAutomation.Pages.CheckoutPage;
import com.test.automation.uiAutomation.Pages.HomePage;
import com.test.automation.uiAutomation.Pages.LoginPage;
import com.test.automation.uiAutomation.Pages.MagentoCommerce;
import com.test.automation.uiAutomation.Pages.Mobilepage;
import com.test.automation.uiAutomation.Pages.MyAccountPage;
import com.test.automation.uiAutomation.Pages.RegistrationPage;
import com.test.automation.uiAutomation.Pages.ShoppingCartPage;


public class RegistrationTest extends TestBase{
	
	HomePage homepage;
	RegistrationPage registration;
	MyAccountPage myAccountPage;
	LoginPage loginpage;
	Mobilepage mobilepage;
	ShoppingCartPage shoppingCartPage;
	CheckoutPage checkoutPage;
	MagentoCommerce magentoCommerce;
	
public static final Logger log = Logger.getLogger(RegistrationTest.class.getName());
	
	
	
	@DataProvider(name="Registration")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "Registration");
		return testRecords;
	}
	
	@DataProvider(name="ShippingAddress")
	public String[][] ShippingAddress(){
		String[][] testRecords = getData("TestData.xlsx", "ShippingAddress");
		return testRecords;
	}
    
	@Parameters({"browser"})
	@BeforeClass
	public void setUp(String browser) throws IOException, ParseException {
		System.out.println("Browser name is "+browser);
		init(browser);
		
	}

@Test(dataProvider = "Registration")	
public void CustomerRegistration(String Firstname,String MiddleName,String lastname,String passwd) {
	
	       try {
	    	   System.out.println("Jenkins Url "+System.getProperty("url"));
	    	   
	    	   log.info("=======Url Printed from jenkins ===========");
	    	  // System.out.println("Jenkins User name "+System.getProperty("userName"));
			    // Random Email Id genration
				EmailExt = CommonMethods.RandomeStringGenrateofEightDigit(8)+CommonMethods.GetProperties(Constants.MAIL_Ext);
				
				log.info("=======started verifyRegistration Test steps ===========");
				
				homepage=new HomePage(driver);
				
				getScreenShot("Home_Page_verification");
				
				registration=homepage.NavigateToRegistrationPage(driver);
				
				log.info("=======Reached To Registration Page ===========");
				
				myAccountPage=registration.EnterRegistrationDetails(driver,Firstname, MiddleName, lastname, EmailExt, passwd, passwd);
				
				log.info("=======Entered Registration Details on Registration Page ===========");
				getScreenShot("Registration_Completeion");
				
				myAccountPage.VerifyMyAccountPage();
				
				log.info("=======Verified Succesfull registration ===========");
				getScreenShot("Registration_Completeion_message");
				
				CommonMethods.SaveInProperties("Email", EmailExt);
				log.info("======= Email ID saved to Database ===========");
				
				homepage.Logout();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=======Ended Registration Test due to exception ===========");
		}
}

@Test(dataProvider = "ShippingAddress",dependsOnMethods = {"CustomerRegistration"})
public void Checkout(String FirstName,String LastName,String Address1,String City,String State,String Zipcode,String mobileno) {
	loginpage=homepage.LogInLink();
	log.info("======= Landed to Login Page ===========");
	getScreenShot("Landed to Login Page");
	
	myAccountPage=loginpage.Login(CommonMethods.GetProperties("Email"), CommonMethods.GetProperties("Password"));
	log.info("======= Login with valid Credential ===========");
	getScreenShot("Login with valid Credential ");
	
	mobilepage=myAccountPage.ClickOnMobile();
	log.info("======= Reached to Mobile page ===========");
	getScreenShot("Reached to Mobile page ");
	
	
	shoppingCartPage=mobilepage.AddFirstProduct();
	log.info("======= Added First mobile product in cart ===========");
	getScreenShot("Added First mobile product in cart ");
	
	checkoutPage=shoppingCartPage.ProceedToCheckout();
	log.info("======= Proceeded to Checkout ===========");
	getScreenShot("Proceeded to Checkout ");
	
	checkoutPage.SelectCheckoutType(driver);
	log.info("======= Selected Checkout Type default as a guest ===========");
	getScreenShot("Selected Checkout Type default as a guest");
	
	
	checkoutPage.AddShippingAddress(driver,FirstName,LastName,CommonMethods.GetProperties("Email"),Address1, City, State, Zipcode, mobileno);
	log.info("======= Entered shipping address details===========");
	getScreenShot("Entered shipping address details");
	
	checkoutPage.PaymentInfo(driver);
	log.info("======= Reached To Payment info Page===========");
	getScreenShot("Reached To Payment info Page");
	
	magentoCommerce=checkoutPage.OrderOverView(driver);
	log.info("======= Submitted for order===========");
	getScreenShot("Submitted for order");
	
	magentoCommerce.GetOrderNo(driver);
	log.info("======= Verified order message and Order no===========");
	getScreenShot("Verified order message and Order no");
	
}

@AfterClass
public void TearDown() {
driver.quit();
}
}
