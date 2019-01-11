package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class EditLead extends Annotations {
	@BeforeClass
	public void setData()
	{
		testcaseName="Edit Lead";
		testcaseDesc="Editing a existing Lead in leaftaps";
		author="Srini";
		category="Smoke";
	}
	@Test
	public void editLead() throws InterruptedException
	{
		
		WebElement eleCRMSLink = locateElement("link", "CRM/SFA");
		click(eleCRMSLink);
		WebElement eleLeads = locateElement("link", "Leads");
		click(eleLeads);
		WebElement eleFindLeads = locateElement("link", "Find Leads");
		click(eleFindLeads);
		WebElement eleFirstName = locateElement("xpath", "//div[@id='findLeads']//input[@name='firstName']");
		click(eleFirstName);
		clearAndType(eleFirstName, "Sangeetha");
		WebElement eleFindButton = locateElement("xpath", "//button[text()='Find Leads']");
		click(eleFindButton);
		Thread.sleep(2000);
		List<WebElement> eleList = locateElements("xpath", "//div//table[@class='x-grid3-row-table']");
		WebElement elerecord = locateElement("xpath", "//div//table[@class='x-grid3-row-table']//tr//td//a");
		click(elerecord);
		verifyTitle("View Lead");
		WebElement eleEditLink = locateElement("link", "Edit");
		click(eleEditLink);
		Thread.sleep(2000);
		WebElement eleCompanyName = locateElement("id", "updateLeadForm_companyName");
		clearAndType(eleCompanyName, "Cognizant");
		WebElement eleSubmit = locateElement("name", "submitButton");
		click(eleSubmit);
		Thread.sleep(2000);
		WebElement eleLeadCompanyName = locateElement("id", "viewLead_companyName_sp");
		verifyPartialText(eleLeadCompanyName, "Cognizant");
	
		
	}

}
