package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.yalla.selenium.api.base.SeleniumBase;

public class CreateLead extends Annotations{
	@BeforeClass
	public void setData()
	{
		testcaseName="Create Lead";
		testcaseDesc="Create a new Lead in leaftaps";
		author="Srini";
		category="Smoke";
	}
	
	@Test
	public void createLead()
	{
		
		WebElement eleCRMSLink = locateElement("link", "CRM/SFA");
		click(eleCRMSLink);
		WebElement eleCreateLead = locateElement("link", "Create Lead");
		click(eleCreateLead);
		WebElement eleCompanyName = locateElement("id", "createLeadForm_companyName");
		clearAndType(eleCompanyName, "CTS");
		WebElement eleFirstName = locateElement("id", "createLeadForm_firstName");
		clearAndType(eleFirstName, "Srinivas");
		WebElement eleLastName = locateElement("id", "createLeadForm_lastName");
		clearAndType(eleLastName, "Subramaniam");
		WebElement eleSource = locateElement("id", "createLeadForm_dataSourceId");
		selectDropDownUsingIndex(eleSource, 2);
		WebElement eleSubmit = locateElement("class", "smallSubmit");
		click(eleSubmit);
		
		
	}

}
