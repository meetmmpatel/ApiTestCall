package com.example.demo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class CallAPITest {

	/*
	 * given usa/city api is up an running 
	 * when application call this api with right url 
	 * then verify that api is returnting HTTP 200 status code.
	 */
	@Test
	public void testResponseCode() {

		Response resp = when().get("http://services.groupkt.com/state/get/USA/all");
		int statuscode = resp.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	/*
	 * given usa/city api is up an running 
	 * when application call this api with right url 
	 * and provide header as "Content-Type" and value as "application/json" 
	 * then verify that api is returnting HTTP 200 status code.
	 */

	@Test
	public void testHeaderParameter() {
		Response resp = given().param("Content-Type", "application/json").when()
				.get("http://services.groupkt.com/state/get/USA/all");

		int statuscode = resp.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	/*
	 * given usa/city api is up an running 
	 * when application call this api with right url 
	 * and provide path variable as State  = MD 
	 * then verify that api is returning HTTP 200 status code
	 * And print the name value as Maryland
	 * 
	 */
	
	@Test
	public void testTheStateAbbr() throws JSONException {
		Response resp = given().when().get("http://services.groupkt.com/state/get/USA/MD");

		String actual = "{id:20, name:\"Maryland\"}";
		JSONAssert.assertEquals("{id:20, name:\"Maryland\"}", actual, JSONCompareMode.LENIENT);
		
	}

	
	/*
	 * given usa/city api is up an running 
	 * when application call this api with right url 
	 * and provide path variable as State  = md (in lower case)
	 * then verify that api is returning HTTP 200 status code
	 * And print the message No matching state found 
	 * 
	 */
	
	@Test
	public void testInvalidUrl() throws JSONException {
		Response resp = given().when().get("http://services.groupkt.com/state/get/USA/md");

		System.out.println(resp.asString());
		
	}
	
	
}
