package reports;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BasicReports {
	static ExtentHtmlReporter reporter;
	static ExtentReports extent;
	@BeforeSuite
	public void startReport()
	{
		reporter=new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
	}
	@Test
	public void report() throws IOException
	{
		ExtentTest test = extent.createTest("CreateLead", "Created lead successfully");
		test.assignAuthor("Srini");
		test.assignCategory("Smoke");
		test.pass("username entered successfully");
		test.fail("password not entered", MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/snap1.png.").build());
		
		
	}
	@AfterSuite
	public void stopReports()
	{
		extent.flush();
	}

}
