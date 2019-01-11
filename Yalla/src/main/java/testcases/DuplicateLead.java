package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class DuplicateLead extends Annotations {
	@BeforeClass
	public void setData()
	{
		testcaseName="Duplicate Lead";
		testcaseDesc="Create a duplicate Lead in leaftaps";
		author="Srini";
		category="Smoke";
	}
	@Test
	public void duplicateLead() throws InterruptedException
	{
		WebElement eleCRMSLink = locateElement("link", "CRM/SFA");
		click(eleCRMSLink);
		WebElement eleLeads = locateElement("link", "Leads");
		click(eleLeads);
		WebElement eleFindLeads = locateElement("link", "Find Leads");
		click(eleFindLeads);
		WebElement eleEditButton = locateElement("xpath", "//span[text()='Email']");
		click(eleEditButton);
		WebElement eleEmail = locateElement("xpath", "//input[@name='emailAddress']");
		click(eleEmail);
		clearAndType(eleEmail, "prabha@testleaf.com");
		WebElement eleFindButton = locateElement("xpath", "//button[text()='Find Leads']");
		click(eleFindButton);
		Thread.sleep(2000);
		/*List<WebElement> eleList = locateElements("xpath", "//div//table[@class='x-grid3-row-table']");*/
		WebElement elerecord = locateElement("xpath", "//div//table[@class='x-grid3-row-table']//tr//td[2]//a");
		String elementText = getElementText(elerecord);
		System.out.println(elementText);
		WebElement elerecordclick = locateElement("xpath", "//div//table[@class='x-grid3-row-table']//tr//td//a");
		click(elerecordclick);
		verifyTitle("View Lead");
		WebElement eleDuplicateLead = locateElement("link", "Duplicate Lead");
		click(eleDuplicateLead);
		verifyTitle("Duplicate Lead");
		WebElement eleCreateLead = locateElement("xpath", "//input[@value='Create Lead']");
		click(eleCreateLead);
		Thread.sleep(2000);
		WebElement eleFirstName = locateElement("id", "viewLead_firstName_sp");
		getElementText(eleFirstName);
		System.out.println(elementText);
		verifyPartialText(eleFirstName, elementText);
	
		
	}

}
