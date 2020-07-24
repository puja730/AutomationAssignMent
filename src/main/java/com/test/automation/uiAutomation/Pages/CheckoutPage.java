package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;

public class CheckoutPage extends TestBase{

	WebDriver driver;
	public static final Logger log = Logger.getLogger(CheckoutPage.class.getName());
	
	@FindBy(id = "onepage-guest-register-button")
	private WebElement CheckoutTypeContinue;
	
	@FindBy(id="billing:firstname")
	private WebElement BillingFirstName;
	
	@FindBy(id="billing:lastname")
	private WebElement BillingLastName;
	
	@FindBy(id="billing:email")
	private WebElement BillingEmail;
	
	@FindBy(id="billing:street1")
	private WebElement BillingAddress;
	
	@FindBy(id="billing:city")
	private WebElement BillingCity;
	
	@FindBy(xpath ="//li[@id='billing-new-address-form']//select[@id='billing:region_id']")
	private WebElement RegionId;
	
	@FindBy(id="billing:postcode")
	private WebElement Zip;
	
	@FindBy(id="billing:telephone")
	private WebElement Telephone;
	
	@FindBy(xpath ="//button[@onclick='billing.save()']")
	private WebElement ShipToAddressContinue;
	
	@FindBy(xpath = "//button[@onclick='shippingMethod.save()']")
	private WebElement ShippingMethod;
	
	@FindBy(xpath = "//label[contains(text(),'Check / Money order ')]")
	private WebElement MoneyOrder;
	
	@FindBy(xpath = "//button[@onclick='payment.save()']")
	private WebElement PaymentSaveContinue;
	
	@FindBy(xpath = "//span[contains(text(),'Place Order')]")
	private WebElement PlaceOrder;
	
	public CheckoutPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void SelectCheckoutType(WebDriver driver) {
		ScrollDown(driver);
		CheckoutTypeContinue.click();
	}
	
	public void AddShippingAddress(WebDriver driver,String Firstname,String LastName,String Email,String Address1,String City,String State,String Zipcode,String mobileno) {
		try {
			BillingFirstName.sendKeys(Firstname);
			BillingLastName.sendKeys(LastName);
			BillingEmail.sendKeys(Email);
			BillingAddress.sendKeys(Address1);
			BillingCity.sendKeys(City);
			SelectDdpByText(RegionId, State);			
			Zip.sendKeys(Zipcode);
			Telephone.sendKeys(mobileno);
			ScrollDown(driver);
			waitForElement(driver, 30, ShipToAddressContinue);
			ShipToAddressContinue.click();
			ScrollUp(driver);
			waitForElement(driver, 60, ShippingMethod);
			ShippingMethod.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void PaymentInfo(WebDriver driver) 
	  {
		waitForElement(driver, 30, MoneyOrder);
		MoneyOrder.click();
		waitForElement(driver, 30, PaymentSaveContinue);
		PaymentSaveContinue.click();		
		
	}
	
	public MagentoCommerce OrderOverView(WebDriver driver) {
		
		waitForElement(driver, 30, PlaceOrder);
		PlaceOrder.click();
		
		return new MagentoCommerce(driver);
	}
}
