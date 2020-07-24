package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.automation.Generic.Base.TestBase;

public class MagentoCommerce extends TestBase{
	
	WebDriver driver;
	public static final Logger log = Logger.getLogger(MagentoCommerce.class.getName());	
	
	@FindBy(xpath = "//h1[contains(text(),'Your order has been received.')]")
	private WebElement OrderSuccessMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Your order # is: ')]")
	private WebElement OrderNo;
	
	
	public MagentoCommerce(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public String GetOrderNo(WebDriver driver) {
		String ordern=null;
	try {
		waitForElement(driver, 50, OrderSuccessMessage);	
		Assert.assertTrue(OrderSuccessMessage.isDisplayed(), "Order message should be displayed successfully");
		ordern= OrderNo.getText();
		System.out.println(ordern);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
    return ordern;	
	}
	
}
