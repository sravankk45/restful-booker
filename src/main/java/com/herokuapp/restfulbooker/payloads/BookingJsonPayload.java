package com.herokuapp.restfulbooker.payloads;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.herokuapp.restfulbooker.pojo.BookingDatesPojo;
import com.herokuapp.restfulbooker.pojo.BookingPojo;

public class BookingJsonPayload {
	
	private BookingPojo bookingPojo;
	private BookingDatesPojo bookingDatesPojo;
	private SimpleDateFormat dateFormat;
	private Calendar calender;
	
	private String checkin;
	private String checkout;
	
	public BookingPojo createBookingJsonPayload() {
		
		bookingPojo = new BookingPojo();
		bookingDatesPojo = new BookingDatesPojo();
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		calender = Calendar.getInstance();
		calender.setTime(new Date()); // Using today's date
		calender.add(Calendar.DATE, 5); // Adding 5 days
		checkin = dateFormat.format(calender.getTime());
		
		bookingDatesPojo.setCheckin(checkin);
		
		calender.add(Calendar.DATE, 10); // Adding 10 days
		checkout = dateFormat.format(calender.getTime());
		
		bookingDatesPojo.setCheckout(checkout);
		
		bookingPojo.setFirstname("Kevin");
		bookingPojo.setLastname("Raid");
		bookingPojo.setTotalprice(2500);
		bookingPojo.setDepositpaid(true);
		bookingPojo.setBookingdates(bookingDatesPojo);
		bookingPojo.setAdditionalneeds("Early checkin");
		
		return bookingPojo;
		
		
	}

}
