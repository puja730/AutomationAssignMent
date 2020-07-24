package com.test.automation.Generic.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.ApiAutomation.service.Service;
import com.test.automation.Generic.CommonMethods.Constants;
import com.test.automation.Generic.CommonMethods.Excel_Reader;
import com.test.automation.Generic.CommonMethods.ResponseParser;
import com.test.automation.Generic.CommonMethods.WebdriverFunctions;
import com.test.automation.Generic.customListner.WebEventListener;

/**
 * 
 * @author  Puja Kumari
 *
 */
public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public RemoteWebDriver driver;
	Excel_Reader excel;
	// public EventFiringWebDriver driver;
	public WebEventListener eventListener;
	public Properties OR = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
    protected Service service;
    public static String EmailExt;
	public static String RemoteUrl;
    protected Response response;

	public WebDriver getDriver() {
		return driver;
	}

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") +Constants.Report_Filepath+ formater.format(calendar.getTime()) + ".html", false);
	}

	public void loadData() throws IOException {
		File file = new File(System.getProperty("user.dir") + Constants.Config_Filepath);
		FileInputStream f = new FileInputStream(file);
		OR.load(f);

	}
	
	public void Setdata(String key,String Value) throws IOException {
		loadData();		
        OutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + Constants.Config_Filepath);          
        OR.setProperty(key,Value);	  
        OR.store(outputStream, null);
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public void init(String browser) throws IOException, ParseException {
		loadData();
		ReadJson();
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		System.out.println(OR.getProperty("browser"));
		System.out.println("condition is "+!OR.getProperty("browser").equalsIgnoreCase("Headless"));
		if(OR.getProperty("Remote").equalsIgnoreCase("false")) {
			if (browser == null) {
				selectBrowser(OR.getProperty("browser"));
			} else {
				selectBrowser(browser);
			}
			
		}
		else {
		
			launchapp(browser);			
		}
		
		getUrl(OR.getProperty("url"));
	}

	public void initAPI() throws IOException {
		loadData();
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		
	}
	
	public void selectBrowser(String browser) {
		System.out.println(System.getProperty("os.name"));
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("browser path is "+Constants.Driver_Filepath);
				//System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +Constants.Driver_Filepath);
				/*
				 * driver = new ChromeDriver(); System.out.println("Launced Window OS -Chrome");
				 */
				
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				
				options.addArguments("disable-infobars");
				//capabilities.setCapability("chrome.binary", "<Path to binary>");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);
				//driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
				// driver = new EventFiringWebDriver(dr);
				// eventListener = new WebEventListener();
				// driver.register(eventListener);
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + Constants.Driver_Filepath_FF);
				DesiredCapabilities desiredCapabilities=DesiredCapabilities.firefox();
				
				desiredCapabilities.setPlatform(Platform.WIN8);
				desiredCapabilities.setCapability("marionette",true);
				
				
				
				driver = new FirefoxDriver();
				// driver = new EventFiringWebDriver(dr);
				//eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
			
			else if (browser.equalsIgnoreCase("IE")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + Constants.Driver_Filepath_IE);
				driver = new InternetExplorerDriver();
				// driver = new EventFiringWebDriver(dr);
				//eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equals("chrome")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + Constants.Driver_Filepath);
				//driver = new ChromeDriver();
				System.out.println("Launced Mac OS -Chrome");
				driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
				// driver = new EventFiringWebDriver(dr);
				// eventListener = new WebEventListener();
				// driver.register(eventListener);
			} else if (browser.equals("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + Constants.Driver_Filepath);
				driver = new FirefoxDriver();
				// driver = new EventFiringWebDriver(dr);
				eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
		}
	}

	public void getUrl(String url) {
		log.info("navigating to :-" + url);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("Window got maximized successfully");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + Constants.TestData_Filepath + excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void getScreenShot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + Constants.ScreenShot_Filepath;
			File destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void highlightMe(WebDriver driver, WebElement element) throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute javascript
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		Thread.sleep(3000);
		js.executeScript("arguments[0].style.border=''", element);
	}

	public Iterator<String> getAllWindows() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	}

	public void getScreenShot(WebDriver driver, ITestResult result, String folderName) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + Constants.ScreenShot_Filepath;
			File destFile = new File((String) reportDirectory + "/" + folderName + "/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");

			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getScreenShotOnSucess(WebDriver driver, ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + Constants.ScreenShot_Filepath;
			File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");

			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String captureScreen(String fileName) {
		String ScreenShot="API is invoked";
		if (fileName == "") {
			fileName = "Test";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        System.out.println("driver value is "+driver);
        if(driver!=null) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + Constants.ScreenShot_Filepath;
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			System.out.println("Destination folder is "+destFile);
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>",true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
		
        }
        return ScreenShot;
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");			
			String screen = captureScreen("");
			test.log(LogStatus.PASS, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.SKIP, test.addScreenCapture(screen));		
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
		extent.endTest(test);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		//test = extent.startTest(result.getName());
		test = extent.startTest("captureScreenshot");
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		//driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
		extent.close();
	}

	public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	//@Parameters("browser")
	//@BeforeTest
	public void launchapp(String browser) throws IOException {
		DesiredCapabilities dc=new DesiredCapabilities();
		
		RemoteUrl=OR.getProperty("Remoteurl");
		
		System.out.println("Remote url is : "+RemoteUrl);
		
		if (OR.getProperty("Remoteplatform").equalsIgnoreCase("Linux")) {
			
			if (browser.equalsIgnoreCase("chrome")) {
				
				System.out.println(" Executing on CHROME");
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);	
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX); 
				URL url =new URL(RemoteUrl);		
				driver =new RemoteWebDriver(url, dc);
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			} else if (browser.equalsIgnoreCase("firefox")) {
				
				System.out.println(" Executing on FireFox");
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);	
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX); 
				URL url =new URL(RemoteUrl);		
				driver =new RemoteWebDriver(url, dc);
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			} else if (browser.equalsIgnoreCase("ie")) {
				
				System.out.println(" Executing on IE");
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);	
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX); 
				URL url =new URL(RemoteUrl);		
				driver =new RemoteWebDriver(url, dc);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
		if (OR.getProperty("Remoteplatform").contains("Window")) {
			if (browser.equals("chrome")) {
				
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);	
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX); 
				URL url =new URL(RemoteUrl);		
				driver =new RemoteWebDriver(url, dc);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								
			} else if (browser.equals("firefox")) {
				
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);	
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX); 
				URL url =new URL(RemoteUrl);		
				driver =new RemoteWebDriver(url, dc);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			} else if (browser.equalsIgnoreCase("ie")) {
				System.out.println(" Executing on IE");
				
				dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);	
				dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX); 
				URL url =new URL(RemoteUrl);		
				driver =new RemoteWebDriver(url, dc);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
	}

	
	public void WaitforWebElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void ScrollDown(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
	}
	
	public void ScrollUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-100)");
	}

	

	public static void SelectDdpByText(WebElement element, String text) {

		Select select = new Select(element);

		select.selectByVisibleText(text);

	}

	public static void SelectDdpByValue(WebElement element, String value) {

		Select select = new Select(element);

		select.selectByValue(value);

	}

	public static void SelectDdpByIndex(WebElement element, Integer index) {

		Select select = new Select(element);

		select.selectByIndex(index);

	}
	
	
public void ReadJson()  {
		try {
			if(System.getProperty("System")!=null) {
			System.out.println("System File is *********** "+System.getProperty("System"));	
			JSONArray SystemOnevalue = ResponseParser.JsonParser(System.getProperty("System"));	    
			
			SystemOnevalue.forEach( emp -> parseJsonObject( (JSONObject) emp ) );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

public void parseJsonObject(JSONObject employee) 
{   
	String browser = (String) employee.get("browser");
	String url = (String) employee.get("url");
	String OS = (String) employee.get("OS");
	String browserVersion = (String) employee.get("browserVersion");
	String UserName = (String) employee.get("UserName");
	String Password = (String) employee.get("Password");
	
	String Remote = (String) employee.get("Remote");
	String Remoteurl = (String) employee.get("Remoteurl");
	String Remoteplatform = (String) employee.get("Remoteplatform");
	
	try {
		
		if(!browser.equals(null)) {
		Setdata("browser", browser);
		Setdata("url", url);
		Setdata("OS", OS);
		Setdata("browserVersion", browserVersion);
		Setdata("UserName", UserName);
		Setdata("Password", Password);
		Setdata("Remote", Remote);
		}		
		else if(Remote.equalsIgnoreCase("true")) {
			
			Setdata("Remote", Remote);
			Setdata("Remoteurl", Remoteurl);
			Setdata("Remoteplatform", Remoteplatform);
			
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
   
}

	
}
