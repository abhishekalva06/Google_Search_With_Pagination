package bonus;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class MultipleBrowserSequentialRunner {
	String project_directory;
	
	WebDriver driver=null;
	
	//Enter the input (Company name) to find in the pages as per the problem statement
	static String searchkeyword="finzy";
	//Enter an input to search for
	static String searchtext = "peer to peer lending";
	//Enter a google search keyword or keep it same as searchtext
	static String googlesearchboxsuggestedinput = "peer to peer lending bangalore";
	static String googleurl = "http://google.com";
	
	@Parameters({"browser"})
	@BeforeTest
	public void openBrowser(String browser) {
		Path currentRelativePath = Paths.get("");
		project_directory = currentRelativePath.toAbsolutePath().toString();
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.out.println("Opening chrome browser");
			System.setProperty("webdriver.chrome.driver", project_directory+"/Drivers/chromedriver");
			Thread thread = new Thread();
		    thread.start();
		    driver = new ChromeDriver();
		    
		}
		
		if(browser.equalsIgnoreCase("firefox")) {
			System.out.println("Opening firefox browser");
			Thread thread = new Thread();
		    thread.start();
		    driver = new FirefoxDriver();
		}
	}
	
	@Test
	public void testmethod() throws Exception {
		    driver.get(googleurl);
		    driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchtext);
		    //ul[@role='listbox']//descendant::div[@class='sbl1']
		    Thread.sleep(3000);
       
       
		    List <WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbl1']"));
       
		    System.out.println(list.size());
		    for(int i=0; i<list.size();i++) {
            String listitem= list.get(i).getText();

            System.out.println(listitem.toString());
		    }
       
		    for(int i=0; i<list.size();i++)
		    {
            String listitem= list.get(i).getText();
            //System.out.println(listitem.toString());
            System.out.println(listitem);
            if(listitem.contains(googlesearchboxsuggestedinput)) {
                list.get(i).click();
                break;
            }
     
		    }
		    Thread.sleep(1000);
       
		    matching_results();
	}
	public void matching_results() throws Exception {
				//for matching with g results
				List <WebElement> listw = driver.findElements(By.xpath("//div[@class='g']"));
				//for matching with blue links
				//List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::h3"));
				//for matching with grey links
				//  List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::cite"));
				//List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::h3"));
				System.out.println(listw.size());
				boolean flag = false;
		        WebElement pageid=driver.findElement(By.xpath("//td[@class='cur']"));
		        String pnum=pageid.getText();
		        //for results at top
		        WebElement pageinfo=driver.findElement(By.xpath("//div[@id='mBMHK']"));
		        String pinfo=pageinfo.getText();
		        
		        for(int i=0; i<listw.size();i++) {
		            String listwitem= listw.get(i).getText();

		            //System.out.println(listwitem.toString());
		        }
		        for(int i=0; i<listw.size();i++)
		        {
		            String listwitem= listw.get(i).getText();
		          //  System.out.println(listwitem);
		            Thread.sleep(2000);
		            if(listwitem.contains(searchkeyword)){
		            flag = true;
		          System.out.println("As per the page results at top, this is the page info: "+pinfo+"& This is the page number: "+pnum);
		           // String abc=pageid.getText();
		         // System.out.println(pageid.toString());
		          //div[@id='mBMHK']
		              //  listw.get(i).click();
		                break;
		            }
		        }
		        if (flag == false) {
		        //	System.out.println("The search keyword :  wasn't found, please wait until pagination finds the keyword for you in the upcoming pages");
		        //	System.out.println("The search keyword : "+searchkeyword+" wasn't found, please wait until pagination finds the keyword for you in the upcoming pages");
		        	System.out.println("The search keyword: "+searchkeyword+" is not present in the page : "+pnum+" As per the search results : "+pinfo);
		        driver.findElement(By.xpath("//div[@role='navigation']//descendant::span[text()='Next']")).click();
		        Thread.sleep(1000);
		        matching_results();
		        }
		}	    
		    
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}
