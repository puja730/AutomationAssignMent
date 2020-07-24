package com.test.automation.UiTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.automation.Generic.CommonMethods.Constants;

public class TestClass {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +Constants.Driver_Filepath);
		WebDriver driver = new ChromeDriver();
	
	}
	
}
