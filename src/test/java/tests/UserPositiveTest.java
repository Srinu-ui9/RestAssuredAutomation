package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payloads.UserPOJO;
import utils.ExcelUtility;
import utils.ExtentReportManager;

public class UserPositiveTest extends BaseClass {

	@DataProvider(name = "positiveUserData")
	public Object[][] positiveUserData() throws IOException {

		ExcelUtility excel = new ExcelUtility();

		excel.openWorkbook(EXCEL_PATH);
		excel.selectSheet("PositiveScenarios");

		int rowCount = excel.getRowCount();
		int columnCount = excel.getColumnCount();

		Object[][] data = new Object[rowCount][columnCount];

		for (int row = 1; row <= rowCount; row++) {

			for (int column = 0; column < columnCount; column++) {

				data[row - 1][column] = excel.getCellData(row, column);
			}
		}

		excel.closeWorkbook();

		return data;
	}

	@Test(priority = 1, description = "Create User data by POST method", dataProvider = "positiveUserData")
	public void createUserTest(String testCase, String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus) {

		ExtentReportManager.createTest(testCase + " - Create User");

		try {

			UserPOJO user = new UserPOJO();

			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);
			user.setUserStatus(Integer.parseInt(userStatus));

			ExtentReportManager.info("Creating user: " + username);

			Response postresponse = UserEndPoints.createuser(user);

			ExtentReportManager.info("Status Code: " + postresponse.statusCode());

			System.out.println("POST Method Response:");
			System.out.println(postresponse.asPrettyString());

			ExtentReportManager.info("Response: " + postresponse.asPrettyString());

			Assert.assertEquals(postresponse.statusCode(), 200, "User creation failed");

			ExtentReportManager.pass("User created successfully");

		} catch (Exception e) {

			ExtentReportManager.fail("Get user failed: " + e.getMessage());

			Assert.fail(e.getMessage());
		}

	}

	@Test(priority = 2, description = "Fetch User data with GET method", dataProvider = "positiveUserData")
	public void getUserTest(String testCase, String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus) {

		ExtentReportManager.createTest(testCase + " - Get User");

		try {

			ExtentReportManager.info("Fetching user: " + username);

			Response getresponse = UserEndPoints.getuser(username);

			ExtentReportManager.info("Status Code: " + getresponse.statusCode());

			System.out.println("GET Method Response:");
			System.out.println(getresponse.asPrettyString());

			ExtentReportManager.info("Response: " + getresponse.asPrettyString());

			Assert.assertEquals(getresponse.statusCode(), 200, "Failed to fetch user");

			Assert.assertEquals(getresponse.jsonPath().getString("username"), username, "Username does not match");

			ExtentReportManager.pass("User fetched successfully");

		} catch (Exception e) {

			ExtentReportManager.fail("Get user failed: " + e.getMessage());

			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 3, description = "Update User details by PUT request", dataProvider = "positiveUserData")
	public void updateUserTest(String testCase, String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus) {

		ExtentReportManager.createTest(testCase + " - Update User");

		try {

			UserPOJO user = new UserPOJO();

			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhone(phone);
			user.setUserStatus(Integer.parseInt(userStatus));

			ExtentReportManager.info("Updating user: " + username);

			Response putresponse = UserEndPoints.updateuser(username, user);

			ExtentReportManager.info("Status Code: " + putresponse.statusCode());

			System.out.println("PUT Method Response:");
			System.out.println(putresponse.asPrettyString());

			ExtentReportManager.info("Response: " + putresponse.asPrettyString());

			Assert.assertEquals(putresponse.statusCode(), 200, "User update failed");

			ExtentReportManager.pass("User updated successfully");

		} catch (Exception e) {

			ExtentReportManager.fail("Update user failed: " + e.getMessage());

			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 4, description = "Delete user data by DELETE request", dataProvider = "positiveUserData")
	public void deleteUserTest(String testCase, String username, String firstName, String lastName, String email,
			String password, String phone, String userStatus) {

		ExtentReportManager.createTest(testCase + " - Delete User");

		try {

			ExtentReportManager.info("Deleting user: " + username);

			Response deleteresponse = UserEndPoints.deleteuser(username);

			ExtentReportManager.info("Status Code: " + deleteresponse.statusCode());

			System.out.println("DELETE Method Response:");
			System.out.println(deleteresponse.asPrettyString());

			ExtentReportManager.info("Response: " + deleteresponse.asPrettyString());

			Assert.assertEquals(deleteresponse.statusCode(), 200, "User deletion failed");

			ExtentReportManager.pass("User deleted successfully");

		} catch (Exception e) {

			ExtentReportManager.fail("Delete user failed: " + e.getMessage());

			Assert.fail(e.getMessage());
		}
	}

}