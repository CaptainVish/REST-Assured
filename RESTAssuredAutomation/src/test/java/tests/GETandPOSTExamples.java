package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GETandPOSTExamples {
	@Test
	public void testGet(){
		baseURI = "https://reqres.in/api";
		given().get("/users?page=2").then().statusCode(200).
		body("data[4].first_name",equalTo("George")).
		body("data.first_name", hasItems("George","Rachel"));
	}
	
	@Test
	public void learningHashMap() {
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("\"name\"","Vishwa");
		map.put("job", "Gamer");
		
		System.out.println(map);
		
	}
	
	
	@Test
	public void testPOST() {
		Map<String, Object>map = new HashMap<String, Object>();
		JSONObject request = new JSONObject(map);
		
		request.put("name","Vishwa");
		request.put("job", "Gamer");
		
		System.out.println(request.toJSONString());
		baseURI = "https://reqres.in/api";
		
		given().header("Content-Type","application/json").
		contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).
		when().post("/users").then().statusCode(201).log().all();
		
	}

	

}
