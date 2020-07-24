package com.test.automation.UiTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.APITest.TC001_Task;
import com.test.automation.Generic.Base.TestBase;
import com.test.automation.Generic.CommonMethods.CommonMethods;
import com.test.automation.Generic.CommonMethods.Constants;
import com.test.automation.uiAutomation.Pages.HomePage;
import com.test.automation.uiAutomation.Pages.HomePageSAP;
import com.test.automation.uiAutomation.Pages.SignUpPageSAP;

public class SapRegistrationTest extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_Task.class.getName());
	HomePageSAP homepage;
	SignUpPageSAP signup;	
	
	
	@DataProvider(name="SignUpData_Sap")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "SignUpData_Sap");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws IOException, ParseException {
		init();
		
	}

@Test(dataProvider = "SignUpData_Sap")	
public void SapCustomerRegistration(String FirstName,String LastName,String Password) {
	
	       try {
	    	   System.out.println("Jenkins Url "+System.getProperty("url"));
	    	   
	    	   log.info("=======Url Printed from jenkins ===========");
	    	   System.out.println("Jenkins User name "+System.getProperty("userName"));
			    // Random Email Id genration
				EmailExt = CommonMethods.RandomeStringGenrateofEightDigit(8)+CommonMethods.GetProperties(Constants.MAIL_Ext);
				
				log.info("=======started verifyRegistration Test steps ===========");
				
				homepage=new HomePageSAP(driver);
				
				getScreenShot("Home_Page_verification");
				
				signup=homepage.GetSignupPage(driver);
				
				signup.FillRegistrationForm(driver,FirstName, LastName, EmailExt, Password, Password);
				
				getScreenShot("Registration_Completeion_message");
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=======Ended Registration Test due to exception ===========");
		}
}

@AfterClass
public void TearDown() {
driver.quit();
}
}
