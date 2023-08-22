package Sample;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Test2 
{
   public static int size;
	@Test
	public void Brokenlinks() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("http://amazon.com/");
		
		Thread.sleep(5000);
		
	 /*	1. Capture the links
		    Most the links start with a tag  
		*/
            List<WebElement> links=   driver.findElements(By.tagName("a"));	
           System.out.println(links.size());
       size =links.size();
           
           //Adding all the url in array List
           List<String> urllist= new ArrayList<String>();
   
           for(WebElement element:links)
           {
        	   String url=	element.getAttribute("href"); 
        	  //Adding all the url in the Array List
        	 urllist.add(url);
        	 System.out.println(url);
           }
          
         long StartTime=System.currentTimeMillis();  
        //Doing for parallel stream    
       urllist.parallelStream().forEach(e -> CheckBrokenLink(e));
       long EndTime= System.currentTimeMillis();
       
       System.out.println("total time taken: " +(EndTime-StartTime));
       //For parallel stream time -  total time taken: 67658 = 67.6 sec
       //For normal stream need to remove parallelStream() and insert stream()
       //For Normal Stream time - total time taken: 149818=149.8 sec
       
       driver.quit();
	}
	
	
     public static void CheckBrokenLink(String linkurl)
     {
    	 
    	 try 
    	 {
		     URL url= new URL(linkurl);
    		 HttpURLConnection httpcon=(HttpURLConnection) url.openConnection();
    	        //Establish the connection
    	     httpcon.setConnectTimeout(5000);   
    		 httpcon.connect();
    	     int brokencount=0;
    	     int workingcount=0;
    	     
    	      
    		 if(size>0)
    		 {	 
    		 if(httpcon.getResponseCode()>=400)
    		 {
    			 System.out.println(linkurl + "--------------->" + httpcon.getResponseMessage()+ " is a Broken Link");
    			 brokencount++;
    		 }
    		 
    		 else
    		 {
    			 System.out.println(linkurl+ "----------------->"+ httpcon.getResponseMessage()+ "working fine");
    			 workingcount++;
    		 }
    		 
    		 }
    		 System.out.println(brokencount);
    		 System.out.println(workingcount);

    		 
    	 }
    	
    	 catch (Exception e) 
    	 {
		
    		 
    	 }	
    	 
    	 
     }
   
	
	
	@Test
	public void ICCRankings()
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver;
		driver= new ChromeDriver();
		driver.get("https://www.icc-cricket.com/rankings/mens/player-rankings/test");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		WebElement newsize=driver.findElement(By.xpath("(//div[@class='col-4 col-12-desk touch-scroll-list__element'])[3]"));
		System.out.println(newsize.getText());
		
	//List of Test Allrounder Rankings	
	List<WebElement> list=	driver.findElements(By.xpath("//div[@class='rankings-block__container ' and @data-title='Test All-Rounder Rankings']"));
	System.out.println(list.size());
	for(int i=0;i<list.size();i++)
	{
		System.out.print(list.get(i).getText());
		System.out.println(" ");
	}
	
	
		
	//1.Getting third rank of Test Allrounder
	
	WebElement name=driver.findElement(By.xpath("//div[@data-title='Test All-Rounder Rankings']/descendant::table/tbody/tr[@class='table-body'][2]"));
	
	System.out.println(name.getText());
	System.out.println(list.get(3).getText());
	
	
	//2. 10th position name of Test Batting Rankings
	
	
	List<WebElement> rankings=driver.findElements(By.xpath("//div[@class='rankings-block__container ' and @data-title='Test All-Rounder Rankings']//preceding::div[@data-title='Test Batting Rankings']"));
	for(int i=0;i<rankings.size();i++)
	{
		System.out.println(rankings.get(i).getText());
		System.out.println(" ");
	}
	  
	WebElement position= driver.findElement(By.xpath("//div[@data-title='Test Batting Rankings']/table/tbody/tr[9]"));
	
	System.out.println(position.getText());
	System.out.println(rankings.get(10).getText());
		
	
	//3. Moving from 10th position to 5th position in Test Batting Rankings
	
WebElement fifth=driver.findElement(By.xpath("//div[@data-title='Test Batting Rankings']/table/tbody/tr[9]/ancestor::div[@class='col-4 col-12-desk touch-scroll-list__element']/div/table/tbody/tr[4]"));
	System.out.println(fifth.getText());
	System.out.println(rankings.get(5).getText());
	
	
	
	//4. 2nd position name in Test Bowler Rankings
	List<WebElement> bowler=driver.findElements(By.xpath("//div[@class='rankings-block__container ' and @data-title='Test Batting Rankings']//following::div[@data-title='Test Bowling Rankings']"));
	for(int i=0;i<bowler.size();i++)
	{
		System.out.println(bowler.get(i).getText());
		
	}
	WebElement second=driver.findElement(By.xpath("//div[@data-title='Test Bowling Rankings']/table/tbody/tr[1]"));
	
		 System.out.println(second.getText());
		 System.out.println(bowler.get(2).getText());
	  

	//5. Moving from 2nd position name to 7th position name in Test Bowling Rankings
	
   WebElement index=driver.findElement(By.xpath("//div[@data-title='Test Bowling Rankings']/table/tbody/tr[1]//following::tr[@class='table-body'][5]"));
	System.out.println(index.getText());
	 System.out.println(bowler.get(7).getText());
  
		
	
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,500)");
		
		driver.close();
	}

    @Test
    public void QSpidersDynamicdropdown()
    {
    	WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
		  driver.get("https://www.google.com");
		  String url=driver.getCurrentUrl();
		  System.out.println(url);
		  
		WebElement name=  driver.findElement(By.xpath("//textarea[@name='q']"));
		name.click();		
		name.sendKeys("Qspiders");
		  
		//when we are taking ul or li it is not displaying all elements so taking common element of all the values
		//same for all dynamic dropdown - Qspider , Flipkart
		
		  List<WebElement> search=driver.findElements(By.xpath("//div[@class='pcTkSc']"));
		  for(WebElement sample:search)
		  {
			  
			  System.out.println(sample.getText());
		  }
			int a=0;
			 for(int i=0;i<search.size()-1;i++)
			 {
				 String text=search.get(i).getText();
				 System.out.println(text);
				 if(text.contains("qspiders hyderabad kphb photos"))
				 {
					 search.get(i).click();
					 a=i;
					 System.out.println(a);
					 break;
				 }
			 
			 }
	       	   
    	
    	driver.close();
    	
    }

    @Test
    public void SwagLabs() throws InterruptedException, IOException
    {
    	WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
       driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    
       WebElement jacket=  driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
	  Point p=jacket.getLocation();	
     JavascriptExecutor jse= (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy"+p);
	
	driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket' and text()='Add to cart']")).click();	
	
	Thread.sleep(3000);
	WebElement menubtn=driver.findElement(By.xpath("//button[text()='Open Menu']"));
	Point p1= menubtn.getLocation();
	jse.executeScript("window.scrollBy"+p1);
	
	Actions ac= new Actions(driver);
	ac.moveToElement(menubtn).click().perform();
	Thread.sleep(2000);
	
    WebElement cartbtn=driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));	
    cartbtn.click();
    
    Thread.sleep(3000);
    WebElement checkoutbtn=driver.findElement(By.name("checkout"));
    checkoutbtn.click();
    
    driver.findElement(By.id("first-name")).sendKeys("Mukesh");
    driver.findElement(By.id("last-name")).sendKeys("Smart");
    driver.findElement(By.id("postal-code")).sendKeys("517502");
    Thread.sleep(3000);
    driver.findElement(By.id("continue")).click();
    
    WebElement checkouttitle=driver.findElement(By.xpath("//span[text()='Checkout: Overview']"));
    
    WebDriverWait wait= new WebDriverWait(driver,30);
    wait.until(ExpectedConditions.visibilityOf(checkouttitle));
    
    Date date= new Date();
	String screenshot = date.toString().replace(":", "-");
	System.out.println(screenshot);
	
	Screenshot screen= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	ImageIO.write(screen.getImage(), "PNG", new File("./screenshot/SWAGLabs'" + screenshot + "'.png"));
	
	
	
	driver.findElement(By.id("finish")).click();
		
	driver.findElement(By.id("back-to-products")).click();
	
	driver.close();
    }

}
