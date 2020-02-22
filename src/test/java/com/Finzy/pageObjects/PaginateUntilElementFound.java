package com.Finzy.pageObjects;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PaginateUntilElementFound {
static WebDriver driver;
//Enter the final search keyword here
static String searchkeyword="finzy";

public static void main(String[] args) throws Exception {

   
// TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "/home/foradian/chromedriver");
driver = new ChromeDriver();

driver.get("http://google.com");

//driver.manage().window().maximize();
//Enter search text
driver.findElement(By.xpath("//input[@name='q']")).sendKeys("peer to peer lending bangalore");

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
            if(listitem.contains("peer to peer lending bangalore")) {
                list.get(i).click();
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
            
            //----------------------------------------
//            WebElement pageid=driver.findElement(By.xpath("//td[@class='cur']"));
//            String pnum=pageid.getText();
//            //for results at top
//            WebElement pageinfo=driver.findElement(By.xpath("//div[@id='mBMHK']"));
//            String pinfo=pageinfo.getText();
            
            
            //----------------------------------------

            if(listwitem.contains(searchkeyword)){
            flag = true;
         // String pageid =  String.valueOf(driver.findElement(By.xpath("//div[@id='mBMHK']")));
           // String pageid =  String.valueOf(driver.findElement(By.xpath("//td[@class='cur']")));
            //for pagination
            //    ---------------------
//          WebElement pageid=driver.findElement(By.xpath("//td[@class='cur']"));
//          String pnum=pageid.getText();
//          //for results at top
//          WebElement pageinfo=driver.findElement(By.xpath("//div[@id='mBMHK']"));
//          String pinfo=pageinfo.getText();
          
          //  ---------------------------
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

}

