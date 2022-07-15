package empapitestCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import empapiBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestCase2 extends TestBase {
	String propFile="src/test/java/resource/base.properties";
	String jsonPath="src/test/java/resource/TestCase2.json";

	String uri=Utils.DataProvider.readTestData(propFile,"uRl");

@BeforeTest
public void Details() throws InterruptedException, IOException
{
	System.out.println(uri);
	RestAssured.baseURI=uri;
	httpRequest=RestAssured.given();
//	JSONObject requestParams=new JSONObject();
//	requestParams.put(  "id",224);
//	requestParams.put("title","string");
//	requestParams.put(  "dueDate","2022-06-28T22:10:18.597Z");
//	requestParams.put(requestParams, requestParams);
//	httpRequest.header("Content-Type","application/json");
//	httpRequest.body(requestParams.toJSONString(requestParams));
	
	byte[] input=Files.readAllBytes(Paths.get(jsonPath));
	String inputValue=new String(input);
	httpRequest.body(inputValue);

	response=httpRequest.request(Method.POST,"https://fakerestapi.azurewebsites.net/api/v1/Activities");
	
	
	Thread.sleep(3);
}
@Test
public void getRequest() throws IOException
{ 
	
	String responseBody= response.getBody().asString();
	System.out.println(responseBody);
	int statusCode=response.getStatusCode();
	System.out.println("status code is"+statusCode);
	Assert.assertEquals(statusCode,200);
	
}
}