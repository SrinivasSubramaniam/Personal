package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MergeLead extends Login {
	@BeforeClass
	public void setData()
	{
		testcaseName="Merge Lead";
		testcaseDesc="Merging a new Lead in leaftaps";
		author="Srini";
		category="Smoke";
	}
	@Test
	public void mergeLead() throws InterruptedException
	{
		login();
		WebElement eleCRMSLink = locateElement("link", "CRM/SFA");
		click(eleCRMSLink);
		WebElement eleLeads = locateElement("link", "Leads");
		click(eleLeads);
		/*WebElement eleFindLeads = locateElement("link", "Find Leads");
		click(eleFindLeads);*/
		WebElement eleMergeLeads = locateElement("link", "Merge Leads");
		click(eleMergeLeads);
		WebElement eleFromLeadIcon = locateElement("xpath", "(//a/img)[4]");
		click(eleFromLeadIcon);
		switchToWindow(1);
		WebElement eleFirstName = locateElement("name", "firstName");
		clearAndType(eleFirstName, "lakshmi");
		WebElement findLeads = locateElement("xpath", "//button[text()='Find Leads']");
		click(findLeads);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement result1 = locateElement("class", "linktext");
		String FromName = getElementText(result1);
		click(result1);
		switchToWindow(0);
		WebElement eleToLeadIcon = locateElement("xpath", "(//a/img)[5]");
		click(eleToLeadIcon);
		switchToWindow(1);
		WebElement eleSecondName = locateElement("name", "firstName");
		clearAndType(eleSecondName, "Keerthi");
		WebElement findLeads1 = locateElement("xpath", "//button[text()='Find Leads']");
		click(findLeads1);
		Thread.sleep(2000);
		WebElement result2 = locateElement("class", "linktext");
		click(result2);
		switchToWindow(0);
		WebElement mergeButton = locateElement("link", "Merge");
		click(mergeButton);
		acceptAlert();
		WebElement findLeadslink = locateElement("link", "Find Leads");
		click(findLeadslink);
		Thread.sleep(2000);
		WebElement eleinputid = locateElement("xpath", "//input[@name='id']");
		clearAndType(eleinputid, FromName);
		WebElement findLeadsbutton = locateElement("xpath", "//button[text()='Find Leads']");
		click(findLeadsbutton);
		Thread.sleep(2000);
		WebElement eleresultgrid = locateElement("class", "x-paging-info");
		String eleresultText = getElementText(eleresultgrid);
		if(eleresultText.contains("No records"))
		{System.out.println("Merge happened sucessfully");
			
		}
		close();
		
		
		
	}
}