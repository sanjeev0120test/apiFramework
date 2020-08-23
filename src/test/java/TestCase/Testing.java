package TestCase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testing {
	RequestSpecification httpRequest = RestAssured.given();
	
	@BeforeTest
	public static void setup() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
	}
	
	@Test (description = "Register user")
	public void registerTest() {
		JSONObject requestJson = new JSONObject();
		requestJson.put("FirstName", "Sanjeev");
		requestJson.put("LastName", "Mishra");
		requestJson.put("UserName", "sanjeevtest");
		requestJson.put("Password", "ok");
		requestJson.put("Email", "sanjeev0120@gmail.com");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestJson.toJSONString());
		Response response = httpRequest.post("/register");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("the status code is: " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
	}

}
