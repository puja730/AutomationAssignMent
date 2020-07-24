package com.test.automation.APITest;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.Generic.Base.TestBase;
import com.test.automation.Generic.CommonMethods.CommonMethods;
import com.test.automation.uiAutomation.Pages.CreateAnAccountPage;
import com.test.automation.uiAutomation.Pages.HomePage;
import com.test.automation.uiAutomation.Pages.LoginandRegistrationPage;
import com.test.automation.uiAutomation.Pages.MyAccountPage;
import com.test.automation.uiAutomation.Pages.MyOrderPage;
import com.test.automation.uiAutomation.Pages.OrderDetailsPage;
import com.test.automation.uiAutomation.Pages.ProductDetailPage;
/**
 * 
 * @author Vivek Kumar Jha
 * 
 */
public class TC001_Task extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_Task.class.getName());
	HomePage homepage;
		
	
	@DataProvider(name="SignUpData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "SignUp");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws IOException, ParseException {
		init();
		
	}
    
	

	
	@Test(dataProvider = "SignUpData", enabled=true)
	public void CustomerRegistration(String Fname,String Lname,String pwd,String day,String month,String year 
			,String Addfname1,String AddLname,String Address,String City,String State,String mobileno,String EmailKey,String Emailid,String zip) {
		
		
		// Random Email Id genration
		String EmailidCust = CommonMethods.RandomeStringGenrateofEightDigit(8)+"@gmail.com";
		
		log.info("=======started verifyRegistration Test===========");
		homepage = new HomePage(driver);
		
		LoginandRegistrationPage loginPage=homepage.NavigateToRegistrationPage();
		log.info("============= Started SignUp ===========");
		getScreenShot("verifyRegistration");
		ScrollDown();
		loginPage.EnterEmailAddress(EmailidCust);
		getScreenShot("EmaildEntered");
		log.info("============= Started filling Su=ignup personal and Address Details ===========");
		CreateAnAccountPage registrationpage =loginPage.ClickCreateAccount_Btn();
		getScreenShot("createAnAccount");
		registrationpage.EnterSignupAllDetails(Fname, Lname, pwd, day, month, year, Addfname1, AddLname, Address, City, State, mobileno, EmailKey, EmailidCust,zip);
		getScreenShot("AllDetailsEnetered");
		MyAccountPage myAccountPage=registrationpage.ClickSignUpButton(driver);
		log.info("============= My Account Home Page ===========");
		getScreenShot("MyAccountHomePage");
		myAccountPage.VerifyMyAccountPage();
		// Save email id in properties file		
		
		System.out.println("Email Key is : "+EmailKey + " and Email Value is : "+EmailidCust);
		CommonMethods.SaveInProperties(EmailKey, EmailidCust);
		
		//driver.quit();
	}
	
	@Test(dependsOnMethods = {"CustomerRegistration"},enabled = true)
   	public void LoginWithNewUserAndAddproductNAndProcdTochkout() throws IOException, ParseException {
		// Launch Browser
		init();
		MyAccountPage myAccountPage=null;
		log.info("=======started verifyRegistration Test===========");
		homepage = new HomePage(driver);
		
		String Email=CommonMethods.GetProperties("Email");
		String Password=CommonMethods.GetProperties("Password");
		String Qty=CommonMethods.GetProperties("Qty");
		
		LoginandRegistrationPage loginPage=homepage.NavigateToRegistrationPage();
		log.info("============= Started SignUp/Login ===========");
		ScrollDown();
		myAccountPage=loginPage.Login(Email, Password,driver);
		myAccountPage.VerifyMyAccountPage();
		log.info("============= My Account Home Page ===========");
		getScreenShot("MyAccountHomePage");
		
		myAccountPage.ClickWomenTab();
		log.info("============= Womens Section ===========");
		getScreenShot("WomenSections");
		
		ProductDetailPage ProductDetailPage = myAccountPage.ClickWomenSectionProduct(driver);
		log.info("============= Womens Section Product Detail===========");
		getScreenShot("WomenSectionProduct");
		ProductDetailPage.AddQnty_Procdtochkout(Qty);
		log.info("============= Womens Section Product Quantity To AddTo cart===========");
		getScreenShot("ProductAddToCart");
		MyOrderPage myOrderPage=ProductDetailPage.ClickOnProceedToChkout(driver);
		log.info("============= My order===========");
		getScreenShot("MyOrder");
		myOrderPage.ProceedToCheckout();
		log.info("============= My order Confirmation===========");
		getScreenShot("MyOrderConfirmation");
		myAccountPage=myOrderPage.ReturnToHome(driver);
		log.info("============= My Account Home Page ===========");
		getScreenShot("MyAccountHomePage");
		myAccountPage.VerifyMyAccountPage();
		OrderDetailsPage orderDetailsPage=myAccountPage.GetOrderHistory(driver);
		log.info("============= My Account Home Page ===========");
		getScreenShot("MyOrderHistoryPage");
		orderDetailsPage.VerifyOrderPriceFromePaymentPage();
		
	}
	
	
	@AfterClass
	public void TearDown() {
	driver.quit();
	}
	
}
