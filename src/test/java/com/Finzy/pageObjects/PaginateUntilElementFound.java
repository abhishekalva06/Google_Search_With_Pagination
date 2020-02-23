package com.Finzy.pageObjects;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class PaginateUntilElementFound {
static WebDriver driver;
static ExtentReports extent;
static ExtentTest elogger;

//Enter the final search keyword here
static String searchkeyword = "finzy";
static String searchtext = "peer to peer lending";
static String googlesearchboxsuggestedinput = "peer to peer lending bangalore";
static String googleurl = "http://google.com";

public static void main(String[] args) throws Exception {
	
	String project_directory;
	String date_time;
	ExtentHtmlReporter reporter;
	ArrayList data = new ArrayList();
	Path currentRelativePath = Paths.get("");
	date_time = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	project_directory = currentRelativePath.toAbsolutePath().toString();
	System.out.println("Please find the report with suffix: "+date_time);

   
// TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", project_directory+"/Drivers/chromedriver");
driver = new ChromeDriver();
reporter = new ExtentHtmlReporter(project_directory + "/Reports/ReportsOfPaginateUntilElementFound/" + date_time + "_automation_report.html");
extent = new ExtentReports();
extent.attachReporter(reporter);
elogger = extent.createTest(date_time + "_automation_report");

elogger.log(Status.INFO,"Navigating to URL: "+googleurl);
driver.get(googleurl);
elogger.log(Status.PASS,"Navigated to URL: "+googleurl);
driver.manage().window().maximize();
elogger.log(Status.INFO,"Entering the search text: "+searchtext);
//Enter search text
driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchtext);
elogger.log(Status.PASS,"Entered the search text: "+searchtext);
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
          //Enter the google search keyword here
            if(listitem.contains(googlesearchboxsuggestedinput)) {
            	elogger.log(Status.INFO,"Clicking on the google searchbox suggestion: "+googlesearchboxsuggestedinput );
                list.get(i).click();
                elogger.log(Status.PASS,"Clicked on the google searchbox suggestion: "+googlesearchboxsuggestedinput);
                break;
            }
     
        }
        Thread.sleep(1000);
        matching_results();
}

public static void matching_results() throws Exception {
	
		//for g results
		List <WebElement> listw = driver.findElements(By.xpath("//div[@class='g']"));
		//blue
		//List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::h3"));
		//grey link
		//  List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::cite"));
		//List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::h3"));
        System.out.println(listw.size());
        boolean flag = false;
        //for pagination result
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
            elogger.log(Status.PASS,"Element: "+searchkeyword+" found in page: "+pnum);
          System.out.println("As per the page results at top, this is the page info: "+pinfo+"& This is the page number: "+pnum);
           // String abc=pageid.getText();
         // System.out.println(pageid.toString());
          //div[@id='mBMHK']
              //listw.get(i).click();
                break;
            }
        }
        if (flag == false) {
        //	System.out.println("The search keyword : "+searchkeyword+" wasn't found, please wait until pagination finds the keyword for you in the upcoming pages");
        	System.out.println("The search keyword: "+searchkeyword+" is not present in the page : "+pnum+" As per the search results : "+pinfo);
        driver.findElement(By.xpath("//div[@role='navigation']//descendant::span[text()='Next']")).click();
        elogger.log(Status.PASS,"Element: "+searchkeyword+" could not found in page: "+pnum+ ","+" hence clicked on next");
        Thread.sleep(1000);
        matching_results();
        extent.flush();
        }
        elogger.log(Status.INFO,"Closing the browser");
        driver.quit();
        elogger.log(Status.PASS,"Closed the browser");

}

}

