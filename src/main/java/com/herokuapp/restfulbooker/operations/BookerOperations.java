package com.herokuapp.restfulbooker.operations;

import static io.restassured.RestAssured.given;
import com.herokuapp.restfulbooker.pojo.BookingPojo;
import io.restassured.response.Response;

public class BookerOperations {
	
	public Response createBooking(BookingPojo bookingPojo) {
		
		return given()
				  .header("content-type","application/json")
				  .header("accept","application/json")
				  .body(bookingPojo)
				  .when()
				  .post("/booking").then()
				  .extract().response();
	}
	
	public Response getBooking(int bookingid) {
		
		return given()
				  .header("content-type","application/json")
				  .header("accept","application/json")
				  .pathParam("id",bookingid)
				  .when()
				  .get("/booking/{id}").then()
				  .extract().response();
	}
	
	public Response updateBooking(int bookingid, BookingPojo bookingPojo, String token) {
		
		return given()
				  .header("content-type","application/json")
				  .header("accept","application/json")
				  .header("cookie","token=" + token)
				  .pathParam("id",bookingid)
				  .body(bookingPojo)
				  .when()
				  .put("/booking/{id}").then()
				  .extract().response();
	}
	
	public Response deleteBooking(int bookingid, String token) {
		
		return given()
				  .header("cookie","token=" + token)
				  .pathParam("id",bookingid)
				  .when()
				  .delete("/booking/{id}").then()
				  .extract().response();
	}
	

}
