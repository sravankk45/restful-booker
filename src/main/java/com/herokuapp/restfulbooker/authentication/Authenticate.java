package com.herokuapp.restfulbooker.authentication;

import static io.restassured.RestAssured.given;

import com.herokuapp.restfulbooker.base.DriverClass;
import com.herokuapp.restfulbooker.pojo.UserPojo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Authenticate extends DriverClass{
	
	private JsonPath jsonResponse;
	private UserPojo userPojo;
	
	public String getToken() {
		
		userPojo = new UserPojo();
		userPojo.setUsername(getUserName());
		userPojo.setPassword(getPassword());
		RestAssured.baseURI = getBaseUri();
		Response response = given()
				  .header("content-type","application/json")
	              .body(userPojo)
				  .when()
				  .post("/auth").then()
				  .extract().response();
		
		jsonResponse = new JsonPath(response.asString());
		String token = jsonResponse.get("token");
		return token;
		
	}

}
