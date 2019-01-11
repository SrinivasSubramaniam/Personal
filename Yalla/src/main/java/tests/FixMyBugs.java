package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class FixMyBugs {

	@Test
	public void test() throws InterruptedException {

		// launch the browser
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();

		// Mouse Over on Men
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByLinkText("Men")).perform();

		// Click on Jackets
		driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
		System.out.println("clicked");


		// Find the count of Jackets
		String leftCount = 
				driver.findElementByXPath("//input[@value='Jackets']//following-sibling::span")
				.getText()
				.replaceAll("\\D", "");
		System.out.println(leftCount);
		Thread.sleep(5000);


		// Click on Jackets and confirm the count is same
		driver.findElementByXPath("//label[text()='Jackets']").click();
		
		
		

		// Wait for some time
		Thread.sleep(5000);

		// Check the count
		String rightCount = 
				driver.findElementByClassName("title-count")
				.getText()
				.replaceAll("\\D", "");
		System.out.println(rightCount);

		// If both count matches, say success
		if(leftCount.equals(rightCount)) {
			System.out.println("The count matches on either case");
		}else {
			System.err.println("The count does not match");
		}

		/*// Click on Offers
		driver.findElementByXPath("//h3[text()='Offers']").click();*/
		
		// Find the costliest Jacket
		List<WebElement> productsPrice = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		System.out.println("product price created");
		List<Integer> onlyPrice = new ArrayList<>();

		for (WebElement productPrice : productsPrice) {
			String eachPrice = productPrice.getText();
			String eachValue = eachPrice.replaceAll("\\D", "");
			int eachRupe = Integer.parseInt(eachValue);
			onlyPrice.add(eachRupe);
		}

		// Sort them 
		Integer max = Collections.max(onlyPrice);
		///Collections.max

		// Find the top one
		System.out.println(max);
		
		/*driver.close();*/

		// Print Only Allen Solly Brand Minimum Price
		driver.findElementByXPath("(//div[@class='filter-search-filterSearchBox'])[2]").click();
		driver.findElementByXPath("//input[@class='filter-search-inputBox']").sendKeys("Allen Solly",Keys.ENTER);
		driver.findElementByXPath("//input[@value='Allen Solly']/following-sibling::div").click();
		Thread.sleep(2000);
		
		// Find the costliest Jacket
		List<WebElement> allenSollyPrices = driver.findElementsByXPath("//span[@class='product-discountedPrice']");

		//List<Integer> onlyAllenPrice = new ArrayList<>();
		onlyPrice = new ArrayList<>();

		for (WebElement productPrice : allenSollyPrices) {
			
			String eachText = productPrice.getText().replaceAll("\\D", "");
			//onlyAllenPrice.add(Integer.parseInt(eachText));
			onlyPrice.add(Integer.parseInt(eachText));
			
		}

		// Get the minimum Price 
		//Integer min = Collections.min(onlyAllenPrice);
		Integer min = Collections.min(onlyPrice);
		// Find the lowest priced Allen Solly
		System.out.println(min);


	}

}
