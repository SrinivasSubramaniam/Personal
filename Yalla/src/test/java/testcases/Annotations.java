package testcases;

import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotations extends SeleniumBase {
   @BeforeMethod
  public void beforeMethod() {
	  startApp("chrome", "https://www.facebook.com/");
	 	/*WebElement eleUserName = locateElement("id", "username");
		clearAndType(eleUserName, "DemoSalesManager");
		WebElement elePassword = locateElement("id", "password");
		clearAndType(elePassword, "crmsfa");
		WebElement eleLogin = locateElement("class", "decorativeSubmit");
		click(eleLogin);*/
	  WebElement eleUserName = locateElement("id", "email");
		clearAndType(eleUserName, "yakesh.elango@gmail.com");
		WebElement elePassword = locateElement("id", "pass");
		clearAndType(elePassword, "yashshhe@2929");
		WebElement eleLogin = locateElement("id", "u_0_2");
		click(eleLogin);
		
  }

  @AfterMethod
  public void afterMethod() {
		/*close();	*/
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }

}
