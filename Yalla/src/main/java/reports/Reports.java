package reports;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	static ExtentHtmlReporter reporter;
	static ExtentReports extent;
	ExtentTest test;
	public String testcaseName, testcaseDesc,author,category;
	@BeforeSuite
	public void startReport()
	{
		reporter=new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
	}
	@BeforeMethod
	public void report() throws IOException
	{
		test = extent.createTest(testcaseName, testcaseDesc);
		test.assignAuthor(author);
		test.assignCategory(category);
		/*test.pass("username entered successfully");
		test.fail("password not entered", MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/snap1.png.").build());*/
		
		
	}
	
	public void reportstep(String desc,String status)
	{
		if (status.equalsIgnoreCase("pass"))
		{
			test.pass(desc);
		}else if (status.equalsIgnoreCase("fail"))
		{
			test.pass(desc);
		}
		
	}
	@AfterSuite
	public void stopReports()
	{
		extent.flush();
	}

}
