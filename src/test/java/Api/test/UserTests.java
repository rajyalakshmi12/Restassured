package Api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.github.scribejava.core.model.Response;

import Api.endpoints.UserEndpoints;
import Api.payload.User;

public class UserTests {
	Faker faker;
	User userpayload; // passed as parameter in userendpoints
	// generate the data using pojo
	@BeforeClass
public void setupdata()
{
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());

		}

	@Test(priority=1)
	public void testPostUser()
	{
		io.restassured.response.Response response = UserEndpoints.createUser(userpayload);// called from userendpointsclass
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
	@Test(priority = 2)
	public void testgetuserbyname()
	{
		io.restassured.response.Response response = UserEndpoints.readUser(this.userpayload.getUsername());
response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	// update the data 
	@Test(priority = 3)
		public void testupdateuserbyname()
		{
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
			io.restassured.response.Response response = UserEndpoints.updateUser(this.userpayload.getUsername(),userpayload);
			// getting user data from the pay load
	response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(),200);
			// checking data after update
			io.restassured.response.Response responseafterupdate = UserEndpoints.readUser(this.userpayload.getUsername());
			response.then().log().all();
					
					Assert.assertEquals(responseafterupdate.getStatusCode(),200);
	}
	@Test(priority = 4)
	public void testdeleteuserbyname()
	{
		io.restassured.response.Response response = UserEndpoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
}	
