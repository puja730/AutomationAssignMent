package com.test.automation.uiAutomation.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.Generic.Base.TestBase;

public class Mobilepage  extends TestBase{
WebDriver driver;

public static final Logger log = Logger.getLogger(Mobilepage.class.getName());

@FindBy(xpath = "(//button[@title='Add to Cart'])[1]")
private WebElement FirstProduct;


public Mobilepage(WebDriver driver) {
	super();
	this.driver = driver;

	PageFactory.initElements(driver, this);
}	

public ShoppingCartPage AddFirstProduct() {
	
	FirstProduct.click();
	
	return new ShoppingCartPage(driver);
	
}

}
