package Sample;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;




public class Test1 
{

	@Test(groups="Smoke")
	public void newtest() throws InterruptedException
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		 driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		driver.navigate().back();
		
		TimeUnit.SECONDS.sleep(2);
		driver.navigate().forward();
		
		driver.close();
			
	}
	
	@Test(groups="Sanity")
	public void newtest1() throws InterruptedException
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		 driver.get("https://www.whatsapp.com");
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		driver.navigate().back();
		
		TimeUnit.SECONDS.sleep(2);
		driver.navigate().forward();
		
		driver.close();
			
	}
	
	@Test(groups="Regression")
	public void newtest3() throws InterruptedException
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		 driver.get("https://www.instagram.com");
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		driver.navigate().back();
		
		TimeUnit.SECONDS.sleep(2);
		driver.navigate().forward();
		
      driver.close();
			
	}
	
	
	
}
