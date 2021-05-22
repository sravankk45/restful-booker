# restful-booker


## Automation test regression suite for restful-booker rest api

### The below is test is implemented with steps

* Creating new booking
	* Create the booking
	* Take bookingid from response
	* Get the booking details based on bookingid
	* Compare response with booking data used to create it to ensure both has same data
	* Delete the booking
* Update booking
	* Create the booking
	* Take bookingid from response
	* Update the booking
	* Compare response with booking data used to update the booking to ensure both has same data
* Get the booking
	* Create the booking
	* Take bookingid from response
	* Get the booking details based on bookingid
	* Compare response with booking data used to create it to ensure both has same data
	* Delete the booking
* Delete the booking
	* Create the booking
	* Take bookingid from response
	* Delete the booking based on bookingid
	* Try to get booking data to verify the deletion is successful



### Tools and Technologies used

* Java (1.8 or higher)
* TestNG
* RestAssured
* Maven
* Extent Report

* Followed Pojo object pattern to represent booking data for serialization and deserialization of the request and responses

### Configuration/setup to be done to run the project

* The below details are configured in config.properies file(available in petstore\src\main\java\com\herokuapp\restfulbooker\config\config.properties)
	* baseURI for pet store rest api
	* username and password for getting token
* TestBase class (com.herokuapp.restfulbooker.base) is base class for all tests and it provides configurations like baseURI,username, password and base directory of project to tests
* Authenticate class(com.herokuapp.restfulbooker.authentication) extends TestBase class and has method (getToken) to retrive the token based on username and password
* All test classes (com.herokuapp.restfulbooker.tests package) extends Authenticate class to inherit base configuration data and token.
* BookerOperations class (com.herokuapp.restfulbooker.operations package) has all operations to perform on booking(Create,update,get and delete booking)
* BookingPojo class (com.herokuapp.restfulbooker.pojo package) represents Booking request and response data to be used for validations


### To Run Tests
* rightclick on testng.xml file and run as testng suite
* after tests run, open report under test-output/TestReport.html to see interactive HTML report on test execution status.
* for testng report, open test-output/index.html




