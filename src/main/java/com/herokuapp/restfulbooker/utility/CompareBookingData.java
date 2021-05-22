package com.herokuapp.restfulbooker.utility;

import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.restfulbooker.pojo.BookingPojo;

import io.restassured.response.Response;

public class CompareBookingData {
	
	private BookingPojo resBookingPojo;
	private ObjectMapper mapper = new ObjectMapper();
	private SoftAssert softassert = new SoftAssert();
	
	
	public void compareData(Response response,BookingPojo bookingPojo) {
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			
			resBookingPojo = mapper.readValue(response.asPrettyString(), BookingPojo.class);
		}
		catch(JsonMappingException e) {
			e.printStackTrace();
		}
		catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		softassert.assertEquals(bookingPojo.getFirstname(), resBookingPojo.getFirstname(),"First Name data mismatching");
		softassert.assertEquals(bookingPojo.getLastname(), resBookingPojo.getLastname(), "last name data mismatching");
		softassert.assertEquals(bookingPojo.getBookingdates().getCheckin(), resBookingPojo.getBookingdates().getCheckin(),"checkin date mismatching");
		softassert.assertEquals(bookingPojo.getBookingdates().getCheckout(), resBookingPojo.getBookingdates().getCheckout(),"checkout date mismatching");
		softassert.assertEquals(bookingPojo.getTotalprice(),resBookingPojo.getTotalprice(), "Total Price mismatching");
		softassert.assertEquals(bookingPojo.getAdditionalneeds(), resBookingPojo.getAdditionalneeds(), "Additional needs data mismatching");
		softassert.assertEquals(bookingPojo.isDepositpaid(), resBookingPojo.isDepositpaid(), "Deposit paid data mismatching");
		softassert.assertAll();
		
	
	}

}
