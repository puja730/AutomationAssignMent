package com.test.automation.Generic.CommonMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.automation.Generic.Base.TestBase;

public class WebdriverFunctions extends TestBase{
	public  void Click(WebDriver driver,WebElement element) {
		
		try {
			waitForElement(driver, 50, element);
			element.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=========== Failed to Find Element ==============");
			System.out.println("Element Not Found");
		}
		
	}

	public  void SendKeys(WebDriver driver,WebElement element,String text) {
		
		try {
			waitForElement(driver, 50, element);
			element.sendKeys(text);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=========== Failed to Find Element ==============");
			System.out.println("Element Not Found");
		}
		
	}

	public  void SelectCheckBox(WebDriver driver,WebElement element) {
		
		try {
			waitForElement(driver, 50, element);
			if(!element.isSelected()) {
			element.click();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=========== Failed to Find Element ==============");
			System.out.println("Element Not Found");
		}
		
	}

	public  void SwitchToFrame(WebDriver driver,WebElement element) {
		
		try {
			//driver.switchTo().defaultContent();
			driver.switchTo().frame(element);
			System.out.println("********* Frame Siwtched Successfully***********");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=========== Failed to Find Element-Frame/Switch to framme ==============");
			System.out.println("Element Not Found");
		}
		
	}

	public  String GetText(WebDriver driver,WebElement element) {
		String text=null;
		try {
			waitForElement(driver, 50, element);
			element.getText();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=========== Failed to Find Element-Frame/Switch to framme ==============");
			System.out.println("Element Not Found");
		}
		
		return text;
	}

	public  Boolean IsDisplayed(WebDriver driver,WebElement element) {
		Boolean flag=null;
		try {
			waitForElement(driver, 50, element);
			flag=element.isDisplayed();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("=========== Failed to Find Element-Frame/Switch to framme ==============");
			System.out.println("Element Not Found");
		}
		
		return flag;
	}
	public void JavascrptClick(WebDriver driver,WebElement element) {

		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		    System.out.println("Javascript click has an error"+e.getMessage());
		}
	}
	
	public void ScrollDown(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
}
