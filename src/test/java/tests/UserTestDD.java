package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payloads.User;
import utils.Excelutilities;

public class UserTestDD extends BaseClass {

	String sheet = "USER";

	@Test(priority = 1)
	public void createUsers() throws Exception {

	    test = extent.createTest("Create User Test");

	    int rows = Excelutilities.getRowCount(sheet);

	    System.out.println("TOTAL ROWS: " + rows);

	    for (int i = 1; i <= rows; i++) {

	        System.out.println("EXECUTING ROW: " + i);

	        User userPayload = new User();

	        userPayload.setId((int) (Math.random() * 10000));
	        userPayload.setUsername(Excelutilities.DataFetchFromExcel(sheet, i, 0));
	        userPayload.setFirstName(Excelutilities.DataFetchFromExcel(sheet, i, 1));
	        userPayload.setLastName(Excelutilities.DataFetchFromExcel(sheet, i, 2));
	        userPayload.setEmail(Excelutilities.DataFetchFromExcel(sheet, i, 3));
	        userPayload.setPassword(Excelutilities.DataFetchFromExcel(sheet, i, 4));
	        userPayload.setPhone(Excelutilities.DataFetchFromExcel(sheet, i, 5));

	        Response response = UserEndPoints.createuser(userPayload);
	        System.out.println("User Created");
	        response.then().log().all();

			if (response.getStatusCode() == 200) {
				test.pass("User created successfully");
			} else {
				test.fail("User creation failed");
			}

	        System.out.println("RESPONSE: " + response.asString());

	        Assert.assertEquals(response.getStatusCode(), 200);
	    }
	}

			

	
	@Test(priority = 2)
	public void getUser() {

		test = extent.createTest("Get User Test");

		test.info("Sending GET request to fetch user by username");
		
		System.out.println(" Get User");

		Response response = UserEndPoints.getuser("john1233");
		response.then().log().all();

		test.info("Response Status Code: " + response.getStatusCode());

		if (response.getStatusCode() == 200) {
			test.pass("User fetched successfully");
		} else {
			test.fail("Failed to fetch user");
		}

		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void deleteUser() {

		test = extent.createTest("Delete user Test");

		test.info("Deleting user by username");

		System.out.println("User Deleted");
		Response response = UserEndPoints.deleteuser("john1233");
		response.then().log().all();

		test.info("Response Status Code: " + response.getStatusCode());

		if (response.getStatusCode() == 200) {
			test.pass("User deleted successfully");
		} else {
			test.fail("Failed to delete user");
		}

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
