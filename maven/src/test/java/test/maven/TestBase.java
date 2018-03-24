package test.maven;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	static
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/report/test_" + formater.format(calendar.getTime()) +".html",false);	
	}
	
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, result.getName()+" test is pass");
		}else if(result.getStatus()==ITestResult.SKIP)
		{
			logger.log(LogStatus.SKIP, result.getName()+" test is skipped and skip reason is" + result.getThrowable());
		}else if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(LogStatus.ERROR, result.getName()+" test is failed" + result.getThrowable());
		}else if(result.getStatus()==ITestResult.STARTED)
		{
			logger.log(LogStatus.INFO, result.getName()+" test is started");
		}
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult result)
	{
		logger = extent.startTest(result.getName());
		logger.log(LogStatus.INFO, result.getName() + " Test Statetd");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		getResult(result);
	}
	
	@AfterClass(alwaysRun = true)
	public void endTest()
	{
		extent.endTest(logger);
		extent.flush();
	}
}