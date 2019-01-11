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
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

import testcases.Annotations;

public class Zoomcar  extends Annotations{
	@BeforeClass
	public void setData()
	{
		testcaseName="Zoom car";
		testcaseDesc="Zoom car search";
		author="Srini";
		category="Functional";
	}
	@Test
	public void search() throws InterruptedException
	{
		
		WebElement eleStartJourneylink = locateElement("link", "Start your wonderful journey");
		click(eleStartJourneylink);
		WebElement elePopularPickPointlinks = locateElement("xpath", "//div[text()='Popular Pick-up points']/following-sibling::div[1]");
		click(elePopularPickPointlinks);
		WebElement eleStartNextButton = locateElement("class", "proceed");
		click(eleStartNextButton);
		// Get the current date
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("dd");
		// Get today's date
		System.out.println(date);
		String today = sdf.format(date);
		// Convert to integer and add 1 to it
		int tomorrow = Integer.parseInt(today)+1;
		// Print tomorrow's date
		System.out.println(tomorrow);
		WebElement eleNextDay = locateElement("xpath", "//div[@class='days']/div[contains(text(),'"+tomorrow+"')]");
		click(eleNextDay);
		System.out.println(eleNextDay);
		WebElement eleNext = locateElement("xpath", "//button[text()='Next']");
		click(eleNext);
		WebElement elePickUpDate = locateElement("xpath", "//div[@class='label time-label']");
		verifyPartialText(elePickUpDate, "+tomorrow+");
		WebElement eleDoneButton = locateElement("xpath", "//button[text()='Done']");
		click(eleDoneButton);
		Thread.sleep(10000);
		List<WebElement> eleSearchResults = locateElements("xpath", "//div[@class='details']/h3");
		int searchResults = eleSearchResults.size();
		System.out.println("Search results are "+ searchResults);
		List<WebElement> eleAmount = locateElements("xpath", "//div[@class='price']");
		eleAmount.get(0).getText();
		List<Integer> eachARupeeAmount=new ArrayList<>();
		for (WebElement eachAmount : eleAmount) {
			
			String text = eachAmount.getText();
			String eachvalue = text.replaceAll("\\D", "");
			System.out.println(eachvalue);
			eachARupeeAmount.add(Integer.parseInt(eachvalue));
			
					
		}
		Integer max = Collections.max(eachARupeeAmount);
		System.out.println("Highest amount is "+ max);
		Thread.sleep(2000);  
		WebElement elebrandName = locateElement("xpath", "//div[contains(text(),'"+max+"')]/../../..//h3");
		String text = elebrandName.getText();
		System.out.println(text);
		WebElement BookNowbutton = locateElement("xpath", "(//div[contains(text(),'"+max+"')]/following::button)[1]");
		click(BookNowbutton);
		
		
		
		
	}

}
