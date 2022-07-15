package empapitestCases;


import java.util.List;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import empapiBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TestCase3 extends TestBase {
	String propFile="src/test/java/resource/base.properties";

	String uri=Utils.DataProvider.readTestData(propFile,"uRl");

//	String empId=RestUtils.empId();
//	String empTitle=RestUtils.empTitle();
//	String dueDate=RestUtils.dueDate();
//	String completed=RestUtils.completed();
//	
	
	@BeforeTest
public void Details() throws InterruptedException
{
	System.out.println(uri);
	RestAssured.baseURI=uri;
	httpRequest=RestAssured.given();

	response=httpRequest.request(Method.GET,"/api/v1/Activities");
	JsonPath jsonPathEvaluator=response.jsonPath();
	
	List<String> id=jsonPathEvaluator.get("id");

	
	response=httpRequest.request(Method.DELETE,""+id);
	
	
	Thread.sleep(3000);
}
	@Test
	public void getRequest()
	{ 
		
		
		int statusCode=response.getStatusCode();
		System.out.println("status code is"+statusCode);
		Assert.assertEquals(statusCode,200);
		
		
//		Assert.assertEquals(responseBody.contains(empId),true);
//		Assert.assertEquals(responseBody.contains(empTitle),true);
//		Assert.assertEquals(responseBody.contains(dueDate),true);
//		Assert.assertEquals(responseBody.contains(completed),true);
	}
	
	

}
