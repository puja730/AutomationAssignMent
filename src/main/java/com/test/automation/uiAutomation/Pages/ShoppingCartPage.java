package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;
import com.test.automation.uiAutomation.UiCommonMethods.SelectDDP;

public class ShoppingCartPage extends TestBase {
	WebDriver driver;
	public static final Logger log = Logger.getLogger(ShoppingCartPage.class.getName());
	
	public ShoppingCartPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	
	@FindBy(id = "region_id")
	private WebElement State;
	
	@FindBy(id="postcode")
	private WebElement PostCode;
	
	@FindBy(xpath = "//ul[@class='checkout-types bottom']//span[contains(text(),'Proceed to Checkout')]")
	private WebElement ProceedTocheckout;
	
	
	public void EnterAddressDetails(String DDPvalue,String Zip) {
		SelectDDP.selectByVisibleText(State, DDPvalue);
		PostCode.sendKeys(Zip);
	}
	
	public CheckoutPage ProceedToCheckout() {
		
		ProceedTocheckout.click();
		
		return new CheckoutPage(driver);
	}

}
