package projectDay;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

import testcases.Annotations;

public class Facebook  extends Annotations{
	@BeforeClass
	public void setData()
	{
		testcaseName="Facebook";
		testcaseDesc="Facebook search";
		author="Srini";
		category="Functional";
	}
	@Test
	public void search() throws InterruptedException
	{
		
		
		WebElement eleSearch = locateElement("xpath", "//input[@data-testid='search_input']");
		
		clearAndType(eleSearch, "Testleaf");
		WebElement eleSearchButton = locateElement("xpath", "//button[@data-testid='facebar_search_button']");
		click(eleSearchButton);
		Thread.sleep(2000);
		WebElement eleResult = locateElement("xpath", "//a[@data-testid='browse-result-link']/div");
		verifyPartialText(eleResult, "Testleaf");
		WebElement eleLike = locateElement("xpath", "//a[@data-testid='browse-result-link']/div/..//following::button");
		String likeText = getElementText(eleLike);
		if (likeText.equalsIgnoreCase("like"))
		{
			click(eleLike);
			System.out.println("printed liked");
			
		}else
		{
			System.out.println("just printed liked");
		}
		WebElement eleLink = locateElement("xpath", "//a[@data-testid='browse-result-link']");
		click(eleLink);
		verifyTitle("Testleaf");
		WebElement likecount = locateElement("xpath", "//span[text()='Community']/..//following::div[contains(text(),'people like this')]");
		getElementText(likecount);
		
	
		
		
		
	}

}
