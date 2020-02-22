
package com.Finzy.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExecuteLeadTestSingleTestCaseRunner{

	static Keywords keyword;
	static ExtentReports extent;
	static ExtentTest elogger;
	static Logger logger;
	static String runmode;
	
	public static void main(String[] args) throws Exception{
		int length;
		String project_directory;
		String date_time;
		ExtentHtmlReporter reporter;
		FileInputStream file;
		Row rowIterator;
		Iterator row;
		Iterator cellItr;
		Appender fh = null;
		keyword = new Keywords();
		ArrayList data = new ArrayList();
		Path currentRelativePath = Paths.get("");
		project_directory = currentRelativePath.toAbsolutePath().toString();
		date_time = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(date_time);
		reporter = new ExtentHtmlReporter(project_directory + "/Reports/ReportsOfExecuteLeadTestSingleTestCaseRunner/" + date_time + "_automation_report.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		elogger = extent.createTest(date_time + "_automation_report");	
		logger = Logger.getLogger("");
		try {	
			fh = new FileAppender(new SimpleLayout(), project_directory+"/logs/LogsOfExecuteLeadTestSingleTestCaseRunner/"+date_time+"_automation_logs.log");
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());
		} catch (SecurityException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		file = new FileInputStream(project_directory +"/TestCases/TestCases.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		//normal eclipse execution
		XSSFSheet sheet = workbook.getSheet("Finzy");
		//argument execution via terminal
		//XSSFSheet sheet = workbook.getSheet(args[0]);
		row = sheet.iterator();
		while(row.hasNext()) {
			rowIterator = (Row)row.next(); 
			cellItr = rowIterator.cellIterator();
			while(cellItr.hasNext()) {
				Cell cell=(Cell)cellItr.next();
				switch(cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: 
						data.add(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						data.add(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC: 
						data.add(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA: 
						data.add(cell.getStringCellValue());
						break;
				}

			}
		}

		for(int i=0;i<data.size();i++) {
			extent.flush();
			String[] data_array = new String[4];
			try {
				if(data.get(i).equals("openbrowser")) {
					runmode = (String)data.get(i+3);
					keyword.openbrowser();
					if(runmode.equals("yes")) {
						elogger.log(Status.PASS,"Opening the browser");
						keyword.openbrowser();
						data_array = show_values(data, i);
						elogger.log(Status.PASS,"Opened the browser");
						logger.info("Opened the browser");
						//logger.info(data_array[0]+'\n'+data_array[1]+'\n'+data_array[2]+'\n'+data_array[3]);
					} 
				}
				if(data.get(i).equals("navigate")) 
				{
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"navigation to :"+data_array[2]);
						keyword.navigate(data_array[2]);
						elogger.log(Status.PASS,"navigated to :"+data_array[2]);
						logger.info("navigated to :"+data_array[2]);
					}
				}
				if(data.get(i).equals("quit")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"close browser operation");
						keyword.quit();
						elogger.log(Status.PASS,"Browser closed");
						logger.info(" Browser closed ");
					}
				}
				if(data.get(i).equals("refresh")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Refresh tab operation");	
						keyword.refresh();
						elogger.log(Status.PASS,"Refreshed the tab");
						logger.info("Refreshed the tab");
					}
				}
				if(data.get(i).equals("navigateback")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Navigate back in browser");
						keyword.navigateback();
						elogger.log(Status.PASS,"Navigated back");
						logger.info("Navigated back");
					}
				}
				if(data.get(i).equals("newTab")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"New tab creation");	
						keyword.newTab();
						elogger.log(Status.PASS,"New tab created/opened ");
						logger.info("New tab created/opened");
					}
				}
				if(data.get(i).equals("newTabOne")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"New tab one creation");
						keyword.newTabOne();
						elogger.log(Status.PASS,"New tab one created/opened  ");
						logger.info("New tab one created/opened");
					}
				}
				if(data.get(i).equals("newTabTwo")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"New tab two creation");	
						keyword.newTabTwo();
						elogger.log(Status.PASS,"New tab two created/opened ");
						logger.info("New tab two created/opened");
					}
				}
				if(data.get(i).equals("newTabZero")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"New tab zero creation");
						keyword.newTabZero();
						elogger.log(Status.PASS,"New tab zero created/opened ");
						logger.info("New tab zero created/opened");
					}
				}
				if(data.get(i).equals("closecurrenttab")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Close current tab");	
						keyword.closecurrenttab();
						elogger.log(Status.PASS,"Closed current tab ");
						logger.info("Closed current tab ");
					}
				}
				if(data.get(i).equals("switchtotabzero")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Switch to zeroth tab");	
						keyword.switchtotabzero();
						elogger.log(Status.PASS,"Switched to zeroth tab ");
						logger.info("Switched to zeroth tab");
					}
				}
				if(data.get(i).equals("switchtotabone")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Switch to tab one");	
						keyword.switchtotabone();
						elogger.log(Status.PASS,"Switched to tab one ");
						logger.info("Switched to tab one ");
					}
				}
				if(data.get(i).equals("switchtotabtwo")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Switch to tab two");	
						keyword.switchtotabtwo();
						elogger.log(Status.PASS,"Switched to tab two ");
						logger.info("Switched to tab two ");
					}
				}
				if(data.get(i).equals("switchtotabthree")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Switch to tab three");	
						keyword.switchtotabthree();
						elogger.log(Status.PASS,"Switched to tab three ");
						logger.info("Switched to tab three ");
					}
				}
				if(data.get(i).equals("scrolltolastelementoftable")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Scroll to last element of the table");	
						keyword.scrolltolastelementoftable(data_array[2]);
						elogger.log(Status.PASS,"Scrolled to last element of the table");
						logger.info("Scrolled to last element of the table");
					}
				}
				if(data.get(i).equals("scrollToNextTab")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Scroll to next tab");			
						keyword.scrollToNextTab();
						elogger.log(Status.PASS,"Scrolled to next tab");
						logger.info("Scrolled to next tab");
					}
				}
				if(data.get(i).equals("robotarrowdown")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Perform Action:press down arrow");	
						keyword.robotarrowdown();
						elogger.log(Status.PASS,"Action:Down arrow performed successfully");
						logger.info("Action:Down arrow performed successfully");
					}
				}
				if(data.get(i).equals("input")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Entering input: "+data_array[1]);
						keyword.input(data_array[1],data_array[2]);
						elogger.log(Status.PASS,"Entered the input: "+data_array[1]);
						logger.info("Entered the input: "+data_array[1]);
					}
				}
				if(data.get(i).equals("inputint")) {
					String Keyword = (String)data.get(i);
					int Data= (int)Math.round((Double) data.get(i + 1));
					String converted_data = String.valueOf(Data);
					String ObjectName = (String)data.get(i+2);
					String Runmode = (String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Entering integer input: "+Data);
						keyword.inputint(converted_data,ObjectName);
						elogger.log(Status.PASS,"Entered the input: "+Data);
						logger.info("Entered the input: "+Data);
						
					}
				}
				if(data.get(i).equals("FloatValue")) {
					String Keyword = (String)data.get(i);
					Double Data= (Double) data.get(i + 1);
					String converted_data = String.valueOf(Data);
					String ObjectName = (String)data.get(i+2);
					String Runmode = (String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Entering float input: "+Data);
						keyword.FloatValue(converted_data,ObjectName);
						elogger.log(Status.PASS,"Entered the input: "+Data);
						logger.info("Entered the input: "+Data);
					}
				}
				if(data.get(i).equals("inputintwithchar")) {
					String Keyword = (String)data.get(i);
					String Data= (String) data.get(i + 1);
					String converted_data = String.valueOf(Data);
					CharSequence converted_new_data = converted_data;
					String ObjectName = (String)data.get(i+2);
					String Runmode = (String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(converted_data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Entering integer and character input: "+Data);
						keyword.inputintwithchar(converted_new_data,ObjectName);
						elogger.log(Status.PASS,"Entered the input: "+Data);
						logger.info("Entered the input: "+Data);
					}

				}
				if(data.get(i).equals("click")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Click on element with ObjectName: "+data_array[2]);
						keyword.click(data_array[2]);
						elogger.log(Status.PASS,"Clicked on element with ObjectName: "+data_array[2]);
						logger.info("Clicked on element with ObjectName: "+data_array[2]);
						

					}

				}
				if(data.get(i).equals("rightclick")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Perform right click action on element with ObjectName: "+data_array[2]);
						keyword.rightclick(data_array[2]);
						elogger.log(Status.PASS,"Perfrormed right click action on element with ObjectName: "+data_array[2]);
						logger.info("Perfrormed right click action on element with ObjectName: "+data_array[2]);
					}

				}
				if(data.get(i).equals("waitandgoback")) {
					String Keyword=(String)data.get(i);
					String Data= String.valueOf(data.get(i + 1));
					By ObjectName=(By)data.get(i+2);
					String Runmode=(String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Wait for the element with ObjectName: "+ObjectName);
						keyword.waitandgoback(ObjectName);
						elogger.log(Status.PASS,"Waited for the element with ObjectName: "+ObjectName);
						logger.info("Waited for the element with ObjectName: "+ObjectName);
					}

				}
				if(data.get(i).equals("robotenter")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Hit Enter key: "+data_array[2]);
						keyword.robotenter(data_array[2],data_array[1]);
						elogger.log(Status.PASS,"Hit Enter key: "+data_array[2]);
						logger.info("Hit Enter key: "+data_array[2]);
					}

				}
				if(data.get(i).equals("rightclickopeninnewtab")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Hit enter key after right clicking on element with ObjectName: "+data_array[2]);
						keyword.rightclickopeninnewtab(data_array[2]);
						elogger.log(Status.PASS,"Hit enter key after right clicking on element with ObjectName: "+data_array[2]);
						logger.info("Hit enter key after right clicking on element with ObjectName: "+data_array[2]);
					}

				}
				if(data.get(i).equals("rightclickopeninnewtabversionone")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Hit enter key after right clicking on element with ObjectName: "+data_array[2]);
						keyword.rightclickopeninnewtabversionone(data_array[2]);
						elogger.log(Status.PASS,"Hit enter key after right clicking on element with ObjectName: "+data_array[2]);
						logger.info("Hit enter key after right clicking on element with ObjectName: "+data_array[2]);
					}

				}
				if(data.get(i).equals("sleep")) {
					String Keyword=(String)data.get(i);
					int Data = (int)Math.round((Double) data.get(i + 1));
					String ObjectName=(String)data.get(i+2);
					String Runmode=(String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Sleep for: "+Data+" Seconds"); 
						keyword.sleep(ObjectName,Data);
						elogger.log(Status.PASS,"Sleep for: "+Data+" Seconds");
						logger.info("Sleep for: "+Data+" Seconds");
					}

				}
				if(data.get(i).equals("selectbyindex")) {
					String Keyword=(String)data.get(i);
					int Data= (int)Math.round((Double) data.get(i + 1));
					String ObjectName=(String)data.get(i+2);
					String Runmode=(String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Select option by index: "+Data+" on the select box with ObjectName: "+ObjectName);
						keyword.selectbyindex(ObjectName,Data);
						elogger.log(Status.PASS,"Select option by index: "+Data+" on the select box with ObjectName: "+ObjectName);
						logger.info("Selected option with index: "+Data+" in the select box with ObjectName: "+ObjectName);
					}

				}
				if(data.get(i).equals("selectbyvisibletextstring")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Select option by string: "+data_array[1]+" in the select box with ObjectName: "+data_array[2]);
						keyword.selectbyvisibletextstring(data_array[2],data_array[1]);
						elogger.log(Status.PASS,"Selected option with string: "+data_array[1]+" in the select box with ObjectName: "+data_array[2]);
						logger.info("Selected option with string: "+data_array[1]+" in the select box with ObjectName: "+data_array[2]);
						
					}

				}
				if(data.get(i).equals("selectbyvisibletextint")) {
					String Keyword=(String)data.get(i);
					int Data= (int)Math.round((Double) data.get(i + 1));
					String converted_data = String.valueOf(Data);
					String ObjectName=(String)data.get(i+2);
					String Runmode=(String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Select option by integer value: "+Data+" in the select box with ObjectName: "+ObjectName);
						keyword.selectbyvisibletextint(ObjectName,converted_data);
						elogger.log(Status.PASS,"Selected option with integer value: "+Data+" in the select box with ObjectName: "+ObjectName);
						logger.info("Selected option with integer value: "+Data+" in the select box with ObjectName: "+ObjectName);
					}

				}
				if(data.get(i).equals("selectbyvalue")) {
					String Keyword=(String)data.get(i);
					int a= (int)Math.round((Double) data.get(i + 1));
					String Data= String.valueOf(a);
					String ObjectName=(String)data.get(i+2);
					String Runmode=(String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Select option by value: "+Data+" in the select box with ObjectName: "+ObjectName);
						keyword.selectbyvalue(ObjectName,Data);
						elogger.log(Status.PASS,"Selected option with value: "+Data+" in the select box with ObjectName: "+ObjectName);
						logger.info("Selected option with value: "+Data+" in the select box with ObjectName: "+ObjectName);
					}

				}
				if(data.get(i).equals("robot")) {
					Path currentRelativePathRobot = Paths.get("");
					String r = currentRelativePathRobot.toAbsolutePath().toString();
					
					String Keyword=(String)data.get(i);
					String Data = (String)data.get(i+1);																								
					String ObjectName=(String)data.get(i+2);
					String Runmode=(String)data.get(i+3);
					System.out.println(Keyword);
					System.out.println(Data);
					System.out.println(ObjectName);
					System.out.println(Runmode);
					if(Runmode.equals("yes")) {
						elogger.log(Status.INFO,"Paste the file: "+Data+" in the choose file window and hit Enter");
						keyword.robot(ObjectName,r+Data);
						elogger.log(Status.PASS,"Pasted the file: "+Data+" in the choose file window and hit Enter");
						logger.info("Pasted the file: "+Data+" in the choose file window and hit Enter");
					}

				}
				if(data.get(i).equals("duplicateTab")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Create a duplicate tab");
						keyword.duplicateTab();
						elogger.log(Status.PASS,"Created a duplicate tab");
						logger.info("Created a duplicate tab");
					}

				}
				if(data.get(i).equals("newwTab")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Create a new tab");
						keyword.newwTab(data_array[2],data_array[1]);
						elogger.log(Status.PASS,"Created a new tab");
						logger.info("Created a new tab");
					}

				}
				if(data.get(i).equals("accept")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Accept the alert");	
						keyword.accept();
						elogger.log(Status.PASS,"Accepted the alert");
						logger.info("Accepted the alert");
					}

				}
				if(data.get(i).equals("scrollDown")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Scroll down");		
						keyword.scrollDown();
						elogger.log(Status.PASS,"Scrolled down");
						logger.info("Scrolled down");
					}

				}
				if(data.get(i).equals("scrollUp")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						elogger.log(Status.INFO,"Scroll up");		
						keyword.scrollUp();
						elogger.log(Status.PASS,"Scrolled up");
						logger.info("Scrolled up");
					}

				}
				if(data.get(i).equals("clearTextAndInputData")) {
					runmode = (String)data.get(i+3);
					if(runmode.equals("yes")) {
						data_array = show_values(data, i);
						elogger.log(Status.INFO,"Clear the text in the element with ObjectName: "+data_array[2]+" and enter input: "+data_array[1]);
						keyword.clearTextAndInputData(data_array[1],data_array[2]);
						elogger.log(Status.PASS,"Cleared the text in the element with ObjectName: "+data_array[2]+" and entered input: "+data_array[1]);
						logger.info("Cleared the text in the element with ObjectName: "+data_array[2]+" and entered input: "+data_array[1]);
						
					}

				}
				
//				if(data.get(i).equals("googlesearchbox2")) {
//				runmode = (String)data.get(i+3);
//				if(runmode.equals("yes")) {
//					data_array = show_values(data, i);
//					elogger.log(Status.INFO,"Select option by string: "+data_array[1]+" in the select box with ObjectName: "+data_array[2]);
//					keyword.googlesearchbox2(data_array[2],data_array[1]);
//					elogger.log(Status.PASS,"Selected option with string: "+data_array[1]+" in the select box with ObjectName: "+data_array[2]);
//					logger.info("Selected option with string: "+data_array[1]+" in the select box with ObjectName: "+data_array[2]);
//					
//				}
//			}
//			
			if(data.get(i).equals("googlesearchbox2")) {
				runmode = (String)data.get(i+3);
				if(runmode.equals("yes")) {
					data_array = show_values(data, i);
					elogger.log(Status.INFO,"Hit Enter key: "+data_array[2]);
					keyword.googlesearchbox2(data_array[1],data_array[2]);
					elogger.log(Status.PASS,"Hit Enter key: "+data_array[2]);
					logger.info("Hit Enter key: "+data_array[2]);
				}

			}
			if(data.get(i).equals("searchKeyword")) {
				runmode = (String)data.get(i+3);
				if(runmode.equals("yes")) {
					data_array = show_values(data, i);
					elogger.log(Status.INFO,"Hit Enter key: "+data_array[2]);
					keyword.searchKeyword(data_array[1],data_array[2]);
					elogger.log(Status.PASS,"Hit Enter key: "+data_array[2]);
					logger.info("Hit Enter key: "+data_array[2]);
				}

			}
				
				
				
				
				
				
				
				
				
				if(data.get(i).equals("LoopToCreateCategories")) {
					int loop_count = (int)Math.round((Double) data.get(i + 1));
					for (int k=1; k<=loop_count; k++) {
						String category_name = "General Category Elective"+k;
						String category_code = "GCE"+k;
						String Runmode = (String)data.get(i+3);
						if(Runmode.equals("yes")) {
							keyword.update_input_values(category_name,category_code);
						}
					}

				}
				if(data.get(i).equals("LoopToDeleteCategories")) {
					int loop_count = (int)Math.round((Double) data.get(i + 1));
					for (int k=1; k<=loop_count; k++) {
						String Runmode = (String)data.get(i+3);
						if(Runmode.equals("yes")) {
							keyword.LoopToDeleteCategories();
						}
					}
				}
			}  catch (Exception e) {
				elogger.log(Status.FAIL, e.getMessage());
			}
		}
	}
	
	public static String[] show_values(ArrayList data, int i) {
		String[] data_list = new String [4];
		for (int j = 0; j < 4; j++) {
			data_list[j] = (String)data.get(i);
			i++;
			System.out.println(data_list[j]);
		}
		return data_list;
	}
}
