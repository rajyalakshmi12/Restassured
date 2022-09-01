package Api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.scribejava.core.model.Response;

import Api.endpoints.UserEndpoints;
import Api.payload.User;
import Api.utilites.Dataproivder;

public class datadriventest {

	// To get all the data from excel
	@Test(priority=1,dataProvider="Data",dataProviderClass=Dataproivder.class)
	
	// pass as parameters to the method exactly which is there in excel sheet
	public void testpostuser(String Userid, String Username, String Firstname, String Lastname, String Email, String Password, String phone)
	{
		User userpayload = new User();// create pojo class object where user is the pay load, here created pay load
		
		userpayload.setId(Integer.parseInt(Userid));

		userpayload.setId(Integer.parseInt(Userid));
		userpayload.setUsername(Username);
		userpayload.setFirstName( Firstname);
		userpayload.setLastName(Lastname);
		userpayload.setEmail( Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(phone);
		
		
		io.restassured.response.Response response=UserEndpoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(),200);
			
	}
	// delete the details which are created in the first test
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataproivder.class)
	public void testDeleteUserByName(String userName)
	{
			io.restassured.response.Response response=UserEndpoints.deleteUser(userName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}
		
		
}
