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

public class UserNegativeTest extends BaseClass {

	@DataProvider(name = "negativeUserData")
	public Object[][] negativeUserData() throws IOException {

		ExcelUtility excel = new ExcelUtility();

		excel.openWorkbook(EXCEL_PATH);

		excel.selectSheet("NegativeScenarios");

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

	@Test(priority = 1, description = "Fetch user with invalid username", dataProvider = "negativeUserData")
	public void getInvalidUserTest(String testCase, String username, String expectedStatusCode) {

		ExtentReportManager.createTest(testCase + " - Get Invalid User");
		
		// Create payload for PUT request
        UserPOJO user = new UserPOJO();

        user.setUsername(username);
        user.setFirstName("Invalid");
        user.setLastName("User");
        user.setEmail("invalid@test.com");
        user.setPassword("invalid123");
        user.setPhone("9999999999");
        user.setUserStatus(1);

		ExtentReportManager.info("Fetching invalid user: " + username);

		Response response = UserEndPoints.getuser(username);

		ExtentReportManager.info("Actual Status Code: " + response.statusCode());

		ExtentReportManager.info("Expected Status Code: " + expectedStatusCode);

		ExtentReportManager.info("Response: " + response.asPrettyString());

		Assert.assertEquals(response.statusCode(), Integer.parseInt(expectedStatusCode), "Unexpected status code");

		ExtentReportManager.pass("Invalid user handled successfully");
	}

	@Test(priority = 2, description = "Update user with invalid username", dataProvider = "negativeUserData")
	public void updateInvalidUserTest(String testCase, String username, String expectedStatusCode) {

		ExtentReportManager.createTest(testCase + " - Update Invalid User");

		ExtentReportManager.info("Updating invalid user: " + username);

		Response response = UserEndPoints.updateuser(username, null);

		ExtentReportManager.info("Actual Status Code: " + response.statusCode());

		ExtentReportManager.info("Expected Status Code: " + expectedStatusCode);

		ExtentReportManager.info("Response: " + response.asPrettyString());

		Assert.assertEquals(response.statusCode(), Integer.parseInt(expectedStatusCode), "Unexpected status code");

		ExtentReportManager.pass("Invalid update handled successfully");
	}

	@Test(priority = 3, description = "Delete user with invalid username", dataProvider = "negativeUserData")
	public void deleteInvalidUserTest(String testCase, String username, String expectedStatusCode) {

		ExtentReportManager.createTest(testCase + " - Delete Invalid User");

		ExtentReportManager.info("Deleting invalid user: " + username);

		Response response = UserEndPoints.deleteuser(username);

		ExtentReportManager.info("Actual Status Code: " + response.statusCode());

		ExtentReportManager.info("Expected Status Code: " + expectedStatusCode);

		ExtentReportManager.info("Response: " + response.asPrettyString());

		Assert.assertEquals(response.statusCode(), Integer.parseInt(expectedStatusCode), "Unexpected status code");

		ExtentReportManager.pass("Invalid delete handled successfully");
	}
}
