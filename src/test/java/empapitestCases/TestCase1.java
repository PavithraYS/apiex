package empapitestCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import empapiBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;



public class TestCase1 extends TestBase{
	String propFile="src/test/java/resource/base.properties";

	String uri=Utils.DataProvider.readTestData(propFile,"url");
	String jsonPath="src/test/java/resource/TestCase1.json";
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	@BeforeSuite
	public void setExtent()
	{
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		htmlReporter.config().setDocumentTitle("API report");
		htmlReporter.config().setReportName("Functional report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname","LocalHost");
		extent.setSystemInfo("OS","Windows10");
		extent.setSystemInfo("Tester Name","Pavithra");
		extent.setSystemInfo("Browser","Chrome");
	}
	@BeforeTest
public void Details() throws InterruptedException
{
	System.out.println(uri);
	RestAssured.baseURI=uri;
	httpRequest=RestAssured.given();
	response=httpRequest.request(Method.GET,"get");
	
	Thread.sleep(3);
}

	@Test
	public void getRequest() throws IOException
	{ 
		test=extent.createTest("TestCase1");
		int statusCode=response.getStatusCode();
		System.out.println("status code is"+statusCode);
		Assert.assertEquals(statusCode,200);
		
		byte[] input=Files.readAllBytes(Paths.get(jsonPath));
		String inputValue=new String(input);
	
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(inputValue.contains(responseBody),true);
		
		
	}
	@AfterMethod
	public void getRequestResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL,"TEST CASE FAILED IS"+result.getName());//to add name in extent report
			test.log(Status.FAIL,"TEST CASE FAILED IS"+result.getThrowable());//to add error/exception in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP,"TEST CASE SKIPPED IS"+result.getName());
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS,"TEST CASE PASSED IS"+result.getName());
			
		}
		
	}
	@AfterSuite
	public void tearDown()
	{
		extent.flush();
	}
	
}

