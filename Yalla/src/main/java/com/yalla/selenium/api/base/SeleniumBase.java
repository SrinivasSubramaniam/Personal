package com.yalla.selenium.api.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yalla.selenium.api.design.Browser;
import com.yalla.selenium.api.design.Element;

import reports.Reports;

public class SeleniumBase extends Reports implements Browser, Element {
	int i;
	public RemoteWebDriver driver;
	WebDriverWait wait;

	@Override
	public void click(WebElement ele) {
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			/*System.out.println("The Element " + ele + " clicked");*/
			reportstep("The Element " + ele + " clicked", "pass");
		} catch (StaleElementReferenceException e) {
			/*System.err.println("The Element " + ele + " could not be clicked");*/
			reportstep("The Element " + ele + " clicked", "fail");
			throw new RuntimeException();
		}

	}

	@Override
	public void append(WebElement ele, String data) {
		
			try {
				ele.sendKeys(data);
				reportstep("The Data :" + data + " entered Successfully","pass");
			} catch (ElementNotInteractableException e) {
				// TODO Auto-generated catch block
				reportstep("The Element " + ele + " is not Interactable","fail");
				throw new RuntimeException();
			} catch (IllegalArgumentException e) {
				reportstep("Send Keys shouldn't be sent as NULL","fail");
				throw new RuntimeException();
			}finally
			{
				takeSnap();
			}
		

	}

	@Override
	public void clear(WebElement ele) {
		try {
			ele.clear();
			reportstep("The data in the field cleared successfully","pass");
		} catch (InvalidElementStateException e) {
			// TODO Auto-generated catch block
			reportstep("The data in the " + ele + "couldn't be cleared","fail");
			throw new RuntimeException();
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportstep("The Data :" + data + " entered Successfully","pass");
		} catch (ElementNotInteractableException e) {
			reportstep("The Element " + ele + " is not Interactable","fail");
			throw new RuntimeException();
		}finally {
			takeSnap();
		}

	}

	@Override
	public String getElementText(WebElement ele) {
		String text = ele.getText();
		System.out.println(text);
		/*takeSnap();*/
		return text;
		
	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		String bgcolor = ele.getCssValue("background-color");
		System.out.println("The background color "+bgcolor+" is retrieved succesfully" );
		takeSnap();
		return bgcolor;
	}

	@Override
	public String getTypedText(WebElement ele) {
		String attribute = ele.getAttribute("value");
		System.out.println("The element typed text  "+attribute+" is retrieved succesfully" );
		takeSnap();
		return attribute;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
			Select sc = new Select(ele);
			sc.selectByVisibleText(value);
			reportstep("Drop down by using text: " + ele + " selected successfully","pass");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportstep("Drop down using text " + ele + " not found","fail");
			throw new RuntimeException();
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		try {
			Select dropdown = new Select(ele);
			dropdown.selectByIndex(index);
			reportstep("Index value selected Successfully","pass");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportstep(ele + " :Index value not found","fail");
			throw new RuntimeException();
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		try {
			Select sc = new Select(ele);
			sc.selectByValue(value);
			reportstep("Drop down by using text: " + ele + " selected successfully","pass");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportstep("Drop down using text " + ele + " not found","fail");
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			String actualText = ele.getText();
			if(actualText.equals(expectedText)) {
				reportstep("The element text "+ actualText+" matched successfully","pass");
				return true;			
			}else {
				reportstep("The element text "+ actualText+" didn't match exactly","fail");
			return false;
			}
		} finally {
			// TODO Auto-generated catch block
			takeSnap();
		}
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			String actualText = ele.getText();
			if(actualText.contains(expectedText)) {
				reportstep("The element text "+ actualText+" partially matched successfully","pass");
				return true;			
			}else {
				reportstep("The element text "+ actualText+" didn't match partially","fail");
			return false;
			}
		} finally {
			// TODO Auto-generated catch block
			takeSnap();
		}
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			// TODO Auto-generated method stub
			String ExactAttribute = ele.getAttribute(attribute);
			if(ExactAttribute.equals(value)) {
				reportstep("The attribute value : "+ExactAttribute+"matched succesfully","pass");
				return true;
				
			}else
			{
				reportstep("The attribute value : "+ExactAttribute+"didn't match exactly","fail");
			return false;
			}
		} finally {
			// TODO Auto-generated catch block
			takeSnap();
		}
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			// TODO Auto-generated method stub
			String ExactAttribute = ele.getAttribute(attribute);
			if(ExactAttribute.contains(value)) {
				reportstep("The attribute value : "+ExactAttribute+"matched succesfully","pass");
				
				
			}else
			{
				reportstep("The attribute value : "+ExactAttribute+"didn't match exactly","fail");
			
			}
		} finally {
			// TODO Auto-generated catch block
			takeSnap();
		}

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		try {
			// TODO Auto-generated method stub
			boolean element = ele.isDisplayed();
			if(element==true)
			{
				reportstep("The element : "+element+ "is displayed","pass");
			return true;
			}
			else
			{
				reportstep("The element : "+element+ "is not displayed","fail");
				return false;
			}
		} finally {
			// TODO Auto-generated catch block
			takeSnap();
		}
	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		try {
			boolean element = ele.isDisplayed();
			if(element==false)
			{
				reportstep("The element : "+element+ "is disappeared","pass");
			return true;
			}
			else
			{
				reportstep("The element : "+element+ "is still displayed","fail");
				return false;
			}
		} finally {
			
			takeSnap();
		}
	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		
		try {
			boolean enabled = ele.isEnabled();
			if (enabled==true)
			{
				reportstep("The element :"+ ele + "is enabled","pass");
				return true;
			}else
			{
				reportstep("The element :"+ ele + "is not enabled","false");
			return false;
			}
		} finally {
			takeSnap();
		}
	}

	@Override
	public boolean verifySelected(WebElement ele) {
		boolean selected = ele.isSelected();
		if (selected==true)
		{
			reportstep("The element: "+ele+" is selected","pass");
			takeSnap();
			return true;
		}else
		{
			reportstep("The element: "+ele+" is not selected","fail");
			takeSnap();
		return false;
		}
	}

	@Override
	public void startApp(String url) {
		// TODO Auto-generated method stub
		try {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			reportstep ("The Chrome Browser has launcehd successfully","pass");
		} catch (Exception e) {
			reportstep("The Chrome Browser Could not be Launched. Hence Failed","fail");
			throw new RuntimeException();
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public void startApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				ChromeOptions op = new ChromeOptions();
				op.addArguments("--disable-notifications");
				driver = new ChromeDriver(op);
				
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			reportstep("The browser launched successfully","pass");
		} catch (Exception e) {
			reportstep("The Browser Could not be Launched. Hence Failed","fail");
			throw new RuntimeException();
		}

	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			switch (locatorType.toLowerCase()) {
			case "id":
				return driver.findElementById(value);
			case "name":
				return driver.findElementByName(value);
			case "class":
				return driver.findElementByClassName(value);
			case "link":
				return driver.findElementByLinkText(value);
			case "xpath":
				return driver.findElementByXPath(value);
			}
			reportstep("The Element with locator:" + locatorType + " is found successfully " + value,"pass");
		} catch (NoSuchElementException e) {
			reportstep("The Element with locator:" + locatorType + " Not Found with value: " + value,"fail");
			//throw new RuntimeException();
		}
		return null;
	}

	@Override
	public WebElement locateElement(String value) {
		try {
			// TODO Auto-generated method stub
			WebElement eleId = driver.findElementById(value);
			reportstep("The Element with locator id is found with value: " + value,"pass");
			return eleId;
			
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			reportstep("The Element with locator id is not found with value: " + value,"fail");
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<WebElement> locateElements(String type, String value) {
		// TODO Auto-generated method stub
		try {
			switch (type.toLowerCase()) {
			case "id":
				return driver.findElementsById(value);
			case "name":
				return driver.findElementsByName(value);
			case "class":
				return driver.findElementsByClassName(value);
			case "link":
				return driver.findElementsByLinkText(value);
			case "xpath":
				return driver.findElementsByXPath(value);
			}
			reportstep("The Elements with locator:" + type + " is Found with value: " + value,"pass");
		} catch (NoSuchElementException e) {
			reportstep("The Element with locator:" + type + " Not Found with value: " + value,"fail");
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public void switchToAlert() {
		try {
			driver.switchTo().alert();
			reportstep("Switched to alert successfully","pass");
		} catch (NoAlertPresentException e) {
			reportstep("No alert present","fail");
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public void acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			reportstep("Alert accepted successfully","pass");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportstep("No alert present","fail");
		}

	}

	@Override
	public void dismissAlert() {
		try {
			driver.switchTo().alert().dismiss();
			reportstep("Alert dismissed successfully","pass");
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportstep("No alert present","fail");
		}
	}

	@Override
	public String getAlertText() {
		try {
			// TODO Auto-generated method stub
			String text = driver.switchTo().alert().getText();
			reportstep("The alert text : "+text+" is retrieved successfully","pass");
			return text;
		} catch (NoAlertPresentException e) {
			// TODO Auto-generated catch block
			reportstep("No alert present","fail");
		}finally
		{
			takeSnap();
			
		}
		return null;
		
	}

	@Override
	public void typeAlert(String data) {
		try {
			driver.switchTo().alert().sendKeys(data);
			reportstep("The text: "+data+" is entered successfully in Alert","pass");
		} catch (NoSuchWindowException e) {
			// TODO Auto-generated catch block
			reportstep("No alert present","fail");
		}finally
		{
			takeSnap();
		}

	}

	@Override
	public void switchToWindow(int index) {
		try {
			Set<String> allwindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<>(allwindows);
			String exwindow = allhandles.get(index);
			driver.switchTo().window(exwindow);
			reportstep("Window index " + index + " foud successfully","pass");
		} catch (NoSuchWindowException e) {
			reportstep("Window index " + index + " couldn't be found","fail");
		}finally {
			takeSnap();
		}
	}

	@Override
	public void switchToWindow(String title) {
		try {
			Set<String> allwindows = driver.getWindowHandles();
			for (String eachwindow : allwindows) {
				driver.switchTo().window(eachwindow);
				if (driver.getTitle().equalsIgnoreCase(title)) {
					break;
				}
			}
			reportstep("Window with the tile "+ title + " is switched","pass");
		} catch (NoSuchWindowException e) {
			// TODO Auto-generated catch block
			reportstep("Window with the tile "+ title + " is not switched","fail");
		}finally {
			takeSnap();
		}
		

	}

	@Override
	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
			reportstep("Switched to frame index: "+ index+ " successfully","pass");
		} catch (NoSuchFrameException e) {
			// TODO Auto-generated catch block
			reportstep("Frame index :"+ index+" is not present","fail");
		}

	}

	@Override
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			reportstep("Switched to frame using welement: "+ ele+ " successfully","pass");
		} catch (NoSuchFrameException e) {
			// TODO Auto-generated catch block
			reportstep("Frame is not present","fail");
		}catch (StaleElementReferenceException e) {
			reportstep("Element is not present to switch frame","fail");
		}

	}

	@Override
	public void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
			reportstep("Switched to frame using idorName: "+ idOrName+ " successfully","pass");
		} catch (NoSuchFrameException e) {
			// TODO Auto-generated catch block
			reportstep("Frame is not present","fail");
		}

	}

	@Override
	public void defaultContent() {
		driver.switchTo().defaultContent();
		System.out.println("Frame is switched to default content successfully");

	}

	@Override
	public boolean verifyUrl(String url) {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains(url))
		{
			reportstep("The browser url: "+currentUrl+ "is matched successfully","pass");
		return true;
		}
		else {
			reportstep("The browser url: "+currentUrl+ "is not matched","fail");
		return false;
		}
	}

	@Override
	public boolean verifyTitle(String title) {
		String actual = driver.getTitle();
		if (actual.contains(title)) {
			reportstep("The page title "+ title+" matched successfully","pass");
			return true;
			
		}else
		{
			reportstep("The page title "+ title+" is not matched successfully","fail");
		return false;
		}
	}

	@Override
	public void takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./snaps/snap" + i + ".png");
		try {
			FileUtils.copyFile(src, des);
			reportstep("Snap shot take successfully","pass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			reportstep("Snap shot couldn't be taken","fail");
		}
		i++;

	}

	@Override
	public void close() {
		driver.close();

	}

	@Override
	public void quit() {
		driver.quit();

	}

}
