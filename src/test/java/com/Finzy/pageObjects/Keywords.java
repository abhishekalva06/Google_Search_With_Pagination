package com.Finzy.pageObjects;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keywords {
	//static ChromeDriver driver;
	static WebDriver driver;
	static Properties prop;
	static FileInputStream file;
	public void openbrowser() throws Exception {
		Path currentRelativePath = Paths.get("");
		String project_directory = currentRelativePath.toAbsolutePath().toString();
		System.setProperty("webdriver.chrome.driver", project_directory+"/Drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		file=new FileInputStream(project_directory+"/Repositories/Objectrepository.properties");
		prop=new Properties();
		prop.load(file);
	}


	public String getChromePath()
	{
		String chromepath= prop.getProperty("chromepath");
		return chromepath;
	}

	public void navigate(String ObjectName) {
		driver.manage().window().maximize();
		driver.get(prop.getProperty(ObjectName));
	}
	public void refresh() throws Exception {
		driver.navigate().refresh();
	}
	public void navigateback() throws Exception {
		driver.navigate().back();
	}
	public void quit() throws Exception {
		driver.quit();
	}
	public void input(String Data, String ObjectName)  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).clear();
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).sendKeys(Data);
	}
	public void inputint(String Data, String ObjectName)  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).clear();
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).sendKeys(Data);
	}
	public void FloatValue(String data, String ObjectName)  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).clear();
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).sendKeys(data);
	}
	public void inputintwithchar(CharSequence Data, String ObjectName)  {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).clear();
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).sendKeys(Data);
	}
	public void click(String ObjectName) throws Exception {
		driver.manage().timeouts().implicitlyWait(300,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).click();
	}
	public void rightclick(String ObjectName) throws Exception {
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath(ObjectName));
		actions.contextClick(elementLocator).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();
	}
	public void select(String ObjectName, int Data) throws Exception {
		Select select=new Select(driver.findElement(By.xpath(prop.getProperty(ObjectName))));
		select.selectByIndex(Data);		
	}
	public void sleep(String ObjectName, int Data) throws Exception {
		Thread.sleep(Data);
	}
	public void waitandgoback(By ObjectName) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectName));
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void selectbyindex(String ObjectName, int Data) throws Exception {
		Select selectDobMonth=new Select(driver.findElement(By.xpath(prop.getProperty(ObjectName))));
		selectDobMonth.selectByIndex(Data);
	}
	public void selectbyvisibletextstring(String ObjectName, String Data) throws Exception {
		Select selectDobMonth=new Select(driver.findElement(By.xpath(prop.getProperty(ObjectName))));
		selectDobMonth.selectByVisibleText(Data);
	}
	public void selectbyvisibletextint(String ObjectName, String Data) throws Exception {
		Select selectDobMonth=new Select(driver.findElement(By.xpath(prop.getProperty(ObjectName))));
		selectDobMonth.selectByVisibleText(Data);
	}
	public void robotenter(String ObjectName, String Data) throws Exception {
		StringSelection selection=new StringSelection(Data);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Robot robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}
	public void selectbyvalue(String ObjectName, String Data) throws Exception {
		Select selectDobMonth=new Select(driver.findElement(By.xpath(prop.getProperty(ObjectName))));
		selectDobMonth.selectByValue(Data);		
	}
	public void robotclick(String ObjectName) throws Exception {
		driver.findElement(By.xpath(ObjectName)).click();
		Thread.sleep(1000);		
	}
	public void accept() throws Exception {
		driver.switchTo().alert().accept();
	}
	public void robot(String ObjectName, String Data) throws Exception {
		StringSelection selection=new StringSelection(Data);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
	}
	public void scrollDown() throws Exception {
		((JavascriptExecutor)driver).executeScript("scroll(0,400)");
	}
	public void scrolltolastelementoftable(String ObjectName) throws Exception {
		WebElement element = driver.findElement(By.xpath(prop.getProperty(ObjectName)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}
	public void scrollUp() throws Exception {
		((JavascriptExecutor)driver).executeScript("scroll(0,-400)");
	}
	public void duplicateTab() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_D);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_D);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Actions action= new Actions(driver);
	}
	public void robotarrowdown() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	public void newwTab(String ObjectName, String Data) throws Exception {
		StringSelection selection=new StringSelection(Data);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void rightclickopeninnewtab(String ObjectName) throws Exception {
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath(prop.getProperty(ObjectName)));
		actions.contextClick(elementLocator).perform();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void rightclickopeninnewtabversionone(String ObjectName) throws Exception {
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath(prop.getProperty(ObjectName)));
		actions.contextClick(elementLocator).perform();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void closecurrenttab() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_W);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_W);
	}
	public void scrollToNextTab() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	public void switchtotabzero() throws Exception 
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	public void switchtotabone() throws Exception 
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	public void switchtotabtwo() throws Exception 
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
	}
	public void switchtotabthree() throws Exception 
	{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(3));
	}
	public void newTab() 
	{
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	public void newTabZero() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	public void newTabOne() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	public void newTabTwo() throws Exception 
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
	}
	public void clearTextAndInputData(String Data, String ObjectName) throws Exception {
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).clear();
		driver.findElement(By.xpath(prop.getProperty(ObjectName))).sendKeys(Data);
	}
	public void googlesearchbox2(String Data, String ObjectName) throws Exception {

		Thread.sleep(1000);

		System.out.println("\n\n\n\n\n\n\n\n"+prop.getProperty(ObjectName));
		List <WebElement> list = driver.findElements(By.xpath(prop.getProperty(ObjectName)));

		System.out.println(list.size());
		System.out.println("\n\n\n\n\n\n\n\n"+list.get(0));
		for(int i=0; i<list.size();i++) {
			String listitem= list.get(i).getText();

			System.out.println(listitem.toString());
		}

		for(int i=0; i<list.size();i++)
		{
			String listitem= list.get(i).getText();
			//System.out.println(listitem.toString());
			System.out.println(listitem);
			if(listitem.contains(Data)) {
				list.get(i).click();
				break;
			}

		}
		Thread.sleep(1000);

		//}



}

public void searchKeyword(String Data, String ObjectName) throws Exception {

	matching_results(Data,ObjectName);



}




public static void matching_results(String searchkeyword, String ObjectName) throws Exception {
	//for g results
	//List <WebElement> listw = driver.findElements(By.xpath("//div[@class='g']"));
	//blue
	//List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::h3"));
	//grey link
	//  List <WebElement> listw = driver.findElements(By.xpath("//h2['Web results']//parent::div[@class='bkWMgd']//descendant::div[@class='rc']//descendant::cite"));
	//List <WebElement> listw = driver.findElements(By.xpath(prop.getProperty(ObjectName)));
	List <WebElement> listw = driver.findElements(By.xpath(prop.getProperty(ObjectName)));


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
			// String pageid =  String.valueOf(driver.findElement(By.xpath("//div[@id='mBMHK']")));
			// String pageid =  String.valueOf(driver.findElement(By.xpath("//td[@class='cur']")));
			//for pagination
			//		          WebElement pageid=driver.findElement(By.xpath("//td[@class='cur']"));
			//		          String pnum=pageid.getText();
			//		           pnum=pageid.getText();
			//
			//		          for results at top
			//		          WebElement pageinfo=driver.findElement(By.xpath("//div[@id='mBMHK']"));
			//		           pinfo=pageinfo.getText();

			// String pinfo=pageinfo.getText();
			System.out.println("->As per the page results at top, for search keyword: "+searchkeyword+ " this is the page info: "+pinfo+"& This is the page number: "+pnum);


			// String abc=pageid.getText();

			// System.out.println(pageid.toString());
			//div[@id='mBMHK']
			//  listw.get(i).click();
			break;
		}
	}
	if (flag == false) {
		//System.out.println("The search keyword: "+searchkeyword+" is not present as per the results of this page: ");
		//		        	System.out.println("The search keyword :  wasn't found, please wait until pagination finds the keyword for you in the upcoming pages");

		//	System.out.println("The search keyword : "+searchkeyword+" wasn't found, please wait until pagination finds the keyword for you in the upcoming pages");
		System.out.println("The search keyword: "+searchkeyword+" is not present in the page : "+pnum+" As per the search results : "+pinfo+" Please wait so that we check the info in the next page");
		driver.findElement(By.xpath("//div[@role='navigation']//descendant::span[text()='Next']")).click();
		Thread.sleep(1000);
		// matching_results(searchkeyword);
		matching_results(searchkeyword,ObjectName);

	}
}




public void update_input_values(String cateogory_name, String category_code) throws Exception
{
	driver.findElement(By.xpath("//input[@id='course_category_name']")).sendKeys(cateogory_name);
	driver.findElement(By.xpath("//input[@id='course_category_code']")).sendKeys(category_code);
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[text()='New']")).click();
}
public void LoopToDeleteCategories() throws Exception {

	driver.findElement(By.xpath("(//a[text()='Delete'])[1]")).click();
	driver.switchTo().alert().accept();
}
}
