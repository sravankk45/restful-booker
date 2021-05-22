package com.herokuapp.restfulbooker.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.herokuapp.restfulbooker.authentication.Authenticate;
import com.herokuapp.restfulbooker.operations.BookerOperations;
import com.herokuapp.restfulbooker.payloads.BookingJsonPayload;
import com.herokuapp.restfulbooker.pojo.BookingPojo;
import com.herokuapp.restfulbooker.utility.CompareBookingData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookerTests extends Authenticate{
	

	private String token;
	private BookerOperations bookerOperations;
	private BookingJsonPayload bookingJsonPayload;
	private BookingPojo bookingPojo;
	private Response response;
	private JsonPath jsonResponse;
	private CompareBookingData compareBookingData;
	
	/**
	 *  Gets the Base URI and get token for authentication before test suite runs
	 */
	@BeforeSuite
	public void beforeSuite() {
		
		bookingJsonPayload = new BookingJsonPayload();
		bookerOperations = new BookerOperations();
		compareBookingData = new CompareBookingData();
		
		RestAssured.baseURI = getBaseUri();
		token = getToken();

	}
	
	@Test
	public void createNewBookingTest() {
		
		bookingPojo=bookingJsonPayload.createBookingJsonPayload();
		response = bookerOperations.createBooking(bookingPojo);
		Assert.assertEquals(response.statusCode(), 200, "Booking creation failed");
		
		jsonResponse = new JsonPath(response.asString());
		int bookingId = jsonResponse.get("bookingid");
		
		response = bookerOperations.getBooking(bookingId);
		compareBookingData.compareData(response, bookingPojo);
		
		response = bookerOperations.deleteBooking(bookingId, token);
		Assert.assertEquals(response.statusCode(), 201, "Booking deletion failed");
		
	}
	
	@Test
	public void updateBookingTest() {
		
		bookingPojo=bookingJsonPayload.createBookingJsonPayload();
		response = bookerOperations.createBooking(bookingPojo);
		Assert.assertEquals(response.statusCode(), 200, "Booking creation failed");
		
		jsonResponse = new JsonPath(response.asString());
		int bookingId = jsonResponse.get("bookingid");
		
		bookingPojo.setTotalprice(3500);
		bookingPojo.setAdditionalneeds("Early checkin and late checkout");
		response = bookerOperations.updateBooking(bookingId, bookingPojo, token);
		Assert.assertEquals(response.statusCode(), 200, "Booking data retrieve unsuccessful");
		compareBookingData.compareData(response, bookingPojo);
		
		response = bookerOperations.deleteBooking(bookingId, token);
		Assert.assertEquals(response.statusCode(), 201, "Booking deletion failed");
		
	}	
	
	@Test
	public void getBookingTest() {
		
		bookingPojo=bookingJsonPayload.createBookingJsonPayload();
		response = bookerOperations.createBooking(bookingPojo);
		Assert.assertEquals(response.statusCode(), 200, "Booking creation failed");
		
		jsonResponse = new JsonPath(response.asString());
		int bookingId = jsonResponse.get("bookingid");
		
		response = bookerOperations.getBooking(bookingId);
		Assert.assertEquals(response.statusCode(), 200, "Booking data retrieve unsuccessful");
		
		compareBookingData.compareData(response, bookingPojo);
		
		response = bookerOperations.deleteBooking(bookingId, token);
		Assert.assertEquals(response.statusCode(), 201, "Booking deletion failed");
		
	}
	
	@Test
	public void deleteBookingTest() {
		
		bookingPojo=bookingJsonPayload.createBookingJsonPayload();
		response = bookerOperations.createBooking(bookingPojo);
		Assert.assertEquals(response.statusCode(), 200, "Booking creation failed");
		
		jsonResponse = new JsonPath(response.asString());
		int bookingId = jsonResponse.get("bookingid");
		
		response = bookerOperations.deleteBooking(bookingId, token);
		Assert.assertEquals(response.statusCode(), 201, "Booking deletion failed");
	
		response = bookerOperations.getBooking(bookingId);
		Assert.assertEquals(response.statusCode(), 404, "Booking deletion failed");
		
	}

}
